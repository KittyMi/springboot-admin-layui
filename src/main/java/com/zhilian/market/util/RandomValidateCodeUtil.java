package com.zhilian.market.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码工具类
 * @author lmin
 * @date 2018-5-29
 */
@Slf4j
public class RandomValidateCodeUtil {
    private static String randString = "01234567890123456789";//随机产生只有数字的字符串 private String
    private static int width = 380;// 图片宽
    private static int height = 40;// 图片高
    private static int lineSize = 40;// 干扰线数量
    private static int stringNum = 6;// 随机产生字符数量


    private static Random random = new Random();

    /**
     * 获得字体
     */
    private static Font getFont() {
        return new Font("Times New Roman", Font.ROMAN_BASELINE, 35);
    }

    /**
     * 获得颜色
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public static void getRandCode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);//图片大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 35));//字体大小
        g.setColor(getRandColor(110, 133));//字体颜色
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drawString(g, randomString, i);
        }
        //log.info(randomString);
        //将生成的随机字符串保存到session中
        session.removeAttribute("authCode");
        session.setAttribute("authCode", randomString);
        g.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "PNG", response.getOutputStream());
        } catch (Exception e) {
            log.error("将内存中的图片通过流动形式输出到客户端失败>>>>   ", e);
        }

    }

    /**
     * 绘制字符串
     */
    private static String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 35 * i, 30);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private static void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }
}
