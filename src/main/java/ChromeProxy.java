import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

/**
 * Created by james on 25/04/2017.
 */
public class ChromeProxy {

    public static void main(String [] args) {
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        proxy.setHttpProxy(PROXY)
                .setFtpProxy(PROXY)
                .setSslProxy(PROXY);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(PROXY, proxy);
        WebDriver driver = new ChromeDriver(cap);
        driver.get("http://google.com");
    }


}
