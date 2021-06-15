import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;


public class parsePDF {
	public static Path pathClass = new Path();
	public static File f = null;
	// 다운로드 경로에서 파일 목록 가져오기
	public String[] readFilesFromDirectory() {		
		File path = new File(pathClass.getDownloadPath());
		File[] fileList = path.listFiles();
		
		if (fileList.length>0) {
			String[] fileArray = new String[fileList.length];
			for (int i = 0; i < fileList.length; i++) {
				fileArray[i] = fileList[i].getName();
			}
			return fileArray;
		} else {
			return null;
		}			
	}
	
	
	public void parseData(String fileList[]) throws IOException {
		File file = null;
		PDDocument pdfDoc = null;
		String dir = pathClass.getDownloadPath();
		String fileName = null;
		String text = null;
		String date = null;
		for (int i = 0; i < fileList.length; i++) {
			file = new File(dir + "\\" + fileList[i]);
			fileName = dir + "\\" + fileList[i];
			pdfDoc = PDDocument.load(file);
			text = new PDFTextStripper().getText(pdfDoc);
			date = getDate(text);
			tabula(date, fileName, pdfDoc);
		}
		pdfDoc.close();
	}
	
	public String getDate(String text) {
		String temp[] = text.split("\n");
		String date;
		if(temp[1].contains(")")) {
			date = temp[1].substring(temp[1].indexOf(") ")+1).trim();
		} else {
			date = temp[4].trim();
			
		}
		return date;
	}
	
	public void tabula(String date, String fileName, PDDocument pdfDoc) throws IOException {
		ObjectExtractor oe = new ObjectExtractor(pdfDoc);
		SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
		int totalpages = pdfDoc.getNumberOfPages();
		for (int p = 1; p <= totalpages; p++) {
			Page page = oe.extract(p);
			List<technology.tabula.Table> table = sea.extract(page);
			boolean isNone = false;	
			String gatherTime="", gatherPlace="", gatherNumber="", gatherJurisdiction="", bigo="";
			for (technology.tabula.Table tables : table) {
				List<List<RectangularTextContainer>> rows = tables.getRows();
				for (int i = 0; i < rows.size(); i++) {
					if (i > 0) {
						List<RectangularTextContainer> cells = rows.get(i);
						
						for (int j = 0; j < cells.size(); j++) {
							if (j == 1) {
								if (cells.get(1).getText().contains("집회")) {
									isNone = true;
									gatherPlace="주요 예정 집회 없음";
									writeCSV(date, gatherTime, gatherPlace, gatherNumber, gatherJurisdiction, bigo);
									break;
								}
							}
							switch (j) {
							case 0:
								gatherTime = cells.get(j).getText().replaceAll("\\r\\n|\\r|\\n|\\n\\r", "");
								break;
							case 1:
								gatherPlace = cells.get(j).getText().replaceAll("\\r\\n|\\r|\\n|\\n\\r", "");
								break;
							case 2:
								gatherNumber = cells.get(j).getText().replaceAll(",", "").replaceAll("\\r\\n|\\r|\\n|\\n\\r", "");
								break;
							case 3:
								gatherJurisdiction = cells.get(j).getText().replaceAll("\\r\\n|\\r|\\n|\\n\\r", "/");
								
								break;
							case 4:
								bigo = cells.get(j).getText();
								break;
							default :
								break;
							}
							if (gatherNumber.contains("없음")) {
								gatherNumber = "";
								gatherPlace = "주요 예정 집회 없음";
							}

							if (j == cells.size() - 1) {			
								writeCSV(date, gatherTime, gatherPlace, gatherNumber, gatherJurisdiction, bigo);
							}

						}
						
						if (isNone) {
							break;
						}

					}
				}
			}
		}

		pdfDoc.close();
	}
	
	public static void writeCSV(String date, String gatherTime,
							String gatherPlace, String gatherNumber,
							String gatherJurisdiction, String bigo) throws IOException {
		String resultPath = pathClass.getResultPath();
		String temp;
		String line;
		f = new File(resultPath);
		FileWriter fw = new FileWriter(f, true);
		BufferedReader br = new BufferedReader(new FileReader(resultPath)); 
		if ((line = br.readLine()) == null) {
			fw.write("날짜,집회시간,장소<행진로>,신고인원,관할서,비고\n");
		}
		temp = String.format("%s,%s,%s,%s,%s,%s\n", date, gatherTime,gatherPlace,
														gatherNumber, gatherJurisdiction, bigo);
		fw.write(temp);
		fw.flush();
		fw.close();
		br.close();
	}
	
	
}
