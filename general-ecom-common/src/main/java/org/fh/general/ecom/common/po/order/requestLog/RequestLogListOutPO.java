package org.fh.general.ecom.common.po.order.requestLog;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
public class RequestLogListOutPO {

    private Long autoId;
    /**
     * 交易类型
     */
    private String transType;
    /**
     * 查询关键字
     */
    private String keyword;
    /**
     * 功能名称
     */
    private String functionName;
    /**
     * 请求参数
     */
    private String reqParam;
    /**
     * 返回结果
     */
    private String resResult;
    /**
     * 扩展字段1
     */
    private String ext1;
    /**
     * 扩展字段2
     */
    private String ext2;
    /**
     * 创建时间
     */
    private Date createTime;


}
