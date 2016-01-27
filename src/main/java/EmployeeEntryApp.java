import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmployeeEntryApp {
	public static void main(String[] args) throws Exception {
		readFile(new File("new.xlsx"));
	}

	public static void readFile(File file) throws InvalidFormatException, IOException {
		XSSFWorkbook xw = new XSSFWorkbook(file);
		SXSSFWorkbook wb = new SXSSFWorkbook(xw);
		System.out.println(wb.getNumberOfSheets());
		wb.close();

	}

	public static void writeFile() throws IOException, FileNotFoundException {
		SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory,
													// exceeding rows will be
													// flushed to disk
		Sheet sh = wb.createSheet();
		for (int rownum = 0; rownum < 1000; rownum++) {
			Row row = sh.createRow(rownum);
			for (int cellnum = 0; cellnum < 10; cellnum++) {
				Cell cell = row.createCell(cellnum);
				String address = new CellReference(cell).formatAsString();
				cell.setCellValue(address);
			}

		}

		File dest = new File("new.xlsx");

		if (!dest.exists()) {
			dest.createNewFile();
		}

		FileOutputStream out = new FileOutputStream(dest);
		wb.write(out);
		out.close();

		// dispose of temporary files backing this workbook on disk
		wb.dispose();
	}

}
