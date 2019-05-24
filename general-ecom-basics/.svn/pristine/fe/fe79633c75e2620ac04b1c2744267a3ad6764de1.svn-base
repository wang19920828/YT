package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.TeamInformation;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationInputDTO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationOutputDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyy
 * @since 2018-09-20
 */
public interface TeamInformationService extends IService<TeamInformation> {

    public TeamInformationOutputDTO selectByPrimaryKey(Long id);

    public List<TeamInformation> selectByTypeId(Long id);

    public String insertTeamInformation(TeamInformationInputDTO dto);

    public String updateTeamInformation(TeamInformationInputDTO dto);

    public String deleteByPrimaryKey(Long id);

    public TeamInformationListOutputDTO findPage(TeamInformationListInputDTO dto);
}
