import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestFindingElements {

    public static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException{

        DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(MobileBy.AccessibilityId("visibleButtonTestCD")).click();
        //driver.findElement(MobileBy.xpath("//*[@content-desc='visibleButtonTestCD']")).click();
        //driver.findElement(MobileBy.xpath("//*[@text='Display text view']")).click();


        Thread.sleep(3000);
        driver.quit();
    }
}
