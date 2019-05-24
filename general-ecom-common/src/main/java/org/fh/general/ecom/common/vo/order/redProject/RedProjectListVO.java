package org.fh.general.ecom.common.vo.order.redProject;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RedProjectListVO {

    private Long id;
    private String projectName;
    private String projectId;
    private Long companyId;
    private String companyName;
    private BigDecimal amountReal;
    private BigDecimal amountLeiji;
    private BigDecimal amountExpected;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date currentTime;
    private String overStatus;
    private String shareStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date addTime;
    private String branch;
    private String exp1;
    private String exp2;



}
