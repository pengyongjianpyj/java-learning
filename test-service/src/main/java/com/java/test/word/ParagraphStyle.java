package com.java.test.word;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 段落样式
 * 2018年12月1日  下午4:20:05
 * @Author Huangxiaocong
 */

@Data
@NoArgsConstructor
public class ParagraphStyle {

    private boolean isSpace;  //是否缩进
    private String before;      //段前磅数
    private String after;  //段后磅数
    private String beforeLines;        //段前行数
    private String afterLines;        //段后行数
    private boolean isLine;        //是否间距
    private String line;    //间距距离
    //段落缩进信息
    private String firstLine;
    private String firstLineChar;
    private String hanging;
    private String hangingChar;
    private String right;
    private String rightChar;
    private String left;
    private String leftChar;

}