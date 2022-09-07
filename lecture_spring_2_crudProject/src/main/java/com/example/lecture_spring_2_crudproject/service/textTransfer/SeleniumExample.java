package com.example.lecture_spring_2_crudproject.service.textTransfer;

import com.example.lecture_spring_2_crudproject.entity.data.ScrapingEntity;
import com.example.lecture_spring_2_crudproject.repository.data.ScrapingEntityRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//selenium : 동적인 데이터 수집 가능 (직접 브라우저를 통제해서 사람처럼 브라우저 작동을 하여 데이터 수집) : chromeDriver
//jsoup : httpRequest 사용해서 정적인 데이터(HTML, CSS..)를 수집
@Service
public class SeleniumExample {

    @Autowired
    ScrapingEntityRepository scrapingEntityRepository;
    //selenum 드라이버 다운
    private WebDriver driver;
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "C:/Oracle/chromedriver_win32/chromedriver.exe";
    //메서드 매개변수로 받아서 스크래핑 동작을 위한 변수 선언
    private String base_url;

    public void scraping() {
        //System.io : 개발한 자바 프로그램(런타임)에서 외부 프로그램을 작동하기 위한 객체
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        base_url = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13";
        driver.get(base_url);

        try {
            Thread.sleep(5000);
            //스크래핑할 페이지의 전체 데이터 출력
            System.out.println(driver.getPageSource());

            //tag search
            List<WebElement> elements_button = driver.findElements(By.tagName("button"));
            int checkNum = 0;
            for (WebElement e : elements_button) {
                List<WebElement> elements_span = e.findElements(By.tagName("span"));
                //Entity인스턴스를 만듬
                ScrapingEntity scrapingEntity = new ScrapingEntity();

                checkNum = 0;
                for (WebElement e_span : elements_span) {
                    if (!e_span.getText().equals("")) {
                        //Entity에 스크래핑을 통해 얻은 데이터를 넣기
                        if (checkNum == 0) {
                            scrapingEntity.setCity(e_span.getText());
                        } else if (checkNum == 1) {
                            scrapingEntity.setCovid19_confirmed(e_span.getText());
                        } else if (checkNum == 2) {
                            scrapingEntity.setIncrease_num(e_span.getText());
                        }
                        checkNum++;

                    }
                }
                //crudRepo 엔티티 저장
                scrapingEntityRepository.save(scrapingEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //공식문서에서는 close()가 아니라 quit() 권장
            driver.quit();

        }
    }
}
