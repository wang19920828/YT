package org.fh.general.ecom.common.dto.basics.sms.phoneVacode;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/25 14:16
 * @Description:
 */
@Data
public class PhoneVacodeListOutputDTO {
    private List<PhoneVacodeOutputDTO> list;

    PageInfo pageInfo;
}
