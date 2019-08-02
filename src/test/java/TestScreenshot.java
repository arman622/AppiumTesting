import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestScreenshot {
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

    public static void main(String[] args) throws InterruptedException, IOException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                        .withAppiumJS(new File("C:\\Users\\arman\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
                        .withLogFile(new File(System.getProperty("user.dir")+"\\src\\test\\log.txt"))
                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));

        service.start();


        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        takeScreenShot();
        Thread.sleep(5000);
        driver.quit();
        service.stop();

    }

}
