package org.fh.general.ecom.product.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.base.PagingExtensionVO;
import org.fh.general.ecom.common.dto.product.order.OutputOperUserListDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperAddDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperLogListDTO;
import org.fh.general.ecom.common.dto.product.projectlog.OutputProjectOperLogListDTO;
import org.fh.general.ecom.common.dto.product.projectlog.UpdateOperaLogInputDTO;
import org.fh.general.ecom.product.model.ProjectOperLog;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-20
 */
public interface ProjectOperLogService extends IService<ProjectOperLog> {

    public OutputProjectOperLogListDTO findPage(InputProjectOperLogListDTO dto);

    public String insertEntity(InputProjectOperAddDTO dto);

    public PagingExtensionVO findUserListPage(InputProjectOperLogListDTO dto);

    public OutputOperUserListDTO findCountUser(InputProjectOperLogListDTO dto);

    public String updateOperaLogRenGouStatus(UpdateOperaLogInputDTO dto);
}
