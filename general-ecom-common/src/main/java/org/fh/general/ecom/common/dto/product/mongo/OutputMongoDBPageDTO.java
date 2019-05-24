package org.fh.general.ecom.common.dto.product.mongo;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/10/11
 **/
@Data
public class OutputMongoDBPageDTO {

    List<OutputMongoProjectDTO> list;

    PageInfo pageInfo;
}
