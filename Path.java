
public class Path {
	private String policeHomePage = "https://www.smpa.go.kr/user/nd54882.do";
	private String WEB_DRIVER_PATH = "C:\\Users\\kmj\\Desktop\\selenium\\chromedriver.exe"; // ũ�ҵ���̹� ���
	private String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private String downloadPath = "C:\\Users\\kmj\\Desktop\\police"; // pdf �ٿ���� ���
	private String resultPath = "C:\\Users\\kmj\\Desktop\\��ȸ.csv"; // ��� csv ������ ���
	public String getPoliceHomePage() {
		return policeHomePage;
	}
	public void setPoliceHomePage(String policeHomePage) {
		this.policeHomePage = policeHomePage;
	}
	public String getWEB_DRIVER_PATH() {
		return WEB_DRIVER_PATH;
	}
	public void setWEB_DRIVER_PATH(String wEB_DRIVER_PATH) {
		WEB_DRIVER_PATH = wEB_DRIVER_PATH;
	}
	public String getWEB_DRIVER_ID() {
		return WEB_DRIVER_ID;
	}
	public void setWEB_DRIVER_ID(String wEB_DRIVER_ID) {
		WEB_DRIVER_ID = wEB_DRIVER_ID;
	}
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	public String getResultPath() {
		return resultPath;
	}
	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}
	
	
}
