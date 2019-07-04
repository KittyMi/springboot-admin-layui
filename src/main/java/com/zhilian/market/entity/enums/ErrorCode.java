package com.zhilian.market.entity.enums;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * 接口返回值定义
 * @author Andy_Lai
 * @date 2019-04-08
 */
public enum ErrorCode implements IErrorCode {
    NO_PARAMS(-1, "参数不能为空,请检查接口."),
    LOGIN_CODE_ERROR(-2, "验证码错误或者过期"),
    DAILY_LOOP(30000, "日报重复填写！"),
    ALREADY_LOGIN(10001, "用户已经登录"),
    NOT_LOGIN(10002, "用户未登录"),
    NO_FOUND_USER(10000, "用户找不到,或者用户密码错误"),
    NO_FOUND_DB_USER(10004, "用户不存在 ，请跳转注册界面！"),
    NO_CODE_FAILED(10005, "验证码下发失败,请重试！"),
    NO_CODE_WORRY(10006, "验证码错误或者失效！"),
    NO_CODE_WORRY_01(10007, "验证码不能为空"),
    PROJECT_NOT_FOUND(20000, "项目找不到！"),
    PROJECT_NOT_FOUND_SELF(20001, "报名失败，您还没有参加这个项目或者项目已失效！"),
    NO_ROLE_USER(10003, "您还没有权限登录后台系统，请联系系统管理员");

    private long code;
    private String msg;

    ErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
