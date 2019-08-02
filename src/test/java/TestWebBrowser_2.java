import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestWebBrowser_2 {

    public static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                        .withArgument(new ServerArgument(){
                            public String getArgument() {
                                return "--avd";
                            }
                        }, "Pixel_2_API_27")
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withAppiumJS(new File("C:\\Users\\arman\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
                .withLogFile(new File(System.getProperty("user.dir")+"\\src\\test\\log.txt"))
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));

        service.start();


        DesiredCapabilities capabilities  = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.get("http://www.google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //driver.findElement(By.name("q")).sendKeys("Hello Appium!!!");
        driver.findElement(By.name("q")).sendKeys("Hello Appium!!!");

        Thread.sleep(3000);

        driver.pressKey(new KeyEvent(AndroidKey.HOME));

        System.out.println(System.getProperty("user.dir"));


        driver.quit();
        service.stop();





    }


}
