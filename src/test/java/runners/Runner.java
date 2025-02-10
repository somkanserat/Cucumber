package runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
//@IncludeEngines("cucumber")  : Class'in cucumber ile calismasini saglar

@SelectClasspathResource("src/test/java")
//@SelectClasspathResource("src/test/java") : Java class'larini bulacagi dosya yolu

@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME,value = "src/test/resources/features") // --> calistirilacak feature dosyalarinin yerini gosterir
//Constants.FEATURES_PROPERTY_NAME : "src/test/resources/features/wip" --> calistirilacak feature dosyalarinin yerini gosterir
// aşağıdaki açıklamada 1.madde(wip)

@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,value = "stepdefinitions") //Glue:Yapıştırıcı
// Constants.GLUE_PROPERTY_NAME : "stepdefinitions" --> belirlenen feature dosyalarini calistirmak icin
// kullanilacak stepdefinition'larin bulundugu package'in dosya yolu

@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME,value = "@smoke")
/*
        EGER sadece bu notasyonlari kullanirsak,
        features klasoru icindeki tum feature'lari calistirir

        istedigimiz feature'lari calistirmak icin
        cucumber 2 alternatif sunar
        1- istediginiz feature'lari bir klasor icine koyup
           sadece o klasoru calistirmasini isteyebilirsiniz(wip: work in progress(üzerinde çalışılan dosyalar))

        2- istediginiz Feature veya Scenario'lari bir tag ile belirleyip,
           Constants.FILTER_TAGS_PROPERTY_NAME : "@smoke"
*/

@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME,value = "false")
    /*
        EGER Runner dosyasi ile calistirdigimiz feature dosyalarini
        SADECE eksik stepdefinition'lari bulmak ve method'larini olusturmak icin
        calistiriyorsak

        Constants.EXECUTION_DRY_RUN_PROPERTY_NAME : "true" yapariz

        dryRun degeri true iken Runner SADECE eksik adim kontrolu yapar
        Feature veya Scenario'lari calistirmaz
        eksik adim varsa failed deyip eksik adimlari verir
        eksik adim yoksa passed deyip calismayi bitirir

     */

//@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty, html:target/cucumber-report/HtmlReport.html")

@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty, json:target/json-reports/cucumberRapor.json")
    /*
        json formatında rapor istenildiği zaman, bu kullanılır. target içerisinde, json-reports çıktısı oluşturulur.
           .json uzantısına tıklanır --> yazılanların hepsi seçilir --> google'a jsonpath finder yazılır ve jasonpath.com'a gidilir
        --> kopyalanan kod input kısmına yapıştırılır ve $ işaretine kadar olan kısım silinir --> Evaluation Results kısmı yazılımcılarla paylaşılır.
     */

//@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty, junit:target/xml-report/cucumber.xml")
    /*
        xml isterlerse bu kullanılır.
     */


public class Runner {
    /*
        Cucumber'daki Runner Class'i icinde hicbir kod yazilmaz

        Gorevi TestNG'deki .xml dosyalari gibi
        toplu calistirmayi saglamaktir.

        Runner class'i gorevini
        kullanacagimiz notasyon'lar ile gerceklestirir.
     */
}
