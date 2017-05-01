/**
 * Created by james on 24/04/2017.
 */

import java.io.File;
import java.io.IOException;

import net.lightbody.bmp.*;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;

import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

public class BrowserMobProxyPerformance {
    public static WebDriver driver;
    public static BrowserMobProxyServer server;

    public static void main(String [] args) {

        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");

        server = new BrowserMobProxyServer();
        server.start();
        int port = server.getPort();
        Proxy proxy = ClientUtil.createSeleniumProxy(server);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(PROXY, proxy);
        WebDriver driver = new ChromeDriver(cap);
        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        server.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        server.newHar();
        // set bandwidth limits
        server.setWriteBandwidthLimit(20000);
        server.setReadBandwidthLimit(20000);

        // open google.com
        driver.get("http://google.com");


        // get the HAR data
        Har har = server.getHar();
        try {
            har.writeTo(new File("c:\\tutorial\\browsermobproxy\\google.har"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.endHar();

        driver.close();
        server.stop();
    }
}
