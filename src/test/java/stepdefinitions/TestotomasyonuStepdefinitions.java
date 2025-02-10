package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class TestotomasyonuStepdefinitions {

    TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();

    // Aşağıdaki değerleri birden fazla metot içerisinde kullanabilmek için burada tanımladık.
    // Diğer metotlarda ya atama yaptık ya da işleme soktuk
    String satirdakiUrunIsmi;
    Double satirdakiMinBulunacakUrunSayisi;
    double actualSonucSayisi;
    Sheet calisilanSayfa;
    Workbook workbook;

    @Given("kullanici testotomasyonu anasayfaya gider")
    public void kullanici_testotomasyonu_anasayfaya_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
    }

    @When("arama kutusuna phone yazip aratir")
    public void arama_kutusuna_phone_yazip_aratir() {
        testotomasyonuPage.aramaKutusu.sendKeys("phone" + Keys.ENTER);
    }

    @Then("arama sonucunda urun bulunabildigini test eder")
    public void arama_sonucunda_urun_bulunabildigini_test_eder() {
        String unExpectedAramaSonucu = ConfigReader.getProperty("toUnexpectedSonuc");
        String actualAramaSonucu = testotomasyonuPage.aramaSonucuElementi
                                                        .getText();

        Assertions.assertNotEquals(unExpectedAramaSonucu,actualAramaSonucu);
    }

    @Then("sayfayi kapatir")
    public void sayfayi_kapatir() {
        Driver.quitDriver();

    }

    @When("arama kutusuna dress yazip aratir")
    public void aramaKutusunaDressYazipAratir() {
        testotomasyonuPage.aramaKutusu.sendKeys("dress" + Keys.ENTER);
    }

    @When("arama kutusuna java yazip aratir")
    public void aramaKutusunaJavaYazipAratir() {
        testotomasyonuPage.aramaKutusu.sendKeys("java" + Keys.ENTER);
    }

    @Then("arama sonucunda urun bulunamadigini test eder")
    public void aramaSonucundaUrunBulunamadiginiTestEder() {

        String expectedAramaSonucu = ConfigReader.getProperty("toUnexpectedSonuc");
        String actualAramaSonucu = testotomasyonuPage.aramaSonucuElementi
                                                    .getText();

        Assertions.assertEquals(expectedAramaSonucu,actualAramaSonucu);

    }

    @When("arama kutusuna {string} yazip aratir")
    //senaryoda step içerisinde yazılan string değer, burada parametre olarak tanımlanır.
    public void aramaKutusunaYazipAratir(String aranacakKelime) {

        testotomasyonuPage.aramaKutusu.sendKeys(aranacakKelime + Keys.ENTER);
    }

    @And("senkronizasyon icin {int} saniye bekler")
    //senaryoda step içerisinde yazılan int değer, burada parametre olarak tanımlanır.
    public void senkronizasyonIcinSaniyeBekler(int beklenecekSaniye) {

        ReusableMethods.bekle(beklenecekSaniye);
    }

    @And("ilk urunu tiklar")
    public void ilkUrunuTiklar() {
        testotomasyonuPage.ilkUrunElementi.click();
    }

    @Then("acilan sayfada urun isminde case sensivite olmadan {string} bulundugunu test eder")
    public void acilanSayfadaUrunIsmindeCaseSensiviteOlmadanBulundugunuTestEder(String arananKelime) {
        String actualUrunIsmi = testotomasyonuPage.ilkUrunSayfasindakiIsimElementi.getText().toLowerCase();
        Assertions.assertTrue(actualUrunIsmi.contains(arananKelime));
    }


    @When("arama kutusuna test data dosyasindaki belirlenen kelimeyi yazip aratir")
    public void aramaKutusunaTestDataDosyasindakiBelirlenenKelimeyiYazipAratir() {
        testotomasyonuPage.aramaKutusu.sendKeys(ConfigReader.getProperty("toAranacakKelime") + Keys.ENTER);
    }


    @Then("acilan sayfada urun isminde case sensivite olmadan test data dosyasindaki belirlenen kelime bulundugunu test eder")
    public void acilanSayfadaUrunIsmindeCaseSensiviteOlmadanTestDataDosyasindakiBelirlenenKelimeBulundugunuTestEder() {
        String actualUrunIsmi = testotomasyonuPage.ilkUrunSayfasindakiIsimElementi.getText().toLowerCase();
        String expectedUrunIsmi = ConfigReader.getProperty("toAranacakKelime");

        Assertions.assertTrue(actualUrunIsmi.contains(expectedUrunIsmi));
    }

    @Given("kullanici test data dosyasinda verilen {string} anasayfaya gider")
    public void kullanici_test_data_dosyasinda_verilen_anasayfaya_gider(String configIstenenUrl) {
        Driver.getDriver().get(ConfigReader.getProperty(configIstenenUrl));

    }

    @Then("url'in test data dosyasinda verilen {string} ile ayni oldugunu test eder")
    public void url_in_test_data_dosyasinda_verilen_ile_ayni_oldugunu_test_eder(String configIstenenUrl) {
        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrl = ConfigReader.getProperty(configIstenenUrl);

        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Then("account butonuna basar")
    public void account_butonuna_basar() {
        testotomasyonuPage.accountLinki.click();
    }

    @Then("email olarak {string} girer")
    public void email_olarak_girer(String configIstenenEmail) {
        testotomasyonuPage.emailKutusu.sendKeys(ConfigReader.getProperty(configIstenenEmail));
    }

    @Then("password olarak {string} girer")
    public void password_olarak_girer(String configIstenenPassword) {
        testotomasyonuPage.passwordKutusu.sendKeys(ConfigReader.getProperty(configIstenenPassword));
    }

    @Then("signIn butonuna basar")
    public void sign_in_butonuna_basar() {
        testotomasyonuPage.loginButonu.click();
    }

    @Then("basarili giris yapilabildigini test eder")
    public void basarili_giris_yapilabildigini_test_eder() {
        Assertions.assertTrue(testotomasyonuPage.logoutButonu.isDisplayed());
    }

    @Then("logout butonuna basar")
    public void logoutButonunaBasar() {
        testotomasyonuPage.logoutButonu.click();
    }

    @And("sayfayi asagi kaydirir")
    public void sayfayiAsagiKaydirir() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);
    }

    @And("sisteme giris yapamadigini test eder")
    public void sistemeGirisYapamadiginiTestEder() {
        Assertions.assertTrue(testotomasyonuPage.emailKutusu.isDisplayed());
    }

    @Then("arama sonucunda {string} veya daha fazla urun bulunabildigini test eder")
    public void aramaSonucundaVeyaDahaFazlaUrunBulunabildiginiTestEder(String belirlenenMiktarStr) {
        /*
        belirlenen sayıda veya daha fazla urun bulunabildiğini test etmek için
        1- sonuç yazısındaki sayıyı alabiliriz
        2- bulunan ürünleri findElements ile locate edip bir listeye koyabilir
           ve listenin size'ina bakabiliriz

        ANCCCAAAKKK 2.yöntemi tercih ederseniz
        gösterilecek ürün olmadığında, locator implicitlyWait süresince
        elemanları locate edebilmek için bekleyecektir
        bu da zaman kaybi olur
      */
        String actualSonucYazisi = testotomasyonuPage.aramaSonucuElementi.getText(); //4 Products Found
        String actualSonucYazisiStr = actualSonucYazisi.replaceAll("\\D",""); //4
        int actualSonucSayisi = Integer.parseInt(actualSonucYazisiStr);

        int expectedMinSonucSayisi = Integer.parseInt(belirlenenMiktarStr);
        //belirlenenMiktar da String olduğu için, int'e döndürürüz.

        Assertions.assertTrue(actualSonucSayisi >= expectedMinSonucSayisi);
    }

    @When("email olarak listede verilen {string} girer")
    public void emailOlarakListedeVerilenGirer(String direktEmail) {

        testotomasyonuPage.emailKutusu.sendKeys(direktEmail);
    }

    @And("password olarak listede verilen {string} girer")
    public void passwordOlarakListedeVerilenGirer(String direktPassword) {
        testotomasyonuPage.passwordKutusu.sendKeys(direktPassword);
    }

    @Then("urun excelindeki {string} daki urunun min. miktarini ve urun ismini kaydeder")
    public void urun_excelindeki_daki_urunun_min_miktarini_ve_urun_ismini_kaydeder(String excelSatirNoStr) throws IOException {
        // ornek satir no "3"

        String excelDosyaYolu = "src/test/resources/urunListesi.xlsx";
        FileInputStream fileInputStream = new FileInputStream(excelDosyaYolu);
        workbook = WorkbookFactory.create(fileInputStream);
        calisilanSayfa = workbook.getSheet("Sheet1"); //excel'de yazılan sayfa ismi

        // sayfaya kadar gittik. Bu metoda, parametre olarak gelen, String excelSatirNoStr değerine göre
        // ilgili satıra gidip, satırdakiUrunIsmi ve satirdakiMinBulunacakUrunSayisi değerlerini kaydedelim

        //once istenen satıra gidelim.

        int satirNo = Integer.parseInt(excelSatirNoStr); //3
        satirdakiUrunIsmi = calisilanSayfa.getRow(satirNo-1).getCell(0).getStringCellValue();
        satirdakiMinBulunacakUrunSayisi = calisilanSayfa.getRow(satirNo-1).getCell(1).getNumericCellValue();
    }

    @Then("urun ismini testotomasyonu sayfasinda arar ve sonuc sayisini kaydeder")
    public void urun_ismini_testotomasyonu_sayfasinda_arar_ve_sonuc_sayisini_kaydeder() {
        testotomasyonuPage.aramaKutusu.sendKeys(satirdakiUrunIsmi+Keys.ENTER);

        String actualSonucYazisi = testotomasyonuPage.aramaSonucuElementi.getText(); //4 Products Found
        String actualSonucYazisiStr = actualSonucYazisi.replaceAll("\\D",""); //4
        actualSonucSayisi = Double.parseDouble(actualSonucYazisiStr);
    }

    @Then("bulunan urun sayisinin {string} da verilen min. miktardan fazla oldugunu test eder")
    public void bulunan_urun_sayisinin_da_verilen_min_miktardan_fazla_oldugunu_test_eder(String satirNoStr) {
        Assertions.assertTrue(actualSonucSayisi>=satirdakiMinBulunacakUrunSayisi);
    }

    @Then("bulunan sonuc sayisini excelde {string} daki {int}. sutuna yazdirir")
    public void bulunanSonucSayisiniExceldeDakiSutunaYazdirir(String satirNoStr, int sutunNo) throws IOException {

        //once workbook'da istenen işlemi yapalım
        int satirNo = Integer.parseInt(satirNoStr);
        calisilanSayfa
                .getRow(satirNo-1)              //ilgili satıra gittik
                .createCell(sutunNo-1)          //ilgili satırdan cell oluşturduk
                .setCellValue(actualSonucSayisi); // cell'e değer olarak actualSonucSayisini kaydettik

        //excel'e bilgi yazdırmak için FileOutputStream'e ihtiyaç var
        String excelDosyaYolu = "src/test/resources/urunListesi.xlsx";
        FileOutputStream fileOutputStream = new FileOutputStream(excelDosyaYolu);
        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();
    }
}


