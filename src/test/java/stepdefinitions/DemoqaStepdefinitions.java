package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DemoqaPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

public class DemoqaStepdefinitions {

    DemoqaPage demoqaPage = new DemoqaPage();

    @Then("soldaki sekmelerden Alerts'e tiklar")
    public void soldaki_sekmelerden_alerts_e_tiklar() {
        demoqaPage.alertSekmesi.click();
        ReusableMethods.bekle(1);
    }

    @When("On button click, alert will appear after {int} seconds karsisindaki click me butonuna basar")
    public void on_button_click_alert_will_appear_after_seconds_karsisindaki_click_me_butonuna_basar(Integer saniye) {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);
        demoqaPage.timerAlertButton.click();

    }

    @And("Allert'in gorunur olmasini bekler")
    public void allert_in_gorunur_olmasini_bekler() {
        // 1.adim: wait objesi olusturmak
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        // 2.adim: mumkunse wait icin kullanacagimiz Webelementi locate edip kaydedin
        //         biz alert'i bekleyecegimiz icin locate edecek bir sey yok

        // 3.adim expectedConditions kullanarak wait objesini bekletin
        wait.until(ExpectedConditions.alertIsPresent());
    }

    @Then("Allert uzerindeki yazinin {string} oldugunu test eder")
    public void allert_uzerindeki_yazinin_oldugunu_test_eder(String expectedAlertYazisi) {
        String actualAlertYazisi = Driver.getDriver().switchTo().alert().getText();

        Assertions.assertEquals(expectedAlertYazisi,actualAlertYazisi);
    }

    @And("Ok diyerek alerti kapatir")
    public void ok_diyerek_alerti_kapatir() {
        Driver.getDriver().switchTo().alert().accept();
    }

    @Then("soldaki sekmelerden Elements'e tiklar")
    public void soldakiSekmelerdenElementseTiklar() {
        demoqaPage.elementsSekmesi.click();
    }

    @Then("acilan menuden Dynamic Properties'e tiklar")
    public void acilanMenudenDynamicPropertieseTiklar() {
        demoqaPage.dynamicPropertiesMenu.click();
    }

    @And("Will enable {int} seconds butonunun enable olmasini bekler")
    public void willEnableSecondsButonununEnableOlmasiniBekler(int beklemeSuresi) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(demoqaPage.enableAfter5SecondsButonu));

    }

    @Then("butonun enable oldugunu test eder")
    public void butonunEnableOldugunuTestEder() {
        Assertions.assertTrue(demoqaPage.enableAfter5SecondsButonu.isEnabled());
    }


    @And("Visible after {int} seconds butonunun visible olmasini bekler")
    public void visibleAfterSecondsButonununVisibleOlmasiniBekler(int beklemeSuresi) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        // bekledigimiz visible after 5 secods butonu
        // visible olmadigi icin locate edip webelement olarak kaydetme sansimiz yok
        // bunun yerine 2. ve 3. adimlari birlestirip
        // locate edip kaydetme ve bekleme islemelerini BIRLIKTE yapiyoruz
        // bekleme ve locate'i birlikte yapacagimiz icin
        // page class'inda degil burada locate etmeliyiz

        WebElement visibleAfter5Seconds = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
    }

    @Then("butonun visible oldugunu test eder")
    public void butonunVisibleOldugunuTestEder() {
        Assertions.assertTrue(demoqaPage.visibleAfter5Seconds.isDisplayed());
    }
}
