package org.fh.general.ecom.common.dto.basics.files;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.basics.files.OutputFileNormsPO;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/28
 **/
@Data
public class OutputFileNormsListDTO
{
    List<OutputFileNormsPO> list;

    PageInfo pageInfo;
}
