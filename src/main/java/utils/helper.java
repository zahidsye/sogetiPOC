package utils;

import driverFactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class helper {
    private WebDriver driver;

    DriverFactory driverFactory;

    public helper(WebDriver driver) {
        this.driver = driver;
    }

    public String generateRandomString(int l) {
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

        StringBuilder s = new StringBuilder(l);
        int i;

        for (i = 0; i < l; i++) {
            //generating a random number using math.random()
            int ch = (int) (AlphaNumericStr.length() * Math.random());

            //adding Random character one by one at the end of s
            s.append(AlphaNumericStr.charAt(ch));
        }

        return s.toString();
    }

    public String generateRandomNumber(int l) {
        // a list of characters to choose from to form of a string
        String AlphaNumericStr = "0123456789";

        StringBuilder s = new StringBuilder(l);
        int i;

        for (i = 0; i < l; i++) {
            //generating a random number using math.random()
            int ch = (int) (AlphaNumericStr.length() * Math.random());

            //adding Random character one by one at the end of s
            s.append(AlphaNumericStr.charAt(ch));
        }

        return s.toString();
    }

    public Boolean getDDList() {
        Boolean status = null;
        hardWait(3000);
        List<WebElement> DDList = driver.findElements(By.xpath("//div[@class='country-list']//a"));
        System.out.println("List Sizze is: " + DDList.size());
        for (WebElement link : DDList) {
            String url = link.getAttribute("href");
            status = verifyLink(url);
        }
        return status;
    }

    public static boolean verifyLink(String url) {
        boolean status = false;
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage());
                status = true;

            } else {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
                status = false;
            }
        } catch (Exception e) {
            System.out.println(url + " - " + "is a broken link");
        }
        return status;

    }

    public void hardWait(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectDropDownByVisibleText_custom(WebElement element, String ddVisibleText) {
        Select s = new Select(element);
        s.selectByVisibleText(ddVisibleText);
    }

}
