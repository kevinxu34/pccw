package pccw.pccwtest;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import dev.brachtendorf.jimagehash.hash.Hash;
import dev.brachtendorf.jimagehash.hashAlgorithms.AverageHash;
import dev.brachtendorf.jimagehash.hashAlgorithms.HashingAlgorithm;

public class BaiduImageSearch {

    public static String readVisitIndex() {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
            return properties.getProperty("VISIT_RESULT");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean compareImage(String file1, String file2) throws IOException {
        File image1 = new File(file1);
        File image2 = new File(file2);

        HashingAlgorithm hasher = new AverageHash(64);

        //Generate the hash for each image
        Hash hash1 = hasher.hash(image1);
        Hash hash2 = hasher.hash(image2);

        //Compute a similarity score
        // Ranges between 0 - 1. The lower the more similar the images are.
        double similarityScore = hash1.normalizedHammingDistance(hash2);
        System.out.println("similarityScore: " + similarityScore);

        return similarityScore < 0.4d;
    }

    @Test
    public void imageSearch() throws MalformedURLException, IOException {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Set up Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://image.baidu.com/");

        driver.findElement(By.xpath("//img[@class='st_camera_off']")).click();
        File uploadFile = new File("src/test/resources/book.jpg");
        driver.findElement(By.xpath("//input[@id='stfile']")).sendKeys(uploadFile.getAbsolutePath());

        int visitResult = Integer.parseInt(readVisitIndex());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // in case of visitResult > 30, the searched images are lazy shown.
        if (visitResult > 30) {
            int scrollCount = visitResult / 30 + 1;
            for (int i =1; i <= scrollCount; i++) {
                int dataIndex = i * 30 -1;
                String xpath = "//a[@class='general-imgcol-item' and @data-index ='" + dataIndex + "']//img";
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                WebElement ele = driver.findElement(By.xpath(xpath));
                js.executeScript("arguments[0].scrollIntoView()", ele);
            }
        }

        int visitIndex = visitResult - 1;

        String xpath = "//a[@class='general-imgcol-item' and @data-index ='" + visitIndex + "']//img";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        WebElement imageElement = driver.findElement(By.xpath(xpath));

        js.executeScript("arguments[0].scrollIntoView()", imageElement);
        File screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
        File destination = new File("screenshot.png");
        screenshot.renameTo(destination);

        String imageUrl = imageElement.getAttribute("src");
        System.out.println("Download image from " + imageUrl);

        FileUtils.copyURLToFile(
                new URL(imageUrl),
                new File("searched_image.jpg"),
                3000,
                5000);

        driver.quit();

        Assertions.assertEquals(true, compareImage("src/test/resources/book.jpg", "searched_image.jpg"),
                "The original image and searched image are NOT similar.");
    }
}
