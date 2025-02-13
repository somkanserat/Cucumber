package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DemoqaPage {

    public DemoqaPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//*[text()='Alerts']")
    public WebElement alertSekmesi;

    @FindBy(xpath = "//*[@id='timerAlertButton']")
    public WebElement timerAlertButton;

    @FindBy(xpath = "//*[text()='Elements']")
    public WebElement elementsSekmesi;

    @FindBy(xpath = "//*[text()='Dynamic Properties']")
    public WebElement dynamicPropertiesMenu;

    @FindBy(xpath = "//*[@id='enableAfter']")
    public WebElement enableAfter5SecondsButonu;

    @FindBy(id = "visibleAfter")
    public WebElement visibleAfter5Seconds;
}
