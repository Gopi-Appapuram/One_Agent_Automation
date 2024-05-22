package base;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseTest {

    protected WebDriver driver = null;
    String projectPath = System.getProperty("user.dir");
    protected HashMap<String, String> excelTestData = null;
    Properties envProp = null;
    int waitDuration = 30;
    BasePage basePage = new BasePage(driver);


    @Parameters({"browser", "env"})
    @BeforeSuite
    public void beforeSuite(@Optional("chrome") String browser, @Optional("Dev") String env) {
        System.out.println("Before suite");
        loadEnvironementData(env);
        //loadTestData();
    }

    private void loadEnvironementData(String environment) {
        try {
            FileReader reader = new FileReader(projectPath + "/src/test/resources/" + environment + ".properties");
            envProp = new Properties();
            envProp.load(reader);

            System.out.println(envProp.get("appurl"));

        } catch (Exception ex) {
            System.out.println("Error occured while reading environment file");
        }
    }


    public void loadTestData() {
        try {
            FileInputStream fs = new FileInputStream(projectPath + "\\testdata\\TestData.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fs);
            XSSFSheet sheet = wb.getSheet("LoginData");
            Iterator<Row> rows = sheet.iterator();

            excelTestData = new HashMap<String, String>();
            List<String> keys = new ArrayList<String>();
            List<String> values = new ArrayList<String>();

            boolean isHeader = true;

            while (rows.hasNext()) {
                XSSFRow eachRow = (XSSFRow) rows.next();
                Iterator<Cell> cells = eachRow.iterator();
                while (cells.hasNext()) {
                    XSSFCell eachCell = (XSSFCell) cells.next();
                    if (isHeader) {
                        keys.add(readCellValue(eachCell));
                    } else {
                        values.add(readCellValue(eachCell));
                    }
                }

                isHeader = false;
            }

            for (int index = 0; index < keys.size(); index++) {
                excelTestData.put(keys.get(index), values.get(index));
            }

        } catch (Exception ex) {
            System.out.println("Not able to read test data from excel");
        }
    }

    private String readCellValue(XSSFCell cell) {
        String cellValue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        cellValue = dateFormat.format(cell.getDateCellValue());
                    } else {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                case BLANK:
                    // Do nothing, leave cellValue as empty string
                    break;
            }
        }
        return cellValue;
    }

    @Test
    @Parameters({"browser", "env"})
    @BeforeMethod
    public void beforeMethod(Method method, @Optional("chrome") String browser, @Optional("Dev") String env) {
        String chromeDriverPath = projectPath + "\\Drivers\\chromedriver.exe";
        String fireFoxDriverPath = projectPath + "\\Drivers\\geckodriver.exe";
        String edgeDriverPath = projectPath + "\\Drivers\\msedgedriver.exe";
        switch (browser.toLowerCase()) {
            case "chrome":
                System.out.println(projectPath);
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", fireFoxDriverPath);
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.msedge.driver", edgeDriverPath);
        }
        driver.manage().window().maximize();
        driver.get(String.valueOf(envProp.get("appurl")));
        basePage.setImplicitWait(waitDuration);
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
