package readPDF;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path pathClass = new Path();
		String WEB_DRIVER_ID = "webdriver.chrome.driver";
		String WEB_DRIVER_PATH = "C:\\Users\\kmj\\Desktop\\selenium\\chromedriver.exe";
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		WebDriver driver;
		driver = new ChromeDriver();
		Document doc = null;

		// 경찰청 오늘의 집회 목록 사이트
		String url = pathClass.getPoliceHomePage();

		// 각 페이지의 게시글 개수 저장 변수
		int rows = 0;
		// 반복 횟수
		int iter = 0;

		String pageNumber;

		try {
			// 홈페이지 접속
			driver.get(url);
			Thread.sleep(2000);
			// 맨뒤로 가기
			driver.findElement(By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/div[2]/a[11]"))
					.click();
			Thread.sleep(3000);
			while (true) {
				
				// 테이블 행 갯수 가져오기
				List row = driver.findElements(By.tagName("tr"));
				
				// th를 뺀 진짜 게시글 갯수
				rows = row.size() - 1;
				
				// 반복 회수
				iter = rows;
				
				// 행 개수만큼 클릭하여 반복
				for (int i = 0; i < iter; i++) {
					driver.findElement(
							By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/table/tbody/tr[" + rows
									+ "]/td[2]/a"))
							.click();
					Thread.sleep(2500);
					driver.navigate().back();
					Thread.sleep(1000);
					driver.navigate().refresh();
					rows--;
				}
				
				// 이전페이지로 가기 버튼이 있는지 확인 **********다른방법찾자..
				pageNumber = driver.findElement(By.xpath("//strong")).getText();
				
				System.out.println(pageNumber);
				
				if (Integer.parseInt(pageNumber) < 11) {
					System.out.println("다음버튼없음");
				} else {
					driver.findElement(By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/div[2]/a[2]")).click();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
