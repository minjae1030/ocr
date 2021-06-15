
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CrawlMethods cm = new CrawlMethods();
		String url = cm.getURL();
		int pageNumber;
		int eachPageNumber = 2;
		Boolean isOver = false;

		try {
			cm.loopPost(url);
			while (true) {
				isOver = cm.enterPost();
				if (isOver) {
					System.out.println("끝");
					break;
				}
				pageNumber = cm.getPageNumber();
				if (pageNumber % 10 == 0) {
					if (pageNumber == 10) {
						cm.TenNext();
						eachPageNumber = 2;
						continue;
					} else {
						cm.afterTenNext();
						eachPageNumber = 2;
						continue;
					}
				}
				// 만약 10페이지 미만이면
				if (pageNumber < 10) {
					cm.beforeTenClickNext(eachPageNumber);
					eachPageNumber++;
					Thread.sleep(2500);
				} else {
					cm.afterTenClickNext(eachPageNumber);
					eachPageNumber++;
					Thread.sleep(2500);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
