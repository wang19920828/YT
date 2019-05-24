package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import org.fh.general.ecom.common.dto.order.projectPlan.ProjectPlanAddInputDTO;
import org.fh.general.ecom.common.dto.order.projectPlan.ProjectPlanListInputDTO;
import org.fh.general.ecom.common.dto.order.projectPlan.ProjectPlanListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.projectPlan.ProjectPlanListOutPO;
import org.fh.general.ecom.order.model.ProjectPlan;
import org.fh.general.ecom.order.dao.ProjectPlanDao;
import org.fh.general.ecom.order.service.ProjectPlanService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目分红方案表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Service
public class ProjectPlanServiceImpl extends ServiceImpl<ProjectPlanDao, ProjectPlan> implements ProjectPlanService {





    @Override
    public ProjectPlanListOutputDTO findPage(ProjectPlanListInputDTO dto)throws Exception {
        ProjectPlanListOutputDTO response=new ProjectPlanListOutputDTO();
        EntityWrapper<ProjectPlan> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        /*
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        List<ProjectPlan> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<ProjectPlanListOutPO>  listpo=new ArrayList<ProjectPlanListOutPO>();
        list.forEach((ProjectPlan temp) -> {
            ProjectPlanListOutPO po=new ProjectPlanListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public String addEntity(ProjectPlanAddInputDTO dto)  throws Exception{
        String code="";
        try {
            ProjectPlan entity=new ProjectPlan();
            BeanUtils.copyProperties(dto,entity );
            baseMapper.insert(entity);
            code= OutEnum.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            code=OutEnum.FAIL.getCode();
        }
        return code;
    }
}
