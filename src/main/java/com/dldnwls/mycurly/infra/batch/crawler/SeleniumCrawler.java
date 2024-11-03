package com.dldnwls.mycurly.infra.batch.crawler;

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
import java.util.List;

public class SeleniumCrawler {
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
            searchBox.sendKeys("연어");

            // 5. 검색 버튼 클릭
            WebElement submitButton = driver.findElement(By.id("submit"));
            submitButton.click();

            // 6. 검색 결과 로드 대기
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".css-1dry2r1.e1c07x485")));

            // 7. 상품 이름 텍스트 요소 찾기
            List<WebElement> productNames = driver.findElements(By.cssSelector(".css-1dry2r1.e1c07x485"));

            // 8. 각 상품 이름 텍스트 출력
            for (WebElement productName : productNames) {
                String text = productName.getText();
                System.out.println("Product Name: " + text);
            }

            // 9. 텍스트 파일에 쓰기
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("product_names.txt"))) {
                for (WebElement productName : productNames) {
                    String text = productName.getText();
                    writer.write("Product Name: " + text);
                    writer.newLine();  // 각 상품명 끝에 줄바꿈 추가
                }
                System.out.println("상품 이름이 'product_names.txt' 파일에 저장되었습니다.");
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
