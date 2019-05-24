package org.fh.general.ecom.common.base;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.comm.SpecailPage;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.BeanUtils;


@Data
@Slf4j
public class PagingVO<T> implements Entity {
    /**
     * 返回信息对象
     */
    private MessageVO msg;

    /**
     * 业务数据对象
     */
    private PageData data;

    /**
     * 业务处理成功
     */
    public void success() {
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),
                OutEnum.SUCCESS.getMessage());
    }
    /**
     * 业务处理成功（带返回数据data,指定Message）
     *
     * @param msg
     */
    public void success(String msg) {
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),msg);
    }
    /**
     * 业务处理成功（带返回数据data）
     *
     * @param data
     */
    public void success(T data,PageInfo info) {
        if (info!=null){
            PageData pageData =new PageData();
            pageData.setInfo(data);
            PageVO pageVO=new PageVO();
            BeanUtils.copyProperties(info, pageVO);
            pageData.setPage(pageVO);
            this.data=pageData;
        }
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),
                OutEnum.SUCCESS.getMessage());

    }
    public void success(T data,SpecailPage info) {
        if (info!=null){

        }
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),
                OutEnum.SUCCESS.getMessage());

    }

    public void success(T data,PageInfo info,String msg) {

        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),msg);

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
                OutEnum.FAIL.getMessage());
        log.error("接口调用异常:{}",exception_msg);
    }
    /**
     * 接口发生异常
     */
    public void exception(String inputData,String exception_msg){
        this.msg = new MessageVO(
                OutEnum.FAIL.getCode(),
                OutEnum.FAIL.getMessage());
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



    public void  mustParam(){
        this.msg = new MessageVO(
                OutEnum.MUSTPARAMS.getCode(),
                OutEnum.MUSTPARAMS.getMessage());
    }

}
