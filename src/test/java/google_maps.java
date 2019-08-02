import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class google_maps {

    public static AndroidDriver driver;
    public static String destDir;
    public static DateFormat dateFormat;

    public static void takeScreenShot() throws IOException {

        //directory
        destDir = "screenshots";

        //capturing screenshot
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //set date
        dateFormat =new SimpleDateFormat("dd-MM-YYYY_hh_mm_ssaa");

        //create folder
        new File(destDir).mkdir();

        String destFile = dateFormat.format(new Date()) + ".png";

        FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                        .withAppiumJS(new File("C:\\Users\\arman\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js")) //the appuim server
                        .withLogFile(new File(System.getProperty("user.dir")+"\\src\\test\\log.txt"))// directing where the log.txt file
                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));

        service.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.apps.maps");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.maps.MapsActivity");

        //OR

        //capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.apps.gmm.taxi.library.BraintreeActivity");
        //capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.apps.gmm.appwidget.CreateDirectionsShortcutActivity");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Thread.sleep(5000);

        driver.findElement(By.id("com.google.android.apps.maps:id/search_omnibox_text_box")).click();

        Thread.sleep(1000);

        Actions action = new Actions(driver);
        action.sendKeys("458 Hillside Dr S, New Hyde Park, NY 11040").perform();
        //Thread.sleep(3000);

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        driver.findElement(By.id("com.google.android.apps.maps:id/placepage_directions_button")).click();


        Thread.sleep(5000);

        driver.findElementById("com.google.android.apps.maps:id/directions_startpoint_textbox").click();

        Thread.sleep(5000);

        action.sendKeys("Columbus, OH 43240").perform();

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        Thread.sleep(7000);

        takeScreenShot();


        MobileElement element0 = (MobileElement) driver.findElements(By.className("android.widget.TextView")).get(0);
        String text0 = element0.getText();

        MobileElement element1 = (MobileElement) driver.findElements(By.className("android.widget.TextView")).get(1);
        String text1 = element1.getText();


        System.out.println("It will take " + text0 + " to go from Ohio to New York.");
        System.out.println("The distance between Ohio and New York is " + text1 + ".");


        Thread.sleep(3000);

        driver.pressKey(new KeyEvent(AndroidKey.HOME));

        Thread.sleep(1000);

        driver.quit();
        service.stop();

    }
}
