package org.fh.general.ecom.common.dto.basics.help.Adver;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.basics.help.AdverListOutPO;

import java.util.List;
@Data
public class AdverListOutputDTO {
    private List<AdverListOutPO> list;

    PageInfo pageInfo;

}
