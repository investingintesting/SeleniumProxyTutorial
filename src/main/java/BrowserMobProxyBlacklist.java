/**
 * Created by james on 24/04/2017.
 */

//Download - https://bmp.lightbody.net/
//I've downloaded and extracted to c:

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

public class BrowserMobProxyBlacklist {
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

        server.whitelistRequests("https?://*.*.lightwidget.com/.*,https?://*.*.facebook.com/.*".split(","), 200);
        //server.blacklistRequests("https?://.*(instagram.com)+.*", 404);

        driver.get("https://lightwidget.com/");
        driver.close();
        server.stop();
    }
}
