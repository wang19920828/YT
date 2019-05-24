package org.fh.general.ecom.common.dto.order.order;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.fh.general.ecom.common.base.MessageVO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.orderLog.OrderLogListOutPO;
import org.fh.general.ecom.common.po.order.orderLog.OrderLogPO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderBgDetailOutputDTO {

    //基本信息
    private String orderSn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date addTime;
    private String orderType;
    private String userName;
    private String userPhone;
    private String projectName;
    private String projectStatus;
    private String orderStatus;

    //交易明细
    private String  planName;
    private String num;
    private BigDecimal price;
    private BigDecimal appointmentAmount;
    private BigDecimal havePay;//已支付预约款/元
    private BigDecimal shouldAmount;
    private BigDecimal shareAll;
    private BigDecimal allPrice;
    private String payName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Data payTime;
    private String flowNo;

    //收货信息
    private String acceptName;
    private String acceptPhone;
    private String address;

    //退款等订单异常
    List<OrderLogListOutPO> logList;


    private MessageVO msg;
    private T data;
    public void success() {
        this.msg = new MessageVO(
                OutEnum.SUCCESS.getCode(),
                OutEnum.SUCCESS.getMessage());
    }
    public void exception(String exception_msg){
        this.msg = new MessageVO(
                OutEnum.FAIL.getCode(),
                exception_msg);
    }

}
