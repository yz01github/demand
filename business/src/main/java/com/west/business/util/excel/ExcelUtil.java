package com.west.business.util.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.styler.ExcelExportStylerColorImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * description: [Excel相关工具类]
 * title: ExcelUtil
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/18
 */
public class ExcelUtil {

    /**
     * description: [默认导出Excel格式参数]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/14
     */
    public static ExportParams defaultExportParams(){
        ExportParams exportParams = new ExportParams();
        exportParams.setHeight((short) 7);
        exportParams.setTitleHeight((short)7);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.LIGHT_TURQUOISE.getIndex());
        exportParams.setStyle(ColorsStyle.class);
        return exportParams;
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }

    public static void defaultExport(List<?> list,Class<?> clazz, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,clazz,list);
        downLoadExcel(fileName, response, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
            workbook.write(response.getOutputStream());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        downLoadExcel(fileName, response, workbook);
    }

    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

}