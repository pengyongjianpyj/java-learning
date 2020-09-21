package com.java.test.word.poi;

import com.java.test.word.WordUnits;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-09-17 14:40
 */
public class DocumentUtil {

    private static XWPFHelperTable xwpfHelperTable = new XWPFHelperTable();
    private static XWPFHelper xwpfHelper = new XWPFHelper();

    public static void saveDocument(XWPFDocument document, String savePath) throws IOException {
        OutputStream out = new FileOutputStream(savePath);
//        File tmpFile = FileUtils.createTmpFile(savePath);
//        OutputStream out = new FileOutputStream(tmpFile);
        document.write(out);
        out.close();
    }


    public static void createParagraph(XWPFDocument doc, String text) {
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
    }

    /**
     * 设置表格
     * @param document
     * @Author Huangxiaocong 2018年12月16日
     */
    public static void createTableParagraph(XWPFDocument document, String[][] arr) {
//        xwpfHelperTable.createTable(xdoc, rowSize, cellSize, isSetColWidth, colWidths)
        XWPFTable infoTable = document.createTable(arr.length, arr[0].length);
        xwpfHelperTable.setTableWidthAndHAlign(infoTable, "9072", STJc.CENTER);
        //合并表格
//        xwpfHelperTable.mergeCellsHorizontal(infoTable, 1, 1, 5);
//        xwpfHelperTable.mergeCellsVertically(infoTable, 0, 3, 6);
//        for(int col = 3; col < 7; col++) {
//            xwpfHelperTable.mergeCellsHorizontal(infoTable, col, 1, 5);
//        }
        //设置表格样式
        List<XWPFTableRow> rowList = infoTable.getRows();
        for(int i = 0; i < rowList.size(); i++) {
            XWPFTableRow infoTableRow = rowList.get(i);
            List<XWPFTableCell> cellList = infoTableRow.getTableCells();
            for(int j = 0; j < cellList.size(); j++) {
                XWPFTableCell xwpfTableCell = cellList.get(j);
                xwpfTableCell.setText(arr[i][j]);
                XWPFParagraph cellParagraph = xwpfTableCell.getParagraphArray(0);
                cellParagraph.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun cellParagraphRun = cellParagraph.createRun();
                cellParagraphRun.setFontSize(12);
                if(i % 2 != 0) {
                    cellParagraphRun.setBold(true);
                }
            }
        }
        xwpfHelperTable.setTableHeight(infoTable, 560, STVerticalJc.CENTER);
    }

    /**
     * 设置表格
     * @param document
     * @Author Huangxiaocong 2018年12月16日
     */
    public static void createTable(XWPFDocument document, String[][] arr) {
//        xwpfHelperTable.createTable(xdoc, rowSize, cellSize, isSetColWidth, colWidths)
        XWPFTable infoTable = document.createTable(arr.length, arr[0].length);
        //设置表格样式
        List<XWPFTableRow> rowList = infoTable.getRows();
        for(int i = 0; i < rowList.size(); i++) {
            XWPFTableRow infoTableRow = rowList.get(i);
            List<XWPFTableCell> cellList = infoTableRow.getTableCells();
            for(int j = 0; j < cellList.size(); j++) {
                XWPFTableCell xwpfTableCell = cellList.get(j);
                xwpfTableCell.setText(arr[i][j]);

            }
        }
    }

    public static void createPic(XWPFDocument document, String pic) throws Exception {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        InputStream is = new FileInputStream(pic);
        // 因为FileInputStream没有重写reset() 所有将流转为了byte数组
        byte[] bs = IOUtils.toByteArray(is);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bs));
        int width = WordUnits.pxToEMU(image.getWidth());
        int height = WordUnits.pxToEMU(image.getHeight());

        XWPFPicture xwpfPicture = run.addPicture(new ByteArrayInputStream(bs), Document.PICTURE_TYPE_PNG, "", width, height);
    }
}
