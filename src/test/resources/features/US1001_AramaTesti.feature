@deneme
Feature: US1001 Testotomasyonu phone arama testi

  # Senario --> Descriptions
  # Test adımları yazılır. Daha sonra sararan adımların üzerinde beklenip,
  # Creat step definition denir ve hangi class'da oluşturulacağı seçilir.

  # Run etmeden önce, File --> Build --> Build Project yapılır.

  @smoke @E2E1 @regression
  Scenario: TC01 Kullanici tesotomasyonu sitesinde phone bulabilmeli

    Given kullanici testotomasyonu anasayfaya gider
    When arama kutusuna phone yazip aratir
    Then arama sonucunda urun bulunabildigini test eder

  Scenario: TC02 Kullanici testotomasyonu sitesinde dress bulabilmeli
    Given kullanici testotomasyonu anasayfaya gider
    When arama kutusuna dress yazip aratir
    Then arama sonucunda urun bulunabildigini test eder

  Scenario: TC03 Kullanici testotomasyonu sitesinde java bulamamali
    Given kullanici testotomasyonu anasayfaya gider
    When arama kutusuna java yazip aratir
    Then arama sonucunda urun bulunamadigini test eder

