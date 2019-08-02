import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


    public class TestNativeApp2 {

        public static AndroidDriver driver;

        public static void main(String[] args) throws MalformedURLException, InterruptedException {

            AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                            .withAppiumJS(new File("C:\\Users\\arman\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
                            .withLogFile(new File(System.getProperty("user.dir")+"\\src\\test\\log.txt"))
                            .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));

            service.start();


            File app = new File("C:\\Users\\arman\\IdeaProjects\\AppiumTesting\\apk\\selendroid.apk");

            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
            //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");

            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


            System.out.println(driver.findElements(By.className("android.widget.Button")));


            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Thread.sleep(3000);

            driver.quit();
            service.stop();

        }
    }

