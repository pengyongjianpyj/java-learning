package com.java.test.word;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/7/12 9:13 上午
 */
public class ExcelTest {
  public static void main(String[] args) throws Exception {
    String file = "/Users/pengyongjian/Downloads/批量上传线索模板-到CRM中心课程顾问 (10).xls";
    Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(file)));
    Sheet sheetAt = workbook.getSheetAt(0);
    Row row = sheetAt.getRow(9);
    Cell cell = row.getCell(16);
    Date date = null;
    CellType cellTypeEnum = cell.getCellTypeEnum();
    if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
      short format = cell.getCellStyle().getDataFormat();
      SimpleDateFormat sdf = null;
      if(format == 14 || format == 31 || format == 57 || format == 58){
        //日期
        sdf = new SimpleDateFormat("yyyy-MM-dd");
      }else if (format == 20 || format == 32) {
        //时间
        sdf = new SimpleDateFormat("HH:mm");
      }
      double value = cell.getNumericCellValue();
      date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
    }
    System.out.println(sheetAt.getSheetName());
    System.out.println(date);
  }
}
