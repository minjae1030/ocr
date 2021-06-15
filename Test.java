import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.google.common.collect.Table;


import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;



public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	    PDDocument pd = PDDocument.load(new File("C:\\Users\\kmj\\Desktop\\200815(토) 인터넷집회.pdf"));

	    int totalPages = pd.getNumberOfPages();
	    System.out.println("Total Pages in Document: "+totalPages);

	    ObjectExtractor oe = new ObjectExtractor(pd);
	    SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
	    Page page = oe.extract(1);

	    // extract text from the table after detecting
	    List<technology.tabula.Table> table = sea.extract(page);
	    for(technology.tabula.Table tables: table) {
	        List<List<RectangularTextContainer>> rows = tables.getRows();

	        for(int i=0; i<rows.size(); i++) {
	        	System.out.println((i+1) + " : 행");
	            List<RectangularTextContainer> cells = rows.get(i);

	            for(int j=0; j<cells.size(); j++) {
	            	String a = cells.get(j).getText().replaceAll(",", "");
	                System.out.print(a + "|");
	                if (j == cells.size() -1) {
	                	System.out.println("");
	                }
	            }
	           
	           // System.out.println();
	        }
	    }
	}
}
