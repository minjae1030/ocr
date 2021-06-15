import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CrawlMethods {
	public Path pathClass = new Path();
	public WebDriver driver;
	
	// URL ���� �� �ٿ�ε� ��� ���� �޼ҵ�
	public String getURL() {
		String downloadFilePath = pathClass.getDownloadPath();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilePath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		System.setProperty(pathClass.getWEB_DRIVER_ID(), pathClass.getWEB_DRIVER_PATH());
		driver = new ChromeDriver(options);
		String url = pathClass.getPoliceHomePage();
		return url;
	}

	// �Խñ� Ž�� �޼ҵ�
	public void loopPost(String url) throws InterruptedException {
		driver.get(url);
		Thread.sleep(3000);
	}
	
	// pdf �ֽ����� ���������� �ݺ��ϸ� pdf���� �����ϱ�
	public boolean enterPost() throws InterruptedException {
		int boardNumber = 1, rows = 0, iter = 0;
		String lastNumber = "";
		boolean isOver = false;
		List row = driver.findElements(By.tagName("tr"));
		rows = row.size() - 1;
		iter = rows;

		for (int i = 0; i < iter; i++) {
			lastNumber = driver
					.findElement(By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/table/tbody/tr["
							+ boardNumber + "]/td[1]"))
					.getText();
			
			if (Integer.parseInt(lastNumber) == 2167) {
				isOver = true;
			}
			
			driver.findElement(
					By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/table/tbody/tr["
							+ boardNumber + "]/td[2]/a")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/table/tbody/tr[3]/td/a[3]")).click();
			Thread.sleep(1500);
			
			driver.findElement(
					By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/div[1]/input")).click();
			boardNumber++;
			Thread.sleep(2000);
			
			if (isOver) {
				break;
			}		
		}	
		return isOver;
	}
	
	// ���� ������ ��ȣ ���
	public int getPageNumber() {
		String pageNumber_s = driver.findElement(By.xpath("//strong")).getText();
		int pageNumber = Integer.parseInt(pageNumber_s);
		return pageNumber;
	}
	
	public void TenNext() {
		driver.findElement(
				By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/div[2]/a[10]"))
				.click();
	}
	
	public void afterTenNext() {
		driver.findElement(
				By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/div[2]/a[12]"))
				.click();
	}
	
	public void beforeTenClickNext(int eachPageNumber) {
		driver.findElement(By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/div[2]/a["
				+ (eachPageNumber - 1) + "]")).click();
	}
	
	public void afterTenClickNext(int eachPageNumber) {
		driver.findElement(By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/div[2]/a["
				+ (eachPageNumber + 1) + "]")).click();
	}
}
