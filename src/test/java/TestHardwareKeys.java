import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestHardwareKeys {

    public static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {


        DesiredCapabilities capabilities = new DesiredCapabilities();


        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



        /*driver.findElement(By.id("io.selendroid.testapp:id/buttonStartWebview")).click();
        Thread.sleep(5000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(5000);*/
        driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).click();

        /*
        driver.pressKey(new KeyEvent(AndroidKey.A));
        driver.pressKey(new KeyEvent(AndroidKey.P));
        driver.pressKey(new KeyEvent(AndroidKey.P));
        driver.pressKey(new KeyEvent(AndroidKey.I));
        driver.pressKey(new KeyEvent(AndroidKey.U));
        driver.pressKey(new KeyEvent(AndroidKey.M));

        Thread.sleep(1000);

        driver.pressKey(new KeyEvent(AndroidKey.HOME));

         */

        Actions action = new Actions(driver);
        action.sendKeys("Appium").perform();


       /* driver.toggleWifi();
        try {
            driver.toggleAirplaneMode();
        }catch(Throwable t){
            System.out.println("Airplane mode active");
        }*/
        Thread.sleep(3000);

        driver.quit();
    }

}
