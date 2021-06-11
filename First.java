package readPDF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;

public class First {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		try (PDDocument document = PDDocument.load(new File("C:\\Users\\kmj\\Desktop\\210609(수) 인터넷집회.pdf"))) {
//			document.getClass();
//			
//			if (!document.isEncrypted()) {
//				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//				stripper.setSortByPosition(true);
//				
//				PDFTextStripper tStripper = new PDFTextStripper();
//				String pdfFileInText = tStripper.getText(document);
//				
//				String lines[] = pdfFileInText.split("\\r?\\n");
//				for(String line : lines) {
//					System.out.println(line);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//		String fileName = "C:\\Users\\kmj\\Desktop\\200815(토) 인터넷집회.pdf";
//		File file = new File(fileName);
//		PDDocument pdfDoc = PDDocument.load(file);
//		String text = new PDFTextStripper().getText(pdfDoc);
//		System.out.print(text);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
			
//		try {
//			File source = new File("C:\\Users\\kmj\\Desktop\\200815(토) 인터넷집회.pdf");
//			PDDocument pdfDoc = PDDocument.load(source);
//			PDFTextStripper pts = new PDFTextStripper() {
//				@Override protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
//					writeString(text.trim() + "/");
//					}
//				};
//				String text = pts.getText(pdfDoc);
//				System.out.println(text);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		String string = null;
        try {
            PDFParser pdfParser = new PDFParser(new File("C:\\Users\\kmj\\Desktop\\200815(토) 인터넷집회.pdf"));
            pdfParser.parse();
            PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            string = pdfTextStripper.getText(pdDocument);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };
        System.out.println(string);
	}
}
