package utilities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    private WebDriver driver;
    private File excelFile = new File(System.getProperty("user.dir") + "\\testData\\login.xlsx");
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private String loginSheetName = "loginDetails";

//    public ExcelReader(WebDriver driver) {
//        this.driver = driver;
//    }

    public XSSFSheet excelInitialization(String sheetName) throws IOException, InvalidFormatException {
        workbook = new XSSFWorkbook(excelFile);
        return sheet = workbook.getSheet(sheetName);
    }

    public List<String> getUserDetails(String userDetails) throws IOException, InvalidFormatException {
        List<String> li = new ArrayList<>();
        sheet = excelInitialization(loginSheetName);
        int row = sheet.getRow(0).getPhysicalNumberOfCells();
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        int length = sheet.getLastRowNum();
        String userName = null;
        String password = null;
        int j = 0;
        for (int i = 0; i <= length; i++) {
            String details = sheet.getRow(j).getCell(0).toString();
            if (details.equals(userDetails)) {
                userName = sheet.getRow(j).getCell(1).toString();
                password = sheet.getRow(j).getCell(2).toString();
                li.add(userName);
                li.add(password);
                break;
            } else {
                j = j + 1;
            }
        }
        closeExcel();
        System.out.println(li);
        return li;
    }


    public void closeExcel() throws IOException {
        workbook.close();
    }



//    static XSSFWorkbook wb = null;
//    static XSSFSheet sh = null;
//    static FileInputStream fis;
//    static FileOutputStream fos;
//
//
//    public static void  setExcelPath(String fName) throws IOException {
//        fis= new FileInputStream(new File(fName));
//        wb=new XSSFWorkbook(fis);
//    }
//
//    public static String getDataUsingColNum(String sheetName, int rowNum, int ColumnNum){
//        sh= wb.getSheet(sheetName);
//        return sh.getRow(rowNum).getCell(ColumnNum).getStringCellValue();
//    }
//
//    public static String getDataUsingColName(String sheetName, int rowNum, String columnName){
//        sh= wb.getSheet(sheetName);
//        Map<String,Integer> columns = new HashMap<>();
//        sh.getRow(0).forEach(cell -> {
//            columns.put(cell.getStringCellValue(),cell.getColumnIndex());
//        });
//        Cell cell = sh.getRow(rowNum).getCell((columns.get(columnName)));
//        return  cell.getStringCellValue();
//    }

}



