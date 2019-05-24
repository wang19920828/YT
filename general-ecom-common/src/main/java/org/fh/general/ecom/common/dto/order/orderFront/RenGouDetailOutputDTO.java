package org.fh.general.ecom.common.dto.order.orderFront;

import lombok.Data;
import org.fh.general.ecom.common.dto.basics.user.MyUserAddressPO;
import org.fh.general.ecom.common.dto.basics.user.UserAddressOutPutDTO;
import org.fh.general.ecom.common.po.order.goldTicket.MyGoldOutPO;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RenGouDetailOutputDTO {

    private Long productId;
    private String projectName;
    private Long planId;
    private String planName;
    private BigDecimal lessMoney;
    private Long limitNumber;
    private Long leaveNum;
    private Long totalNum;

    private BigDecimal orgPrice;
    private BigDecimal yuyuePrice;
    private BigDecimal allPrice;
    private String userName;
    private String email;
    private String idCard;
    private String coolNote;
    private String preNote;

    private MyUserAddressPO addressEn;
    private List<MyGoldOutPO> goldList;
}
