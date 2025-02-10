
  Feature: US1003 kullanici parametre olarak istedigi kelimeyi girip aratir
    @smoke
    Scenario: TC07 parametre ile urun aratilabilmeli

      # step içerisinde String değer ya da integer değer girilirse, cucumber bunu parametre olarak alacaktır.
      # Stepdefinitions class'ı içerisinde... (class'a git)
      Given kullanici testotomasyonu anasayfaya gider
      When arama kutusuna "baby" yazip aratir
      And senkronizasyon icin 3 saniye bekler
      Then arama sonucunda urun bulunabildigini test eder