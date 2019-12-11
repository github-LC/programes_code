package com.lc.p2p.web;

import com.lc.p2p.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成和验证图形验证码
 */
@Controller
public class CapachaController {

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    @RequestMapping(value="/jcaptcha/captcha")
    public void getCapacha(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        //获取验证码中随机生成验证码
        String capachaCode = getRandomCode(6);
        //创建图片缓存对象,设置该图片的高，宽和像素
        BufferedImage bufferedImage = new BufferedImage(Constants.WIDTH,Constants.HEIGHT,BufferedImage.TYPE_INT_BGR);
        //获取图片的画布对象
        Graphics graphics = bufferedImage.getGraphics();
        //设置画布的背景颜色
        graphics.setColor(Color.CYAN);
        //设置画布的填充区域
        graphics.fillRect(0,0,Constants.WIDTH,Constants.HEIGHT);
        //设置画布的边框区域
        /*graphics.drawRect(1,1,Constants.WIDTH-2,Constants.HEIGHT-2);*/
        //设置字体和颜色
        graphics.setColor(Color.pink);
        graphics.setFont(new Font("微软雅黑", Font.ITALIC, 32));
        //将随机的验证码填充到画布中
        graphics.drawString(capachaCode,10,38);
        //将生成的验证码存放到session中
        request.getSession().setAttribute(Constants.CAPTCHA,capachaCode);
        //创建输出字节数组流对象
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //将图片刷到输出流中
        ImageIO.write(bufferedImage,"jpeg",bos);
        //获取该输出流的字节数组
        byte[] captchaChallengeAsJpeg = bos.toByteArray();
        //设置响应头
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        //将图片写入到页面中
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(captchaChallengeAsJpeg);
        servletOutputStream.flush();
        servletOutputStream.close();

    }

    /**
     * 获取随机的验证码
     * @param count
     * @return
     */
    private String getRandomCode(int count){
        //存放随机生成的字符
        StringBuffer randomCode = new StringBuffer();
        //创建包含所有字母和数字的字符串数组
        String[] array =
                {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
                        "T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k"
                        ,"l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2"
                        ,"3","4","5","6","7","8","9"};

        //随机生成下标，并获取对应的字符串然后存进缓冲区中
        for(int i = 1;i<=count;i++){
            int random = (int)(Math.random()*62);
            //将该下标对应的值存进缓冲区中
            randomCode.append(array[random]);
        }

        return randomCode.toString();
    }

    /**
     * 验证验证码是否正确
     * @param request
     * @param captcha
     * @return
     */
    @RequestMapping(value="/loan/checkCaptcha")
    public @ResponseBody
    Object checkCaptcha(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value="captcha",required=true) String captcha){
        //存放参数
        Map<String,Object> resultMap = new HashMap<String,Object>();

        //获取session中的随机验证码
        String relCaptcha = (String) request.getSession().getAttribute(Constants.CAPTCHA);

        if(StringUtils.equalsIgnoreCase(captcha,relCaptcha)){
            resultMap.put(Constants.ERROR_MESSAGE,Constants.OK);
            return resultMap;
        }

        resultMap.put(Constants.ERROR_MESSAGE,"验证码输入错误");


        return resultMap;
    }
}
