package org.fh.general.ecom.common.dto.product.user;

import lombok.Data;

/**
 * @Author huliping
 * @DATE 2018/9/21
 **/
@Data
public class OutputUserDetailDTO
{
    private Long userId;

    private  String  nickName;

    private String userImg;

    private String phone;

}
