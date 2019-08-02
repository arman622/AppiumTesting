

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestNativeElements {

    public static AndroidDriver<MobileElement> driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {


        new File("C:\\Users\\arman\\IdeaProjects\\AppiumTesting\\selendroid-test-app-0.17.0.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

            if(driver.isDeviceLocked()){
                driver.unlockDevice();
            }


        driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).click();

        Thread.sleep(3000);

        driver.hideKeyboard();

        System.out.println(driver.findElements(By.className("android.widget.Button")));

        /*List<MobileElement> btn = driver.findElements(By.className("android.widget.Button"));

            for (MobileElement button:btn){
                if (button.getAttribute("test").contains("Display test view")){
                    button.click();
                }
            }*/

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.openNotifications();
        Thread.sleep(3000);

        driver.findElement(By.id("com.android.systemui:id/dismiss_text")).click();

        driver.closeApp();

        driver.removeApp("io.selendroid.testapp");

        System.out.println("Status of App installation : " + driver.isAppInstalled("io.selendroid.testapp"));

        if(!driver.isAppInstalled("io.selendroid.testapp")){
            driver.installApp("C:\\Users\\arman\\IdeaProjects\\AppiumTesting\\selendroid-test-app-0.17.0.apk");
            //driver.startActivity("io.solenoid.testapp", ".HomeScreenActivity");//switching the appps -selendriods test app, message


        }

        Thread.sleep(3000);

        driver.quit();

    }
}
