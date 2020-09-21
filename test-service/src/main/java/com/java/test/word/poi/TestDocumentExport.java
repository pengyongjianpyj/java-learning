package com.java.test.word.poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-09-17 14:46
 */
public class TestDocumentExport {

    String pathRead = "/Users/pengyongjian/workCzwl/wordExport/documentExport.docx";
    String pathWrite = "/Users/pengyongjian/workCzwl/wordExport/documentExport.docx";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        new TestDocumentExport().read();
    }

    private void read() {
        try {
            XWPFDocument doc = new XWPFDocument(new FileInputStream(pathRead));


//            List<XWPFParagraph> paragraphs = doc.getParagraphs();
//            for (XWPFParagraph paragraph : paragraphs) {
////                String paragraphText = paragraph.getParagraphText();
////                System.out.println(paragraphText);
//                for (XWPFRun run : paragraph.getRuns()) {
//                    System.out.println(run.getText(0));
//                }
//            }
//            String[][] arr = {
//                    new String[]{"1-1", "1-2", "1-3", "1-4"},
//                    new String[]{"2-1", "2-2", "2-3", "2-4"},
//                    new String[]{"3-1", "3-2", "3-3", "3-4"},
//                    new String[]{"4-1", "4-2", "4-3", "4-4"}
//            };
            DocumentUtil.createPic(doc, "/Users/pengyongjian/workCzwl/pics/logoTmp.png");
            DocumentUtil.createParagraph(doc, sdf.format(new Date()));
            DocumentUtil.saveDocument(doc, pathWrite);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
