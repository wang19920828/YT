package org.fh.general.ecom.common.dto.basics.dictionary;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/29
 **/
@Data
public class OutputDictionaryListDTO {
    List<OutputDictionaryDetailDTO> list;
    PageInfo pageInfo;
}
