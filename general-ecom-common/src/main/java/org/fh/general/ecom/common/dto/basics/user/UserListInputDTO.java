package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/17 11:06
 * @Description:
 */
@Data
public class UserListInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
    /**
     * 平台：1001-D5厨房 1002-美食工场 1003-爱炉火锅 1004-真真小吃
     */
    private String branch;
    /**
     * 渠道:1-线下,2-微信(公众号),3-Android（app）,4-IOS（app）,5-小程序,6-Web,7-Wap
     */
    private String channel;
    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 是否认证：0未认证，1待审核，2审核未通过，3通过
     */
    @TableField("is_name")
    private String isName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeStart;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeEnd;
}
