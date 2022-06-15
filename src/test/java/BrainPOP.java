import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class BrainPOP {

    static WebDriver driver;

    @BeforeSuite
    public static WebDriver openBrowser() {

        //import webdrivermanager
        WebDriverManager.chromedriver().setup();
        return driver = new ChromeDriver();
    }

    //Part 1 : Various Login Flows

    @Test(priority = 1)
    public void SuccessfulLogIn() throws InterruptedException {
        //go to brainpop.com site
        driver.navigate().to("https://www.brainpop.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //click on Log In
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nli_log_in_button")));
        loginButton.click();

        //enter username and password into respective fields
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        usernameField.sendKeys("qatest2022");
        passwordField.sendKeys("brainp0p");

        //submit login attempt
        WebElement submitLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLogin")));
        submitLogin.click();

        //verify login complete to correct account
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("user_menu"))));
        String accountActual = account.getText();
        Assert.assertEquals(accountActual, "qatest2022");
        Thread.sleep(3000);
        //driver.quit();
    }

    @Test
    public void EmptyLogIn() throws InterruptedException {
        //expected error for blank login attempt: "Please type your username."
        String expectedMsg = "Please type your username.";

        //go to brainpop.com site
        driver.navigate().to("https://www.brainpop.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //click on Log In
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nli_log_in_button")));
        loginButton.click();

        //attempt login with no credentials entered
        WebElement submitLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLogin")));
        submitLogin.click();

        //retrieve error message
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-error")));
        String msg = error.getText();
        //Validate actual message against expected message
        Assert.assertEquals(msg,expectedMsg);
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void WrongUser() throws InterruptedException {
        //expected error message for wrong username supplied with correct pw : "The username and password you entered did not match."
        String expectedMsg = "The username and password you entered did not match.";

        //go to brainpop.com site
        driver.navigate().to("https://www.brainpop.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //click on Log In
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nli_log_in_button")));
        loginButton.click();

        //enter username and password into respective fields
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("login"))));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("password"))));
        usernameField.sendKeys("qa");
        passwordField.sendKeys("brainp0p");

        //attempt login with wrong username entered
        WebElement submitLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLogin")));
        submitLogin.click();

        //retrieve error message
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-error")));
        String actualMsg = error.getText();
        //Validate actual message against expected message
        Assert.assertEquals(actualMsg,expectedMsg);
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void WrongPW() throws InterruptedException {
        //expected error message for correct username supplied with incorrect pw : "The username and password you entered did not match."
        String expectedMsg = "The username and password you entered did not match.";

        //go to brainpop.com site
        driver.navigate().to("https://www.brainpop.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //click on Log In
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nli_log_in_button")));
        loginButton.click();

        //enter username and password into respective fields
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("login"))));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("password"))));
        usernameField.sendKeys("qatest2022");
        passwordField.sendKeys("brainpop");

        //attempt login with wrong password entered
        WebElement submitLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLogin")));
        submitLogin.click();

        //retrieve error message
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-error")));
        String actualMsg = error.getText();
        //Validate actual message against expected message
        Assert.assertEquals(actualMsg,expectedMsg);
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void SuccessfulLogInTrimUser() throws InterruptedException {
        //go to brainpop.com site
        driver.navigate().to("https://www.brainpop.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //click on Log In
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nli_log_in_button")));
        loginButton.click();

        //enter username and password into respective fields
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        usernameField.sendKeys(" qatest2022 ");
        passwordField.sendKeys("brainp0p");

        //submit login attempt with excess spaces on outer ends of username and correct password
        WebElement submitLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLogin")));
        submitLogin.click();

        //verify login complete to correct account
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("user_menu"))));
        String accountActual = account.getText();
        Assert.assertEquals(accountActual, "qatest2022");
        Thread.sleep(3000);
        driver.quit();
    }


    // Part 2: Challenge > DNA > Features


    //must be logged in to access search bar
    @Test(dependsOnMethods = "SuccessfulLogIn")
    public void Challenge() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //enter and submit "Challenge" in search input field
        //search field id requires search by partial tag because portion of the id is autogenerated each time page is loaded
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'search-input-')]")));
        searchField.sendKeys("Challenge");
        searchField.sendKeys(Keys.RETURN);
        //WebElement submitSearch =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search_button\"]")));
        //submitSearch.click();
    }

    //must be logged in and have searched "Challenge" to get results
    @Test(dependsOnMethods = {"SuccessfulLogIn", "Challenge"})
    public void VerifyResultsCount() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //determine the expected number of topics from top of page
        WebElement resultsExpected = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("topics_results_number")));
        String countText = resultsExpected.getText();
        int expectedCount = Integer.parseInt(countText);

        //verify that actual number of results matches number at top of page
        //find the list of all topics using the unique identifier for id containing "topic_result_"
        List<WebElement>topics = driver.findElements(By.xpath("//*[contains(@id, 'topic_result_')]"));
        //size of the list is equal to number of topics in the results
        int countTopics = topics.size();
        Assert.assertEquals(countTopics, expectedCount);
    }


    //new method to convert string time to int seconds
    public static int ConvertTime(String timeText) {
        String[] minSec = timeText.split(":");
        int mins = Integer.parseInt(minSec[0]);
        int sec = Integer.parseInt(minSec[1]);
        int minInSeconds = mins *60;
        return minInSeconds + sec;
    }

    @Test(dependsOnMethods = {"SuccessfulLogIn", "Challenge"})
        public void DNAPlayMovie() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //click DNA topic
        WebElement dna = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@href, '/dna/')]")));
        dna.click();

        //click Movie from features
        //WebElement movie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("feature_movie")));
        //movie.click();

        //or click Watch Movie button
        WebElement watchMovie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("play_movie")));
        watchMovie.click();

        //click play button
        WebElement playButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("play")));
        playButton.click(); //can also add verification to see if video is playing here or if pause button visible

        //verify end screen is visible;
        //Personal issue: Bug or lag; end screen not loading locally
        /* String durationText = driver.findElement(By.id("duration")).getText();
        System.out.println(durationText);
        int duration = ConvertTime(durationText);
        System.out.println(duration);
        WebDriverWait movieTimeWait = new WebDriverWait(driver, duration);
        WebElement postMovie = movieTimeWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("post_movie")));
*/
    }

    @Test(dependsOnMethods = {"SuccessfulLogIn", "Challenge", "DNAPlayMovie"})
    public void ClosedCapToggle() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //When captions are off:
        String expectedOff = "control_item button-wrapper medium";
        //When captions are on:
        String expectedOn = "control_item button-wrapper medium active";

        WebElement ccButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("caption")));

        //depending on the default state of the CC, the click method will perform a different action
        //if CC is off, the click should toggle CC on (by default CC is off)

        //Toggle on
        ccButton.click();
        String ccActualOn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("caption"))).getAttribute("class");
        Assert.assertEquals(ccActualOn, expectedOn);
        System.out.println("Toggled CC on successfully.");


        //Thread.sleep(3000);

        //if CC is on, the click should toggle CC off

        //Toggle off
        ccButton.click();
        String ccActualOff = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("caption"))).getAttribute("class");
        Assert.assertEquals(ccActualOff, expectedOff);
        System.out.println("Toggled CC off successfully.");

    }

    public static String FeatureSelectionFromMain(String feature) {
        //make this method only accept list of features
        List<String> acceptedFeatures = Arrays.asList(new String[]{"movie", "quiz", "challenge", "concept_map", "related_reading"});
        String feat = feature;
        //make feature entered not be case-sensitive by converting to all lowercase
        feat = feat.toLowerCase();
        if (acceptedFeatures.contains(feat)) {
            //define feature id
            String fid = "feature_" + feat;
            //click to select feature
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement featureButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fid)));
            featureButton.click();
            return fid;}
        else {System.out.println("Feature not accepted. Please select from: "+acceptedFeatures);}
        return feat;
    }


    @Test(dependsOnMethods = {"SuccessfulLogIn", "Challenge"})
    public void SelectFeature() {
        //click on a topic to reach a topic page, ex DNA
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dna = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@href, '/dna/')]")));
        dna.click();

        //select the feature button you'd like to click off the main topic page
        FeatureSelectionFromMain("related_reading");

    }


}
