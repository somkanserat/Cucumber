package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Assertions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


public class ExcelStepdefinitions {

    Workbook workbook;
    Sheet sayfa1;
    String actualHucreData;
    Map<String, Map<String,String>> ulkelerMap;

    @Given("kullanici baskentler exceline ulasir")
    public void kullanici_baskentler_exceline_ulasir() throws IOException {

        String excelDosyaYolu = "src/test/resources/ulkeler.xlsx";
        FileInputStream fileInputStream = new FileInputStream(excelDosyaYolu);
        workbook = WorkbookFactory.create(fileInputStream);
        sayfa1 = workbook.getSheet("Sayfa1");

    }

    @Then("{int}.satir {int}.hucredeki datayi yazdirir")
    public void satir_hucredeki_datayi_yazdirir(Integer satirNo, Integer hucreNo) {
        System.out.println(satirNo+". satır "+hucreNo+". hücredeki bilgi: "
                           +sayfa1.getRow(satirNo-1).getCell(hucreNo-1));
    }

    @And("{int}.satir {int}.hucredeki datayi bir string degiskene kaydeder")
    public void satir_hucredeki_datayi_bir_string_degiskene_kaydeder(Integer satirNo, Integer hucreNo) {
        actualHucreData = sayfa1.getRow(satirNo-1).getCell(hucreNo-1).getStringCellValue();
    }

    @And("kaydedilen datanin {string} oldugunu test eder")
    public void kaydedilen_datanin_oldugunu_test_eder(String expectedData) {
        Assertions.assertEquals(expectedData,actualHucreData);
    }

    @Then("baskenti {string} olan ulkenin Turkce isminin {string} oldugunu test eder")
    public void baskentiOlanUlkeninTurkceIsmininOldugunuTestEder(String verilenIngilizceBaskent, String expectedTurkceUlkeIsmi) {
        // ingilizce baskent ismi Jakarta olan ulkeyi bulmak icin
        // her bir satiri kontrol etmeliyiz
        for (int i=0; i<=sayfa1.getLastRowNum(); i++){
            String satirdakiIngilizceBaskent = sayfa1.getRow(i)
                                                     .getCell(1)
                                                     .getStringCellValue();

            if (satirdakiIngilizceBaskent.equals(verilenIngilizceBaskent)){
                // bir satirda başkent ismini Jakarta bulursak
                // o satirda Turkce ulke ismini alip
                String satirdakiTurkceUlkeIsmi = sayfa1.getRow(i)
                        .getCell(2)
                        .getStringCellValue();
                // expected ulke ismiyle ayni oldugunu test ederiz

                Assertions.assertEquals(expectedTurkceUlkeIsmi,satirdakiTurkceUlkeIsmi);
                break;
            }
        }

    }

    @And("excelde kayitli ulke sayisinin {int} oldugunu test eder")
    public void excelde_kayitli_ulke_sayisinin_oldugunu_test_eder(Integer expectedUlkeSayisi) {

        int actualUlkeSayisi = sayfa1.getLastRowNum()+1-1; //index olduğu için +1, en üstteki başlığı çıkarmak için -1
        Assertions.assertEquals(expectedUlkeSayisi,actualUlkeSayisi);
    }

    @And("excelde kullanilan fiziki satir sayisinin {int} oldugunu test eder")
    public void excelde_kullanilan_fiziki_satir_sayisinin_oldugunu_test_eder(Integer expectedSatirSayisi) {
        int actualSatirSayisi = sayfa1.getPhysicalNumberOfRows();
        Assertions.assertEquals(expectedSatirSayisi,actualSatirSayisi);
    }

