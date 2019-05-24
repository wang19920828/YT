package org.fh.general.ecom.common.dto.basics.user;

import lombok.Data;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/17 11:10
 * @Description:
 */
@Data
public class UserListOutputDTO {
    private List<UserOutputDTO> list;

    PageInfo pageInfo;
}
