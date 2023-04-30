import com.insider.pages.*;
import org.testng.annotations.Test;

public class InsiderTests extends BaseTest {

    @Test
    public void insiderTest(){
        extentLogger = report.createTest("insiderTest");

        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage()
                .verifyHomePageOpened()
                .goToSubMenuInNavBarMenus("More","Careers");

        new CareersPage(driver).verifyCareersPageOpened()
                .selectTeam("Quality Assurance");

        new QAPage(driver).clickSeeAllJobsBtn();

        new PositionsPage(driver).filterJobs("Istanbul, Turkey")
                .checkPresenceOfPositions()
                .verifyPositionsContainsJobTitle("Quality Assurance")
                .verifyDepartmentsText("Quality Assurance")
                .verifyLocationsText("Istanbul, Turkey")
                .clickApplyButton();

        new LeverPage(driver).verifyLeverPageOpened();
    }
}
