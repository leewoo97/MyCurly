package com.dldnwls.mycurly.infra.batch.jobconfig.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumCrawler {
    static String directoryPath = "C:/Users/dldnw/OneDrive/Desktop/semiproject/mycurly/mycurly/src/main/resources/product_details.csv";
    static String productLinkSelector = ".css-8bebpy.e1c07x488";
    static String nameSelector = ".css-13lg2xu.ezpe9l11";
    static String priceSelector = ".css-9pf1ze.e1q8tigr2";
    static String deliveryMethodSelector = "css-c02hqi.e6qx2kx1";
    static String gramSelector = "css-c02hqi.e6qx2kx1";


    public static void main(String[] args) {

        // 1. WebDriver 인스턴스 생성
        WebDriver driver = new ChromeDriver();

        try {
            // 2. 검색 페이지로 이동
            driver.get("https://www.kurly.com");

            // 3. 검색창 요소가 로드될 때까지 대기
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gnb_search")));

            // 4. 검색어 입력
            searchBox.sendKeys("우럭");

            // 5. 검색 버튼 클릭
            WebElement submitButton = driver.findElement(By.id("submit"));
            submitButton.click();

            // 6. 검색 결과 로드 대기
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productLinkSelector)));

            // 7. 상품 링크 요소들 가져오기
            List<WebElement> productLinks = driver.findElements(By.cssSelector(productLinkSelector));
            List<String> productUrls = new ArrayList<>();

            // 8. 각 상품 이름 텍스트 출력
            for (WebElement productLink : productLinks) {
                String productUrl = productLink.getAttribute("href");
                System.out.println("Product Url: " + productUrl);
                productUrls.add(productUrl);
            }

            // 9. CSV 파일에 저장
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath))) {
                writer.write("name,price,deliveryMethod,gram,link");
                writer.newLine();

                // 10. 각 상품을 순회하며 상세 정보 수집
                for (String productUrl : productUrls) {
                    driver.get(productUrl); // 상세 페이지로 이동

                    // 11. 상세 페이지의 특정 정보 추출
                    String name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nameSelector))).getText();
                    String price = driver.findElement(By.cssSelector(priceSelector)).getText().replace(",","");
                    String link = driver.getCurrentUrl();

                    List<WebElement> elements = driver.findElements(By.cssSelector(".css-c02hqi.e6qx2kx1"));
                    String deliveryMethod = elements.get(0).getText();
                    String gram = elements.get(4).getText();

                    // 12. CSV 파일에 기록
                    writer.write(name + "," + price + "," + deliveryMethod + "," + gram + "," + link);
                    writer.newLine();

                }
                System.out.println("상품 정보가 'product_details.csv' 파일에 저장되었습니다.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8. 브라우저 닫기
            driver.quit();
        }
    }
}
