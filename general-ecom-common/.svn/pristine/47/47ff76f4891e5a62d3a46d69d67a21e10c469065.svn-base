package org.fh.general.ecom.common.dto.order.orderMy;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.fh.general.ecom.common.base.MessageVO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.orderMy.HavePayAppEntityPO;
import org.fh.general.ecom.common.po.order.orderMy.HavePayRenGouEntityPO;
import org.fh.general.ecom.common.po.order.orderMy.HavePayYuYueEntityPO;
import org.fh.general.ecom.common.po.order.orderMy.UnPayAppEntityPO;
import org.fh.general.ecom.common.po.order.orderMy.UnPayRenGouEntityPO;
import org.fh.general.ecom.common.po.order.orderMy.UnPayYuYueEntityPO;

import java.math.BigDecimal;

@Data
public class HavePayOrderOutputDTO {

    private String orderSn;
    private String projectName;
    private String projectStatus;
    private String projectStatusName;
    private String planName;
    private String buyNum;
    private BigDecimal unitPrice;
    private String proImg;
    private String projectId;
    private Long planId;

    private HavePayYuYueEntityPO yuYueEntity;
    private HavePayAppEntityPO appEntity;
    private HavePayRenGouEntityPO renGouEntity;

    private String acceptName;
    private String acceptPhone;
    private String address;


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
