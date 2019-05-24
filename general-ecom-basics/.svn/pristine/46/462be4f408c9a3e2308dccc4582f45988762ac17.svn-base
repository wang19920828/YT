package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.TeamInformationDao;
import org.fh.general.ecom.basics.model.TeamInformation;
import org.fh.general.ecom.basics.service.TeamInformationService;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationInputDTO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationOutputDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.help.TeamInformationListOutPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyy
 * @since 2018-09-20
 */
@Service
public class TeamInformationServiceImpl extends ServiceImpl<TeamInformationDao, TeamInformation> implements TeamInformationService {


    @Override
    public TeamInformationOutputDTO selectByPrimaryKey(Long id) {
        TeamInformationOutputDTO outputDTO=new TeamInformationOutputDTO();
        TeamInformation card=baseMapper.selectById(id);
        if(card!=null){
            BeanUtils.copyProperties(card,outputDTO);
        }
        return outputDTO;
    }

    @Override
    public List<TeamInformation> selectByTypeId(Long id) {
        List<TeamInformation> outputDTO=new ArrayList<>();
        List<TeamInformationOutputDTO> card= baseMapper.selectByTypeId(id);
        card.forEach((TeamInformationOutputDTO temp) -> {
            TeamInformation po = new TeamInformation();
            BeanUtils.copyProperties(temp, po);
            outputDTO.add(po);
        });
     /* for(int i=0;card.size()>i;i++){
            TeamInformationOutputDTO temp = new TeamInformationOutputDTO();
            TeamInformation po = new TeamInformation();
            temp=card.get(i);
            BeanUtils.copyProperties(temp,po);
            outputDTO.add(po);
            }*/

        return outputDTO;
    }


    @Override
    public String insertTeamInformation(TeamInformationInputDTO dto) {
        TeamInformation entity=new TeamInformation();
        BeanUtils.copyProperties(dto,entity);
        entity.setCreateTime(new Date());
        baseMapper.insert(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String updateTeamInformation(TeamInformationInputDTO dto) {
        TeamInformation entity=new TeamInformation();
        BeanUtils.copyProperties(dto,entity);
        entity.setCreateTime(new Date());
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String deleteByPrimaryKey(Long id) {
        baseMapper.deleteById(id);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public TeamInformationListOutputDTO findPage(TeamInformationListInputDTO dto) {
        TeamInformationListOutputDTO response=new TeamInformationListOutputDTO();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        EntityWrapper<TeamInformation> wrapper = new EntityWrapper<>();
        wrapper.eq("type",dto.getType());
        wrapper.eq("del",ComEnum.IsDelete.NORMAL.getValue());
        List<TeamInformation> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<TeamInformationListOutPO>  listpo=new ArrayList<TeamInformationListOutPO>();
        list.forEach((TeamInformation temp) -> {
            TeamInformationListOutPO po=new TeamInformationListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }
}
