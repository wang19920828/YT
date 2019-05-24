package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.FileNorms;
import org.fh.general.ecom.common.dto.basics.files.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-28
 */
public interface FileNormsService extends IService<FileNorms> {

    public OutputFileNormsDTO checkFileFlag(InputFileNormsDTO dto);

    public  OutputFileNormsListDTO findPage(InputFileNormsListDTO dto);

    public String addEntity(InputFileNormsAddDTO dto);

    public  OutputFileNormsDetailDTO findEntity(InputFileNormsDetaiDTO dto);

    public String updateDetail(InputFileNormsUpdateDTO dto);

    public String delFileNorms(InputFilesNormsDelDTO dto);
}
