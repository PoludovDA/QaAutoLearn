package helpDesk;

import core.BaseSeleniumPage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


/**
 * В page классах в функциях вместо void возвращается экземпляр страницы которая откроется следующей
 * так можно обращаться к методам следующей страницы через точку (одной строкой)
 * это продвинутый способ оформления pageObject
 */
public class HelpDeskTest {

    @Test
    public void checkTicket(){
//        String title = getUniqueString(TestValues.TEST_TITLE);
//        TicketPage ticketPage = new MainPage().createTicket(title, TestValues.TEST_BODY, TestValues.TEST_EMAIL)
//                .openLoginPage()
//                .auth(ConfigProvider.DEMO_LOGIN, ConfigProvider.DEMO_PASSWORD)
//                .findTicket(title);
//        Assertions.assertTrue(ticketPage.getTitle().contains(title));
//        Assertions.assertEquals(ticketPage.getBody(), TestValues.TEST_BODY);
//        Assertions.assertEquals(ticketPage.getEmail(), TestValues.TEST_EMAIL);
    }
}
