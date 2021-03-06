package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzy
 * @since 2018-09-14
 */
@Data
@TableName("tb_user_address")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
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
    /**
     * 固定电话
     */
	private String tel;
    /**
     * 邮政编码
     */
	@TableField("post_code")
	private String postCode;
    /**
     * 详细地址
     */
	private String address;
    /**
     * 百度纬度
     */
	private String latitude;
    /**
     * 百度经度
     */
	private String longitude;
    /**
     * 用户上送定位点
     */
	@TableField("sign_area")
	private String signArea;
    /**
     * 城市编码
     */
	@TableField("district_code")
	private String districtCode;
    /**
     * 地区编码
     */
	@TableField("area_code")
	private String areaCode;

	private String status;

}
