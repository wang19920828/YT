package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/26 14:44
 * @Description:
 */
@Data
public class UserAddressListInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private Long userId;
    private Long id;
    /**
     * 是否默认地址 0否 1是
     */
    @TableField("is_host")
    private String isHost;
    /**
     * 联系人
     */
    private String name;
    /**
     * 用户性别0女1男2不限
     */
    private String sex;
    /**
     * 手机号
     */
    private String mobile;

    private String status;

}
