package com.lc.p2p.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取指定内容的二维码到指定路径
 */
public class QRCode {


    @Test
    public void getRRCodeTest() throws WriterException, IOException {

        //通过向map中添加编码方式将矩阵对象中加入编码方式
        Map<EncodeHintType,Object> paramMap = new HashMap<EncodeHintType,Object>();
        paramMap.put(EncodeHintType.CHARACTER_SET,"utf-8");

        //创建矩阵对象
        String contents = "爱你呦娇猪";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE,200,200,paramMap);
        //设置二维码的保存路径
        String filePath = "G://";
        //设置文件名
        String fileName = "qrcode.jpg";
        //获取带路径对象
        Path path = FileSystems.getDefault().getPath(filePath,fileName);
        //将矩阵对象转换成图片到指定路径
        MatrixToImageWriter.writeToPath(bitMatrix,"jpg",path);
    }

    @Test
    public void getQRCode() throws WriterException, IOException {
        String s = "APP尚未上线";

        Map<EncodeHintType,String> hinMap = new HashMap<EncodeHintType, String>();
        hinMap.put(EncodeHintType.CHARACTER_SET,"utf-8");
        //创建矩阵对象
        BitMatrix bitMatrix = new MultiFormatWriter().encode(s,BarcodeFormat.QR_CODE,200,200,hinMap);
        //将矩阵对象输出到指定的路径中
        String fileName = "app.jpg";
        String filePath = "G://";
        Path path =  FileSystems.getDefault().getPath(filePath,fileName);

        MatrixToImageWriter.writeToPath(bitMatrix,"jpg",path);
    }

}
