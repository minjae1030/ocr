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

		// ����û ������ ��ȸ ��� ����Ʈ
		String url = pathClass.getPoliceHomePage();

		// �� �������� �Խñ� ���� ���� ����
		int rows = 0;
		// �ݺ� Ƚ��
		int iter = 0;

		String pageNumber;

		try {
			// Ȩ������ ����
			driver.get(url);
			Thread.sleep(2000);
			// �ǵڷ� ����
			driver.findElement(By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/div[2]/a[11]"))
					.click();
			Thread.sleep(3000);
			while (true) {
				
				// ���̺� �� ���� ��������
				List row = driver.findElements(By.tagName("tr"));
				
				// th�� �� ��¥ �Խñ� ����
				rows = row.size() - 1;
				
				// �ݺ� ȸ��
				iter = rows;
				
				// �� ������ŭ Ŭ���Ͽ� �ݺ�
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
				
				// ������������ ���� ��ư�� �ִ��� Ȯ�� **********�ٸ����ã��..
				pageNumber = driver.findElement(By.xpath("//strong")).getText();
				
				System.out.println(pageNumber);
				
				if (Integer.parseInt(pageNumber) < 11) {
					System.out.println("������ư����");
				} else {
					driver.findElement(By.xpath("/html/body/form/div/section/div[1]/div/div[2]/div/div[2]/div[2]/a[2]")).click();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
