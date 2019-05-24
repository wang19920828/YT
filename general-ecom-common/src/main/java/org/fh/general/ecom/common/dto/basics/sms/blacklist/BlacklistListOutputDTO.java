package org.fh.general.ecom.common.dto.basics.sms.blacklist;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/20 16:12
 * @Description:
 */
@Data
public class BlacklistListOutputDTO {
    private List<BlacklistOutputDTO> list;

    PageInfo pageInfo;
}
