package ddt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DataProviderFunctions {

	@DataProvider(name = "LoginData")
	public Object[][] dataFromXLS() {
		Object[][] data = readDataFromXLS("test/resources/data/Credentials.xls", "NT", "NTStartEnd");
		return data;
	}

	@DataProvider(name = "CSV")
	public Iterator getDataFromExternalSourceCSV() throws IOException {
		Collection<String[]> retObjArr = getTestDataFromCSV("test\\resources\\data\\Datacsv.csv");
		return (retObjArr.iterator());
	}

	public String[][] readDataFromXLS(String xlFilePath, String sheetName, String marker) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			// Workbook class is provied by jxl.jar
			// WebDriver provided by Selenium
			// File is class provided by Java to read a physical file
			Sheet sheet = workbook.getSheet(sheetName);
			Cell tableStart = sheet.findCell(marker);

			int startRow, startCol, endRow, endCol, ci, cj;

			startRow = tableStart.getRow();// 2
			startCol = tableStart.getColumn();// 1

			Cell tableEnd = sheet.findCell(marker, startCol + 1, startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();// 7
			endCol = tableEnd.getColumn();// 5
			System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", " + "startCol=" + startCol
					+ ", endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];// 4, 3
			ci = 0; // array row
			// ci=0,i=3, j=3,cj=1
			for (int i = startRow + 1; i < endRow; i++, ci++) {// i represents
																// xls row
				cj = 0;// array column
				for (int j = startCol + 1; j < endCol; j++, cj++) {// j
																	// represents
																	// xls
																	// column
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file you specified does not exist");
		} catch (Exception e) {
			System.out.println("Please check if file path, sheet name and tag name are correct");
			e.toString();

		}

		return (tabArray);
	}

	public static Collection<String[]> getTestDataFromCSV(String fileName) throws IOException {
		List<String[]> records = new ArrayList<String[]>(); // arraylist of array of string is two dim arraylist

		String record;
		// FileReader is meant for reading streams of characters(reading text files)
		// FileReader myReader = new FileReader(fileName);
		// A BufferedReader object takes a FileReader object as
		// an input which contains all the necessary information
		// about the text file that needs to be read.
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		while ((record = file.readLine()) != null) {// record = "niche,thyself"
			String fields[] = record.split(",");// split() method is inside String class
			// fields[0] = "niche", and fields[1] = "thyself"
			records.add(fields);
		}
		file.close();
		return records;
	}

	@DataProvider(name = "DB")
	public Iterator getDataFromDB() throws Exception {
		Collection<String[]> retObjArr = getTestDataFromDB("Select user_name, password from login_data");
		return (retObjArr.iterator());
	}

	
	public static Collection<String[]> getTestDataFromDB(String sqlQuery) throws Exception {
		ArrayList<String[]> records = new ArrayList<String[]>();

		/*
		 * Windows - You need to install the MySQL DB - Login credentials - You need to
		 * know - create a DB - You need to create tables in your DB
		 * 
		 * Java ( - You need DB driver - jar file - Load the DB driver - Connect to the
		 * DB
		 * 
		 * 
		 * Common for all the DBs - Fire the SQL query - Read the result and pass it to
		 * Data provder in an two dim array
		 */

		// Connection, DriverManager,Statement, ResultSet, ResultSetMetaData

		/*
		 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");//Driver for MSAccess String
		 * String myDB = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" +
		 * mdbFile;//Database String Connection conn = DriverManager.getConnection(myDB,
		 * "", "");
		 */

		Class.forName("com.mysql.jdbc.Driver"); // This similar to providing chromedriver.exe, geckodriver.exe to your
												// Selenium code.
		String myDB = "jdbc:mysql://localhost:3306/niche"; // DB Connection String
		// String myDB = "jdbc:mysql://localhost:3306/paratus";//paratus is a DB name
		// which one needs to create
		// conn1=DriverManager.getConnection(myDB,"mysql","mysql");
		Connection conn = DriverManager.getConnection(myDB, "root", "");

		Statement stmt = null; // SQL Statements

		ResultSet rs = null; // DB data are returned inside ResultSet object

		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//
		rs = stmt.executeQuery(sqlQuery);// 5, 2
		ResultSetMetaData rsMetaData = rs.getMetaData();

		int cols = rsMetaData.getColumnCount();

		while (rs.next()) {

			String fields[] = new String[cols]; // creating array of columns
			int col = 0;
			for (int colIdx = 1; colIdx <= cols; colIdx++) {
				fields[col] = rs.getString(colIdx);
				col++;
			}
			records.add(fields);
		}
		rs.close();
		stmt.close();
		conn.close();

		return records;
	}

}
