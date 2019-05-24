package org.fh.general.ecom.product.service;

import org.fh.general.ecom.common.dto.product.mongo.InputMongoDBProjectDTO;
import org.fh.general.ecom.common.dto.product.mongo.OutputMongoDBPageDTO;
import org.fh.general.ecom.common.dto.product.mongo.OutputMongoProjectDTO;
import org.fh.general.ecom.common.dto.product.web.InputWebProjectDetailDTO;

/**
 * @Author huliping
 * @DATE 2018/9/25
 **/
public interface ProjectMongoDBService {

    public void  updateOne(OutputMongoProjectDTO dto);

    public void  insertOne(OutputMongoProjectDTO dto);

    public OutputMongoDBPageDTO findListFromMongoDB(InputMongoDBProjectDTO dto);

    public void delOne(OutputMongoProjectDTO mongoProjectDTO);

    public  OutputMongoDBPageDTO findWebHotProjectPage(InputWebProjectDetailDTO dto);

}
