

  Feature: US1009 kullanici fake bilgilerle kayit olusturmayi dener

    Scenario: TC15 Kullanici fake bilgilerle kayit olusturabilmeli

      Given kullanici test data dosyasinda verilen "faceUrl" anasayfaya gider
      And facebook'da yeni hesap olustur butonuna basar
      And senkronizasyon icin 1 saniye bekler
      When Faker class'indan bilgilerle facebook kayit formunu doldurur
      Then senkronizasyon icin 5 saniye bekler
      And facebook kayit ol tusuna basar
      Then senkronizasyon icin 12 saniye bekler