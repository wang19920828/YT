package org.fh.general.ecom.common.dto.order.orderMy;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.fh.general.ecom.common.po.order.orderMy.HasPlanPO;
import org.fh.general.ecom.common.po.order.orderMy.HasRedPO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class DetailHasProjectOutputDTO {


    private Long projectId;
    private String projectName;
    private String projectStatus;
    private String projectStatusName;
    private BigDecimal  investReal;
    private String imageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date successTime;
    private String logoUrl;
    private String logoName;//酒店
    private String stockName;//股权

    List<HasPlanPO> planList;
    List<HasRedPO> redList;

}
