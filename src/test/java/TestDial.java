import io.appium.java_client.MobileElement;
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

public class TestDial {

    public static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {


        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.dialer");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".DialtactsActivity");

        //OR another way of writing APP_PACKAGE and APP_ACTIVITY below

        //capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.dialer");
        //capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.apps.dialer.extensions.GoogleDialtactsActivity");





        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("com.google.android.dialer:id/floating_action_button")).click();




        driver.findElement(By.id("com.google.android.dialer:id/two")).click();
        driver.findElement(By.id("com.google.android.dialer:id/three")).click();
        driver.findElement(By.id("com.google.android.dialer:id/five")).click();
        driver.findElement(By.id("com.google.android.dialer:id/two")).click();
        driver.findElement(By.id("com.google.android.dialer:id/four")).click();
        driver.findElement(By.id("com.google.android.dialer:id/seven")).click();
        driver.findElement(By.id("com.google.android.dialer:id/dialpad_floating_action_button")).click();


        driver.findElement(By.id("com.google.android.dialer:id/incall_end_call")).click();

        driver.pressKey(new KeyEvent(AndroidKey.HOME));


        Thread.sleep(1000);

        driver.quit();

    }
}
