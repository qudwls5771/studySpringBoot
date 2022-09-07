package textTransfer;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

//selenium : 동적인 데이터 수집 가능(직접 브라우저를 통제해서 사람처럼 브라우저 작동을 하여 데이터 수집) : chromeDriver
//jsoup : httpRequest를 사용해서 정적인 데이터(html, css .....)를 수집
public class seleniumExample {
    private WebDriver driver;

    //selenum 드라이버 다운
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "구글드라이브 다운받은 위치";
    //메소드 매개변수로 받아서 스크래핑 동작을 위한 변수 선언
    private String base_url;

    public void scraping(){
        //System.io : 개발한 자바 프로그램(런타임)에서 외부 프로그램을 작동하기 위한 객체
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        //네이버는 접속하기 힘듬
        base_url = "접속하고 싶은 URL";
        driver.get(base_url);

        try{
            Thread.sleep(5000);
            //스크래핑할 페이지의 전체 데이터 출력
            System.out.println(driver.getPageSource());

            WebElement element = driver.findElement(By.tagName("button"));
            List<WebElement> elements = element.findElements(By.tagName("span"));
            int checkNum = 0;

            for(WebElement e : elements){
                System.out.println(checkNum);
                System.out.println(e.getText());
                checkNum++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //공식문서에서는 close가 아니라 quit()권장
            driver.quit();
        }
    }


}
