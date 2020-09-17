package com.java.test.word.poi;


import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestExportWord {

    public static void main(String[] args) throws Exception {
        ExportWord ew = new ExportWord();
        XWPFDocument document = ew.createXWPFDocument();
        List<List<Object>> list = new ArrayList<List<Object>>();

        List<Object> tempList = new ArrayList<Object>();
        tempList.add("姓名");
        tempList.add("黄xx");
        tempList.add("性别");
        tempList.add("男");
        tempList.add("出生日期");
        tempList.add("2018-10-10");
        list.add(tempList);
        tempList = new ArrayList<Object>();
        tempList.add("身份证号");
        tempList.add("36073xxxxxxxxxxx");
        list.add(tempList);
        tempList = new ArrayList<Object>();
        tempList.add("出生地");
        tempList.add("江西");
        tempList.add("名族");
        tempList.add("汉");
        tempList.add("婚否");
        tempList.add("否");
        list.add(tempList);
        tempList = new ArrayList<Object>();
        tempList.add("既往病史");
        tempList.add("无");
        list.add(tempList);

        Map<String, Object> dataList = new HashMap<String, Object>();
        dataList.put("TITLE", "个人体检表");
        dataList.put("TABLEDATA", list);
        ew.exportCheckWord(dataList, document, "/Users/pengyongjian/workCzwl/wordExport/expWordTest.docx");
        System.out.println("文档生成成功");
    }
}