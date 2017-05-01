/**
 * Created by james on 25/04/2017.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

/*
A proxy or proxy server is basically another computer which serves as a hub through which
internet requests are processed. By connecting through one of these servers, your computer
sends your requests to the proxy server which then processes your request and returns what you were wanting.
 */

//https://github.com/SeleniumHQ/selenium/wiki/DesiredCapabilities

/*
The desired capability is a series of key/value pairs that stores the browser properties
like browsername, browser version, the path of the browser driver in the system, etc.
to determine the behaviour of the browser at run time.

Desired capability can also be used to configure the driver instance of Selenium WebDriver.
We can configure driver instance like FirefoxDriver, ChromeDriver, InternetExplorerDriver by using desired capabilities.

 */
public class FirefoxProxy {

    public static void main(String [] args) {
        System.setProperty("webdriver.gecko.driver", "c:\\webdriver\\geckodriver.exe");
        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        proxy.setHttpProxy(PROXY)
                .setFtpProxy(PROXY)
                .setSslProxy(PROXY);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(PROXY, proxy);
        WebDriver driver = new FirefoxDriver(cap);
        driver.get("http://google.com");
    }


}
