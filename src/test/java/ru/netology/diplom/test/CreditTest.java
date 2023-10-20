package ru.netology.diplom.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diplom.data.DataHelper;
import ru.netology.diplom.data.SQLHelper;
import ru.netology.diplom.page.StartPage;

import static com.codeborne.selenide.Selenide.open;

public class CreditTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @DisplayName("Successful credit card purchase")
    @Test
    public void shouldConfirmCreditApprovedCard() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var approvedCardInformation = DataHelper.getValidCard();
        buyCredit.enterCreditCardData(approvedCardInformation);
        buyCredit.verifySuccessNotificationCreditCard();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusCredit(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }

    @DisplayName("Successful credit purchase with current M and Y.")
    @Test
    public void shouldConfirmCreditWithCurrentMonthAndYear() {
        var startPage = new StartPage();
        var buyCredit = startPage.openBuyCredit();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        buyCredit.enterCreditCardData(validCardInformation);
        buyCredit.verifySuccessNotificationCreditCard();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusCredit(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }
}
