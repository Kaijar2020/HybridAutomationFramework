package utilites;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String[][] get_LoginData() throws IOException {
        String path = ".\\testData\\Book1.xlsx";

        ExcelUtilites excelUtilites = new ExcelUtilites(path);

        int total_rows = excelUtilites.getRowCount("Sheet1");
        int total_cols = excelUtilites.getCellCount("Sheet1",1);

        //Created 2D array to store the data.
        String loginData [][] = new String[total_rows][total_cols];

        //Read data from Excel sheet
        for (int i=1;i<=total_rows;i++){
            for (int c =0 ;c<total_cols;c++){
                loginData[i-1][c]= excelUtilites.getCellData("Sheet1",i,c);
            }
        }
        return loginData;
    }
}
