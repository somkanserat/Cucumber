

  Feature: US1018 kullanici ozel bir gorev icin bekler

    Scenario: TC24 Kullanici webelementin enable olmasini bekleyebilmeli

      Given kullanici test data dosyasinda verilen "demoqaUrl" anasayfaya gider
      Then soldaki sekmelerden Elements'e tiklar
      Then acilan menuden Dynamic Properties'e tiklar
      And Will enable 5 seconds butonunun enable olmasini bekler
      Then butonun enable oldugunu test eder

    Scenario: TC25 Kullanici webelementin gorunur olmasini bekleyebilmeli

      Given kullanici test data dosyasinda verilen "demoqaUrl" anasayfaya gider
      Then soldaki sekmelerden Elements'e tiklar
      Then acilan menuden Dynamic Properties'e tiklar
      And Visible after 5 seconds butonunun visible olmasini bekler
      Then butonun visible oldugunu test eder