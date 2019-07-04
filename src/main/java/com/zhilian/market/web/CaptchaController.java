package com.zhilian.market.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.zhilian.market.util.RandomValidateCodeUtil.getRandCode;

/**
 *
 * 获取验证码
 * @author lmin
 * @Date 2018-05-29
 *
 */
@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {
    @RequestMapping(value = "get", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public void getCaptcha(HttpServletResponse response, HttpServletRequest request)
    {
        response.setContentType("image/png");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        getRandCode(request, response);//输出验证码图片方法
    }

}
