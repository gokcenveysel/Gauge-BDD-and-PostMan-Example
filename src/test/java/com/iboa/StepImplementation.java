package com.iboa;

import com.thoughtworks.gauge.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

public class StepImplementation {

    //region Base Elements
    protected static WebDriver driver;
    protected final static Logger log = Logger.getLogger(StepImplementation.class);
    protected WebElement webelement;
    protected static WebDriverWait wait;
    protected static String originalWindow;
    //endregion

    //region Constructor
    public StepImplementation(){
    }
    //endregion

    //region -------  Helper Methods  -------
    public WebElement Locator(String index,String locatorValue) {
        webelement = null;
        List<WebElement> listObj = null;
        String[] locatorValueArray = locatorValue.split("=");
        log.info("locator Identifier : " + locatorValueArray[0] +"");

        if(index.trim().isEmpty() || index.trim() == null)
        {
            index = "0";
        }
        try
        {
            //Eğer ekranda birden fazla aynı element olduğu düşünülmüyorsa ya da index verilmemişse
            if(Integer.parseInt(index) == 0)
            {
                if(locatorValue.startsWith("/"))
                {
                    log.info("Search the element by XPATH");
                    webelement = driver.findElement(By.xpath(locatorValue));
                }
                else
                {
                    if(locatorValueArray[0].startsWith("id")) //id:ornek
                    {
                        log.info("Search the element by ID");
                        webelement = driver.findElement(By.id(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("css")) //css:ornek
                    {
                        log.info("Search the element by CSS");
                        webelement = driver.findElement(By.cssSelector(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("name"))//name:ornek
                    {
                        log.info("Search the element by NAME");
                        webelement = driver.findElement(By.name(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("partial"))//partial:ornek
                    {
                        log.info("Search the element by PARTIALLINK");
                        webelement = driver.findElement(By.partialLinkText(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("link"))//link:ornek
                    {
                        log.info("Search the element by LINKTEXT");
                        webelement = driver.findElement(By.linkText(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("tag"))//tag:ornek
                    {
                        log.info("Search the element by TAGNAME");
                        webelement = driver.findElement(By.tagName(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("class"))//class:ornek
                    {
                        log.info("Search the element by CLASS");
                        webelement = driver.findElement(By.className(locatorValueArray[1]));
                    }
                    else
                    {
                        log.info("Search the element by CONTAINS");
                        webelement = driver.findElement(By.xpath("//*[" +
                                "contains(id,'"+locatorValue+"') or " +
                                "contains(text(),'"+locatorValue+"') or " +
                                "contains(@value,'"+locatorValue+"') or " +
                                "contains(css,'"+locatorValue+"') or " +
                                "contains(@class,'"+locatorValue+ "')]"));
                    }
                }
            }
            else
            {
                if(locatorValue.startsWith("/"))
                {
                    log.info("Search the element by XPATH");
                    listObj = driver.findElements(By.xpath(locatorValueArray[1]));
                }
                else
                {
                    if(locatorValueArray[0].startsWith("id"))
                    {
                        log.info("Search the element by ID");
                        listObj = driver.findElements(By.id(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("css"))
                    {
                        log.info("Search the element by CSS");
                        listObj = driver.findElements(By.cssSelector(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("name"))
                    {
                        log.info("Search the element by NAME");
                        listObj = driver.findElements(By.name(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("partial"))
                    {
                        log.info("Search the element by PARTIALLINK");
                        listObj = driver.findElements(By.partialLinkText(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("link"))
                    {
                        log.info("Search the element by LINKTEXT");
                        listObj = driver.findElements(By.linkText(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("tag"))
                    {
                        log.info("Search the element by TAGNAME");
                        listObj = driver.findElements(By.tagName(locatorValueArray[1]));
                    }
                    else if(locatorValueArray[0].startsWith("class")) {
                        log.info("Search the element by CLASS");
                        listObj = driver.findElements(By.className(locatorValueArray[1]));
                    }
                    else {
                        log.info("Search the element by CONTAINS");
                        listObj = driver.findElements(By.xpath("//*[" +
                                "contains(id,'"+locatorValue+"') or " +
                                "contains(text(),'"+locatorValue+"') or " +
                                "contains(@value,'"+locatorValue+"') or " +
                                "contains(css,'"+locatorValue+"') or " +
                                "contains(@class,'"+locatorValue+ "')]"));
                    }
                }
                webelement = listObj.get(Integer.parseInt(index)-1);
            }
        }
        catch (NotFoundException ex)
        {
            log.warn(locatorValue + " The element not found");
        }
        catch (ElementNotVisibleException visibleex)
        {
            log.warn(locatorValue + " The element not visible on screen");
        }
        catch(ElementNotInteractableException interactex)
        {
            log.warn(locatorValue + " The element not interactable");
        }
        catch (ElementNotSelectableException selectex)
        {
            log.warn(locatorValue + " The element not selectable");
        }
        return webelement;
    }

    public void highLighterMethod(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 1px solid #00ff08 ;');", element);
    }

    public static void cleanhighLightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].style.border=''", element, "");
    }
    //endregion

    //region -------  General Methods  -------
    @Step("Wait for <second>")
    public void setWait(String second) throws InterruptedException {

        TimeUnit.SECONDS.sleep(Integer.parseInt(second));
    }

    @Step("Navigate to <URL>")
    public void GoToURL(String URL) throws Exception {
        try
        {
            driver.get(URL);
        }
        catch (Exception e)
        {
            throw new Exception("Could not navigate to the specified URL");
        }
    }

    @Step("Click on the <index>th <element> if there is")
    public void isDisplayedStandardClick(String index,String locatorValue) {
        try
        {
            webelement = Locator(index,locatorValue);
            if(webelement !=null && webelement.isDisplayed())
            {
                webelement.click();
            }
            else
            {
                log.info("The element was not found on the screen.");
            }

        }
        catch (Exception e)
        {
            log.warn("The element could not be clicked! " + e.getMessage());
        }
    }

    @Step("Choose the <radioOrCheckbox>")
    public void StandardRadioCheckClick(String locatorValue) {
        try
        {
            driver.findElement(By.xpath("//*[contains(text(),'"+locatorValue+"')]/../input")).click();
        }
        catch (Exception e)
        {
            log.warn("The element could not be selected! " + e.getMessage());
        }
    }

    @Step("Click on the <index>th <element>")
    public void StandardClick(String index,String locatorValue) {
        try
        {
            webelement = Locator(index,locatorValue);
            highLighterMethod(webelement);
            webelement.click();
        }
        catch (Exception e)
        {
            log.warn("The element could not be clicked! " + e.getMessage());
        }
    }

    @Step("Select <selectedOption> on the <index>th <combobox>")
    public void StandartSelectCombobox(String selectedOption, String index, String locatorValue) throws Exception {
        webelement = Locator(index,locatorValue);
        Select drpApplication = new Select(webelement);
        try
        {
            drpApplication.selectByVisibleText(selectedOption);
        }
        catch (Exception e)
        {
            log.warn("No item to select was found.");
        }
    }

    @Step("Write <text> on the <index>th <element>")
    public void StandartTypeKeys(String entryType, String index, String locatorValue) {
        try
        {
            webelement = Locator(index,locatorValue);
            highLighterMethod(webelement);
            for (int i = 0; i < entryType.length(); i++){
                Thread.sleep(300);
                webelement.sendKeys(Character.toString(entryType.charAt(i)));
            }
            cleanhighLightElement(webelement);
        }
        catch (Exception e)
        {
            log.warn("Text could not be written to the element!");
        }
    }

    @Step("Clear input the <index>th <element>")
    public void ClearInput(String index, String locatorValue) {

        try
        {
            webelement = Locator(index,locatorValue);
            highLighterMethod(webelement);
            webelement.sendKeys(Keys.CONTROL + "a");
            webelement.sendKeys(Keys.DELETE);
            cleanhighLightElement(webelement);
        }
        catch (Exception e)
        {
            log.warn("The element could not be clear!");
        }
    }

    @Step("Switch to a new tab or window")
    public void SwitcherTabsAndWindow() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        String mainWindow = driver.getWindowHandle();
        Set<String> set = driver.getWindowHandles();
        Iterator<String> windowsIterator = set.iterator();

        while (windowsIterator.hasNext()) {
            String childWindow = windowsIterator.next();
            if (!mainWindow.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                if(!driver.switchTo().window(childWindow).getTitle().equalsIgnoreCase("Error"))
                {
                    log.info("Selenium on " +driver.switchTo().window(childWindow).getTitle() + "page");
                }
                else
                {
                    throw new Exception("Application is crash");
                }

            }
        }
    }

    @Step("Verify <page> is correct")
    public void VerifyPage(String page) throws Exception {

        log.info("The current page is " + driver.getTitle());

        if(driver.getTitle().contains(page))
        {
            log.info("Page is correct");
        }
        else {
            throw  new Exception("The page is different");
        }

    }

    @Step("Check on the <index>th <element>")
    public void CheckObjectIsDisplayed(String index, String locatorValue) throws Exception {
        webelement = null;
        webelement = Locator(index,locatorValue);

        if(webelement == null || !webelement.isDisplayed())
        {
            throw new Exception("Sorry, The element not displayed");
        }
        else
        {
            log.info("The element is display");
        }
    }

    //endregion

    @AfterSuite
    public void closeDriver(){
        driver.quit();
    }

    @BeforeSuite
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("silent");
        option.addArguments("disable-popup-blocking");
        option.addArguments("ignore-certificate-errors");
        option.addArguments("disable-translate");
        option.addArguments("--start-maximized");
        option.setExperimentalOption("useAutomationExtension", false);
        option.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        originalWindow = driver.getWindowHandle();
        wait = new WebDriverWait(driver,60);
    }
}
