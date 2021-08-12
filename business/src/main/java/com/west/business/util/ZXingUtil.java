package com.west.business.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.west.business.consts.FileSuffixConsts;

import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * description: [二维码生成/解析工具类]
 * title: ZXingUtil
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/8/10
 */
public class ZXingUtil{
    //定义int类型的常量用于存放生成二维码时的类型
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    // 默认二维码高度
    private static final int DEFAULT_HEIGHT = 430;
    // 默认二维码宽度
    private static final int DEFAULT_WIDTH = 430;


    /**
     * description: [生成不带中心logo的，默认大小(430*430)的，后缀为jpeg的二维码]
     * @param   imagePath   文件存储路径
     * @param   content     二维码内容
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/8/10
     */
    public static void encodeimage(String imagePath , String content) throws IOException, WriterException {
        genQRCode(imagePath, FileSuffixConsts.JPEG, content, DEFAULT_WIDTH, DEFAULT_HEIGHT, null);
    }

        /**
         * description：[文字-->二维码  将文本变成二维数组，]
         * @param imagePath     生成图片的路径
         * @param imageSuffix   文件格式及后缀名
         * @param content       二维码内容
         * @param height        二维码图片高度
         * @param width         二维码图片宽度
         * @param logo          二维码中心图片文件路径(可控)
         */
    public static void genQRCode(String imagePath , String imageSuffix , String content , int width , int height,
                                   String logo) throws WriterException, IOException, WriterException {

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType , Object>();
        //容错率:L(7%)<M(15%)<Q(25%)<H(35%);H容错率最高
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //外边距
        hints.put(EncodeHintType.MARGIN, 1);

        /***
         * BarcodeFormat.QR_CODE:解析什么类型的文件：要解析的二维码的类型
         * content：解析文件的内容
         * width：生成二维码的宽
         * height:生成二维码的高
         * hints:涉及到加密用到的参数: 即 编码 和容错率
         */
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height , hints);
        //使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
        int matrixWidth = bitMatrix.getWidth();

        /**
         * 内存中的一张图片:是RenderedImage的子类，而RenderedImage是一个接口
         * 此时需要的图片是一个二维码-->需要一个boolean[][]数组存放二维码 --->Boolean数组是由BitMatrix产生的
         * BufferedImage.TYPE_INT_RGB : 表示生成图片的类型： 此处是RGB模式
         */
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //生成二维数组
        for(int x=0;x<matrixWidth;x++){
            for(int y=0 ; y<matrixWidth;y++){
                //二维坐标整个区域：画什么颜色
                img.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }

        //画log
        if(logo != null & logo != ""){
            img = logoImg(img, logo);
        }

        //将二维码图片转换成文件
        File file = new File(imagePath);
        //生成图片
        ImageIO.write(img, imageSuffix, file);

    }

    /**
     * 解密，将生成的二维码转换成文字
     * @param file:二维码文件
     * @throws Exception
     */
    /**
     * description: [将生成的二维码转换成文字]
     * @param  file 二维码文件
     * @return String 解析内容
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/8/10
     */
    public static String parseQRCode(File file) throws Exception{

        //首先判断文件是否存在
        if(!file.exists()){
            return "";
        }
        //将file转换成内存中的一张图片
        BufferedImage image = ImageIO.read(file);
        MultiFormatReader formatter = new MultiFormatReader();
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        //将图片的文字信息解析到result中
        Result result = formatter.decode(binaryBitmap);
        System.out.println(result.getText());
        return result.getText();
    }

    /**
     * 传logo和二维码，生成-->带logo的二维码
     * @param matrixImage :二维码
     * @param logo ： 中间的logo
     * @throws IOException
     */
    public static BufferedImage logoImg(BufferedImage matrixImage ,String logo) throws IOException{
        //在二维码上画logo:产生一个二维码画板
        Graphics2D g2 = matrixImage.createGraphics();
        //画logo,将String类型的logo图片存放入内存中;即 string-->BufferedImage
        BufferedImage logoImage = ImageIO.read(new File(logo));
        //获取二维码的高和宽
        int height = matrixImage.getHeight();
        int width = matrixImage.getWidth();
        /**
         * 纯log图片
         * logoImage：内存中的图片
         * 在二维码的高和宽的2/5,2/5开始画log,logo占用整个二维码的高和宽的1/5,1/5
         */
        g2.drawImage(logoImage,width*2/5, height*2/5, width*1/5, height*1/5, null);

        /**
         * 画白色的外边框
         * 产生一个画白色圆角正方形的画笔
         * BasicStroke.CAP_ROUND:画笔的圆滑程度，此处设置为圆滑
         * BasicStroke.JOIN_ROUND：在边与边的连接点的圆滑程度，此处设置为圆滑
         */
        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        //将画板和画笔关联起来
        g2.setStroke(stroke);

        /**
         * 画一个正方形
         * RoundRectangle2D是一个画长方形的类，folat是他的内部类
         */
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(width*2/5, height*2/5, width*1/5, height*1/5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        //设置为画白色
        g2.setColor(Color.WHITE);
        g2.draw(round);

        //画灰色的内边框，原理与画白色边框相同
        BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke2);

        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(width*2/5+2, height*2/5+2, width*1/5-4, height*1/5-4,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        //另一种设置灰色的方法：Color color = new Color(128,128,128);其中三个参数是 R G  B
        g2.setColor(Color.GRAY);
        g2.draw(round2);

        //释放内存
        g2.dispose();
        //刷新二维码
        matrixImage.flush();
        return matrixImage;
    }
}