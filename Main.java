import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		CrawlMethods cm = new CrawlMethods();
//		String url = cm.getURL();
		int pageNumber;
		int eachPageNumber = 2;
		Boolean isOver = false;
		
//		try {
//			cm.loopPost(url);
//			while (true) {
//				isOver = cm.enterPost();
//				if (isOver) {
//					System.out.println("��");
//					break;
//				}
//				pageNumber = cm.getPageNumber();
//				if (pageNumber % 10 == 0) {
//					if (pageNumber == 10) {
//						cm.TenNext();
//						eachPageNumber = 2;
//						continue;
//					} else {
//						cm.afterTenNext();
//						eachPageNumber = 2;
//						continue;
//					}
//				}
//				// ���� 10������ �̸��̸�
//				if (pageNumber < 10) {
//					cm.beforeTenClickNext(eachPageNumber);
//					eachPageNumber++;
//					Thread.sleep(2500);
//				} else {
//					cm.afterTenClickNext(eachPageNumber);
//					eachPageNumber++;
//					Thread.sleep(2500);
//				}
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try {
			parsePDF pp = new parsePDF();
			pp.parseData(pp.readFilesFromDirectory());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