    @When("Tum bilgileri map olarak kaydedip")
    public void tum_bilgileri_map_olarak_kaydedip() {
        /*
            map'i olusturmadan once bazi kararlari almamiz lazim

            1- hangi bilgiler map'de tutulacak?
               ingilizceUlkeIsmi, ingilizceBaskentIsmi, turkceUlkeIsmi, turkceBaskentIsmi

            2- hangi bilgi key olacak?
               ingilizceBaskentIsmi key olacak

            3- Kalan bilgiler nasil tek bir value yapilacak?
                ingilizceUlkeIsmi, turkceUlkeIsmi, turkceBaskentIsmi
                - birlestirip tek bir String yapmak
                - map yapmak
                - array veya list yapmak
                biz map yapmayi tercih ettik
         */
        ulkelerMap = new TreeMap<>();
        Map<String,String> valueMap;

        for (int i=1;i<=sayfa1.getLastRowNum();i++){
            valueMap = new TreeMap<>();
            valueMap.put("ingilizceUlkeIsmi",sayfa1.getRow(i).getCell(0).getStringCellValue());
            valueMap.put("turkceUlkeIsmi",sayfa1.getRow(i).getCell(2).getStringCellValue());
            valueMap.put("turkceBaskentIsmi",sayfa1.getRow(i).getCell(3).getStringCellValue());

            ulkelerMap.put(sayfa1.getRow(i).getCell(1).getStringCellValue(),valueMap);
        }
        //System.out.println(ulkelerMap);
        /*
            {
              Abidjan = {ingilizceUlkeIsmi=Ivory Coast, turkceBaskentIsmi=Abidjan, turkceUlkeIsmi=Fildişi Sahili},
              Abu Dhabi={ingilizceUlkeIsmi=United Arab Emirates, turkceBaskentIsmi=Abu Dabi, turkceUlkeIsmi=Birleşik Arap Emrlikleri},
              Abuja={ingilizceUlkeIsmi=Nigeria, turkceBaskentIsmi=Abuja, turkceUlkeIsmi=Nijerya},
              Accra={ingilizceUlkeIsmi=Ghana, turkceBaskentIsmi=Accra, turkceUlkeIsmi=Gana}
         */
    }

    @Then("Ingilizce baskent ismi {string} olan ulkenin tum bilgilerini yazdirir")
    public void ingilizceBaskentIsmiOlanUlkeninTumBilgileriniYazdirir(String verilenIngilizceBaskentIsmi) {
        //System.out.println(ulkelerMap.get(verilenIngilizceBaskentIsmi));
        System.out.println("Başken ismi"+verilenIngilizceBaskentIsmi+" olan ülkenin bilgileri:"
                            +"\nİngilizce ülke ismi: "+ulkelerMap.get(verilenIngilizceBaskentIsmi).get("ingilizceUlkeIsmi")
                            +"\nTürke ülke ismi: "+ulkelerMap.get(verilenIngilizceBaskentIsmi).get("turkceUlkeIsmi")
                            +"\nTürkçe başkent ismi: "+ulkelerMap.get(verilenIngilizceBaskentIsmi).get("turkceBaskentIsmi")
        );
    }

    @And("mapi kullanarak Turkce ismi {string} olan bir ulke bulundugunu test eder")
    public void mapiKullanarakTurkceIsmiOlanBirUlkeBulundugunuTestEder(String verilenTurkceUlkeIsmi) {
// Turkce ulke ismi value icindeki bilgilerden biri
        // ulkelerMap.containsValue() kullanmak icin TUM VALUE'nun verilmis olmasi lazim
        // Bu durumda tum value'leri gozden gecirebilmek icin
        // map'den tum value'leri alip, farkli bir formatta kaydetmeliyiz

        Collection<Map<String,String>> valueCollection = ulkelerMap.values();

        // valueCollection'daki herbir value'yu ele almak icin
        // bir for-each loop olusturabiliriz
        boolean arananUlkeVarMi = false;

        for (Map<String,String> eachValueMap  :valueCollection){

            if (eachValueMap.get("turkceUlkeIsmi").equals(verilenTurkceUlkeIsmi)){
                arananUlkeVarMi = true;
                break;
            }
        }

        Assertions.assertTrue(arananUlkeVarMi);
    }

}
