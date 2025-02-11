
@smoke
Feature: US1008 Kullanici yanlis bilgilerle giris yapamaz

  Scenario: TC12 Kullanici yanlis password ile giris yapamamali

    Given kullanici test data dosyasinda verilen "toUrl" anasayfaya gider
    Then account butonuna basar
    And email olarak "toGecerliEmail" girer
    And password olarak "toGecersizPassword" girer
    Then signIn butonuna basar
    And sisteme giris yapamadigini test eder
    And senkronizasyon icin 3 saniye bekler

  Scenario: TC13 Kullanici yanlis email ile giris yapamamali
    Given kullanici test data dosyasinda verilen "toUrl" anasayfaya gider
    Then account butonuna basar
    And email olarak "toGecersizEmail" girer
    And password olarak "toGecerliPassword" girer
    Then signIn butonuna basar
    And sisteme giris yapamadigini test eder
    And senkronizasyon icin 3 saniye bekler

  Scenario: TC14 Kullanici yanlis email ve yanlis password ile giris yapamamali
    Given kullanici test data dosyasinda verilen "toUrl" anasayfaya gider
    Then account butonuna basar
    And email olarak "toGecersizEmail" girer
    And password olarak "toGecersizPassword" girer
    Then signIn butonuna basar
    And sisteme giris yapamadigini test eder
    And senkronizasyon icin 3 saniye bekler