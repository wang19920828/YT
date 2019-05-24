package org.fh.general.ecom.common.base;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.BeanUtils;

@Data
@Slf4j
public class PagingExtensionVO <T> implements Entity{

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


    public void success(T data,PageInfo info,T headEntity) {
        if (info!=null){
            PageData pageData =new PageData();
            pageData.setInfo(data);
            PageVO pageVO=new PageVO();
            BeanUtils.copyProperties(info, pageVO);
            pageData.setPage(pageVO);
            pageData.setHeadEntity(headEntity);
            this.data=pageData;
        }
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),
                OutEnum.SUCCESS.getMessage());

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
     * 接口发生异常
     */
    public void exception(){
        this.msg = new MessageVO(
                OutEnum.FAIL.getCode(),
                OutEnum.FAIL.getMessage());

    }


    public void  mustParam(){
        this.msg = new MessageVO(
                OutEnum.MUSTPARAMS.getCode(),
                OutEnum.MUSTPARAMS.getMessage());
    }

}
