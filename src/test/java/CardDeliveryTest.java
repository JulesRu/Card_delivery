import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    private String generateDate(int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

@Test
    void shouldReceiveCardDeliveryForm() {

    Configuration.holdBrowserOpen = true;
    open("http://localhost:9999");
    $x("//input[@placeholder='Город']").setValue("Калининград");
    String currentDate = generateDate(3);
    $("[data-test-id=date] input").setValue(currentDate);
    $("[data-test-id=name] input").setValue("Круглова Валентина");
    $("[data-test-id=phone] input").setValue("+79659087622");
    $("[data-test-id=agreement]").click();
    $("button.button").click();
    $(".notification__content")
            .shouldBe(Condition.visible, Duration.ofSeconds(11))
            .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));
}
}
