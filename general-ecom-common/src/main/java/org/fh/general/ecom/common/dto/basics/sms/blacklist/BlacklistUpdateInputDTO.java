package org.fh.general.ecom.common.dto.basics.sms.blacklist;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/20 15:16
 * @Description:
 */
@Data
public class BlacklistUpdateInputDTO {
    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 手机端唯一标识
     */
    private String uuid;
    /**
     * 次数
     */
    private Integer num;
    /**
     * 状态：0、启用；1、禁用
     */
    private String status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;
    /**
     * 平台编号：1001-D5厨房；1002-美食工场 ；1003-爱炉火锅；1004-真真小吃
     */
    private String branch;
    /**
     * 平台名称：1001-D5厨房；1002-美食工场 ；1003-爱炉火锅；1004-真真小吃
     */
    @TableField("branch_name")
    private String branchName;
}
