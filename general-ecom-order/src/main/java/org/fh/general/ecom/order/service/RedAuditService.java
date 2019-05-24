package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.redAudit.RedAuditAddInputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditBgDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditListInputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditListOutputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.SureRedAuditInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectBgDetailOutputDTO;
import org.fh.general.ecom.order.model.RedAudit;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 分红审核表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface RedAuditService extends IService<RedAudit> {

    RedAuditListOutputDTO findPage(RedAuditListInputDTO dto)throws Exception;

    String addEntity(RedAuditAddInputDTO dto)throws Exception;

    RedAuditBgDetailOutputDTO detailBgRedAudit(Long id)throws Exception;

    String sureRedAudit(SureRedAuditInputDTO dto)throws Exception;

    void insertProjectOperLog(Long projectId,String redStatus);
}
