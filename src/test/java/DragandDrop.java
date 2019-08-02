import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DragandDrop {

    public static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                        .withAppiumJS(new File("C:\\Users\\arman\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
                        .withLogFile(new File(System.getProperty("user.dir")+"\\src\\test\\log.txt"))
                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));

        service.start();

        File app = new File("C:\\Users\\arman\\IdeaProjects\\AppiumTesting\\apk\\drag.apk");


        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        //capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "Android");
        //capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "Android");

        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Thread.sleep(3000);


        //method 1 below

        //WebElement element1 = (WebElement) driver.findElements(By.id("com.mobeta.android.demodslv:id/activity_title")).get(1);
        //element1.click();

        //method 2 below

        String text = "Basic";
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+text+"\")").click();

        Thread.sleep(3000);

         WebDriverWait wait = new WebDriverWait(driver, 5);
         wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.mobeta.android.demodslv:id/text")));
       // System.out.println(driver.getPageSource());





        Thread.sleep(5000);
        driver.quit();
        service.stop();

    }

}
