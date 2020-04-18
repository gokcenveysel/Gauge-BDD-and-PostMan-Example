Functional Tests
=====================
Created by Veysel GOKCEN on 18.04.2020

Write automated functional/end-to-end tests for the following features of the IBAR website

Navigate
----------------
* Navigate to "https://www.ibar.az/"
     
Internationalisation
----------------
* Verify "Azərbaycan Beynəlxalq Bankı - Müasir Dövlət Bankınız" is correct
* Click on the ""th "/html/body/div[2]/div[2]/div[2]/footer/div[2]/div/ul[1]/li[1]/a"
* Wait for "3"
* Verify "International Bank of Azerbaijan - Your Modern State Bank" is correct

Global Quick Search
----------
* Click on the ""th "/html/body/div[2]/div[2]/div[2]/div/div/div/div/div[1]/div/input"
* Write "American express" on the ""th "/html/body/div[2]/div[2]/div[2]/div/div/div/div/div[1]/div/input"
* Wait for "30"
* Check on the ""th "/html/body/div[2]/div[2]/div[2]/div/div/div/div/div[3]/div[2]/div/div/div[2]/div/a"
* Check on the ""th "/html/body/div[2]/div[2]/div[2]/div/div/div/div/div[3]/div[2]/div/div/div[3]/div/a"

Form Input Validation
-----------
* Clear input the ""th "/html/body/div[2]/div[2]/div[2]/div/div/div/div/div[1]/div/input"
* Click on the ""th "/html/body/div[2]/div[2]/header/a[1]"
* Wait for "2"
* Click on the ""th "/html/body/div[2]/div[2]/div[2]/div/div/div/div/div[3]/div[1]/div/div[2]/div/a"
* Click on the ""th "//*[@id='submitButton']"
* Check on the ""th "//*[@id='loanexpress']/section/div[1]/div/span"
* Check on the ""th "//*[@id='loanexpress']/section/div[2]/div/span"
* Check on the ""th "//*[@id='loanexpress']/section/div[3]/div/span"
* Check on the ""th "//*[@id='loanexpress']/section/div[4]/div/span"
