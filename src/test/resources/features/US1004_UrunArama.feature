
Feature: US1004 kullanici istediği urunu aratir ve bulabildiğini test eder

  @paralel1
  Scenario: TC08 Kullanici istedigi urunu aratip, bulundugunu test edebilmeli

    Given kullanici testotomasyonu anasayfaya gider
    When arama kutusuna "dress" yazip aratir
    Then arama sonucunda urun bulunabildigini test eder
    And ilk urunu tiklar
    Then acilan sayfada urun isminde case sensivite olmadan "dress" bulundugunu test eder
    And senkronizasyon icin 2 saniye bekler