package org.fh.general.ecom.common.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;


@Data
@Slf4j
public class BaseVO<T> implements Entity {

    /**
     * 返回信息对象
     */
    private MessageVO msg;

    /**
     * 业务数据对象
     */
    private T data;

    /**
     * 业务处理成功
     */
    public void success() {
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),
                OutEnum.SUCCESS.getMessage());
    }
    /**
     * 业务处理成功（带返回数据data）
     *
     * @param data
     */
    public void success(T data) {
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),
                OutEnum.SUCCESS.getMessage());
        this.data = data;
    }

    /**
     * 业务处理成功（带返回数据data,指定Message）
     *
     * @param msg
     */
/*    public void success(String msg) {
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),msg);
    }*/
    /**
     * 业务处理成功（指定Message）
     *
     * @param msg
     */
    public void success(T data,String msg) {
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),msg);
        this.data = data;
    }

    /**
     * 查无业务数据
     */
    public void noData(){
        this.msg = new MessageVO(
                OutEnum.WARN.getCode(),
                OutEnum.WARN.getMessage());
    }

    /**
     * 查无业务数据(指定Message）
     * @param msg
     */
    public void noData(String msg){
        this.msg = new MessageVO(
                OutEnum.WARN.getCode(),msg);
    }

    /**
     * 接口发生异常
     */
    public void exception(){
        this.msg = new MessageVO(
                OutEnum.FAIL.getCode(),
                OutEnum.FAIL.getMessage());

    }

    /**
     * 接口发生异常
     */
    public void exception(String exception_msg){
        this.msg = new MessageVO(
                OutEnum.FAIL.getCode(),
                exception_msg);
        log.error("接口调用异常:{}",exception_msg);
    }
    /**
     * 接口发生异常
     */
    public void exception(String inputData,String exception_msg){
        this.msg = new MessageVO(
                OutEnum.FAIL.getCode(),
                exception_msg);
        log.error("接口调用异常:{},输入数据为:{}",exception_msg,inputData);
    }

    /**
     * 前台提示
     * @param msg
     */
    public void error(String msg){
        this.msg = new MessageVO(
                OutEnum.FAIL.getCode(),msg);

    }

    /**
     * 数据不一致
     */
    public void  warn(){
        this.msg = new MessageVO(
                OutEnum.INCONSISTENCIES.getCode(),
                OutEnum.INCONSISTENCIES.getMessage());
    }

    /**
     * 数据不一致
     */
    public void  warn(String msg){
        this.msg = new MessageVO(
                OutEnum.INCONSISTENCIES.getCode(),msg);
    }

    /**
     * 错误
     */
    public static MessageVO failure(String code, String message) {
        log.error("接口调用异常:{},输入数据为:{}",message,code);
        return new MessageVO(OutEnum.FAIL.getCode(), message);
    }

    /**
     * 缺少必要参数
     */
    public void mustParam(){
        this.msg = new MessageVO(
                OutEnum.MUSTPARAMS.getCode(),
                OutEnum.MUSTPARAMS.getMessage());
    }
    //业务类型错误，前台展示给用户
    public void setBusAlert(String message) {
        if(StringUtils.isNotEmpty(message)){
            this.msg= new MessageVO(
                    OutEnum.TIPS.getCode(),
                    message);
        }else {
            this.msg = new MessageVO(
                    OutEnum.TIPS.getCode(),
                    OutEnum.TIPS.getMessage());
        }

    }


}
