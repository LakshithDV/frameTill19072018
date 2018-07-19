package utils;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;


public class ExcelParsing {
	static XSSFWorkbook workBook;
	static XSSFSheet sheet1;

	private String getCellValueAsString(XSSFCell cell,FormulaEvaluator formulaEvaluator) {
		if ((cell == null) || (cell.getCellType() == 3)) {
			return "";
		}
		if (formulaEvaluator.evaluate(cell).getCellType() == 5) {
			try {
				throw new Exception(
						"Error in formula within this cell! Error code: "
								+ cell.getErrorCellValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		DataFormatter dataFormatter = new DataFormatter();
		return dataFormatter.formatCellValue(formulaEvaluator.evaluateInCell(cell));
	}

	public Multimap<String, String> getValuesInDiffColumn(String filepath, int rowNum, String[] columnHeader, String sheetname){
		Multimap<String, String> detailsTableData = LinkedListMultimap.create();
		for(String columnHead:columnHeader){
			try {
				if(workBook==null) {
					workBook = openFileForReading(filepath);

				}
				XSSFSheet sheet1 = workBook.getSheet(sheetname);

				FormulaEvaluator formulaEvaluator = workBook.getCreationHelper().createFormulaEvaluator();
				XSSFRow row = sheet1.getRow(0);
				int columnNum=-1;
				int currentColumnNum = 0;
				for (; currentColumnNum < row.getLastCellNum(); ++currentColumnNum) {
					XSSFCell cell = row.getCell(currentColumnNum);
					String currentValue = getCellValueAsString(cell, formulaEvaluator);

					if (currentValue.equals(columnHead)) {
						columnNum = currentColumnNum;
						break;
					}
				}
				if (columnNum == -1) {
					System.out.println("Column doesnot exist");
				}else{
					row = sheet1.getRow(rowNum);
					XSSFCell cell = row.getCell(columnNum);
					detailsTableData.put(columnHead, getCellValueAsString(cell, formulaEvaluator));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return detailsTableData;
	}

	public int getTotalNumberOfRowCount(String filepath,String sheetname){
		workBook = openFileForReading(filepath);
		sheet1 = workBook.getSheet(sheetname);
		return sheet1.getLastRowNum();
	}

    public int getRowNum(String filePath,String key, int columnNum, int startRowNum,String sheetname) {
        if(workBook==null) {
            workBook = openFileForReading(filePath);

        }
        XSSFSheet sheet1 = workBook.getSheet(sheetname);

        FormulaEvaluator formulaEvaluator = workBook.getCreationHelper().createFormulaEvaluator();

        int currentRowNum = startRowNum;
        for (; currentRowNum <= sheet1.getLastRowNum(); ++currentRowNum) {
            XSSFRow row = sheet1.getRow(currentRowNum);
            XSSFCell cell = row.getCell(columnNum);
            String currentValue = getCellValueAsString(cell, formulaEvaluator);

            if (currentValue.toLowerCase().equals(key)) {
                return currentRowNum;
            }
        }

        return -1;
    }
	public XSSFWorkbook openFileForReading(String filePath) {

		XSSFWorkbook xssfWorkbook=null;
		try{
			FileInputStream file = new FileInputStream(new File(filePath));
			XSSFWorkbook workBook = new XSSFWorkbook(file);
			xssfWorkbook=workBook;
		}catch(Exception e){

		}
		return xssfWorkbook;
	}

    public int findColumnNumber(String filePath,String columnHeader,String sheetname){
        int columnNum=-1;
        try {
            if(workBook==null) {
                workBook = openFileForReading(filePath);

            }
            XSSFSheet sheet1 = workBook.getSheet(sheetname);
            FormulaEvaluator formulaEvaluator = workBook.getCreationHelper().createFormulaEvaluator();
            XSSFRow row = sheet1.getRow(0);
            int currentColumnNum = 0;
            for (; currentColumnNum < row.getLastCellNum(); ++currentColumnNum) {
                XSSFCell cell = row.getCell(currentColumnNum);
                String currentValue = getCellValueAsString(cell, formulaEvaluator);

                if (currentValue.equals(columnHeader)) {
                    columnNum = currentColumnNum;
                    break;
                }
            }
        } catch (Exception e) {
            columnNum=-1;
        }
        return columnNum;
    }

    public Map<String, String> getValueInDiffColumnCell(String filepath,int rowNum,String testdata){
        Map<String, String> mapping=new LinkedHashMap<>();
        if(workBook==null) {
            workBook = openFileForReading(filepath);

        }
            try {

                XSSFSheet testdatasheet = workBook.getSheet(testdata);
                FormulaEvaluator formulaEvaluator = workBook.getCreationHelper().createFormulaEvaluator();
                XSSFRow row = testdatasheet.getRow(0);
                XSSFRow cellrow = null;
                int currentColumnNum = 0;
                for (; currentColumnNum < row.getLastCellNum(); ++currentColumnNum) {
                    XSSFCell colvalue = row.getCell(currentColumnNum);
                    cellrow=testdatasheet.getRow(rowNum);
                    XSSFCell cellvalue = cellrow.getCell(currentColumnNum);
                    String colValue = getCellValueAsString(colvalue, formulaEvaluator);
                    mapping.put(colValue, getCellValueAsString(cellvalue, formulaEvaluator));
                }

            } catch (Exception e) {
                Assert.fail("Error at excel read function getValueInDiffColumnCell() "+e);
            }
        return mapping;
    }

    public Map<String, String> getValueInDiffColumns(String filepath,String testcaseid,String testdata) {

        int getColNumber=findColumnNumber(filepath,"TC_ID",testdata);
        if(getColNumber==-1){
            Assert.fail("Given column header TC_ID not exist in the sheet");
        }
        int getRoNum=getRowNum(filepath,testcaseid.toLowerCase(),getColNumber,1,testdata);
        if(getRoNum==-1){
            Assert.fail("Given testcase id not exist in the excel sheet column");
        }
        return getValueInDiffColumnCell(filepath,getRoNum,testdata);
    }



}
