package com.west.business.util.excel;

import cn.afterturn.easypoi.excel.export.styler.ExcelExportStylerColorImpl;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * description: [自定义Excel风格]
 * title: ColorsStyle
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/22
 */
public class ColorsStyle  extends ExcelExportStylerColorImpl {
    public ColorsStyle(Workbook workbook) {
        super(workbook);
    }

    // 表header
    @Override
    public CellStyle getHeaderStyle(short headerColor) {
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFillForegroundColor(headerColor);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return titleStyle;
    }

    // 单行
    @Override
    public CellStyle stringNoneStyle(Workbook workbook, boolean isWarp) {
        CellStyle style = super.stringNoneStyle(workbook, isWarp);
        style.setAlignment(HorizontalAlignment.LEFT);// 左对齐
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        return style;
    }

    // 双行
    @Override
    public CellStyle stringSeptailStyle(Workbook workbook, boolean isWarp) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);// 左对齐
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        style.setDataFormat(STRING_FORMAT);
        if (isWarp) {
            style.setWrapText(true);
        }
        return style;
    }

    // 表头格式, 就是每列什么含义那一行
    @Override
    public CellStyle getTitleStyle(short color) {
        CellStyle titleStyle = workbook.createCellStyle();
        Font font = this.workbook.createFont();
        font.setBold(true);
        titleStyle.setFont(font);// 表头字体加粗
        titleStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setWrapText(true);

        // 颜色
        titleStyle.setFillForegroundColor(color);
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        // 有线边框
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setDataFormat(STRING_FORMAT);
        return titleStyle;
    }
}
