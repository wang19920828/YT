package org.fh.general.ecom.common.enums;

import lombok.Getter;

/**
 * 返回消息信息
 */
@Getter
public enum OutEnum {
    SUCCESS("000000","业务处理成功"),
    WARN("999999","查询无业务数据"),
    FAIL("777777","接口处理异常"),
    INCONSISTENCIES("111111","数据不一致"),
    TIPS("222222","给前台弹框提示信息"),
    PWD("333333","密码错误"),
    REPETITION("444444","用户已存在！"),
    MUSTPARAMS("888888","缺少必要参数");

    private String  code;
    private String message;
    OutEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
}
