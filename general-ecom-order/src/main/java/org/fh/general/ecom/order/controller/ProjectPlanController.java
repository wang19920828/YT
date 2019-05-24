package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.projectPlan.ProjectPlanAddInputDTO;
import org.fh.general.ecom.common.dto.order.projectPlan.ProjectPlanListInputDTO;
import org.fh.general.ecom.common.dto.order.projectPlan.ProjectPlanListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.projectPlan.ProjectPlanListOutPO;
import org.fh.general.ecom.common.vo.order.projectPlan.ProjectPlanListVO;
import org.fh.general.ecom.order.service.ProjectPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目分红方案表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@RestController
public class ProjectPlanController {





    @Autowired
    private ProjectPlanService projectPlanService;


    /**
     * 分页列表
     * */
    @RequestMapping("PLA8005")
    public PagingVO findPage(ProjectPlanListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            ProjectPlanListOutputDTO dtoEntity= this.projectPlanService.findPage(dto);
            List<ProjectPlanListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<ProjectPlanListVO> listvo=new ArrayList<ProjectPlanListVO>();
            list.forEach((ProjectPlanListOutPO temp) -> {
                ProjectPlanListVO voEn=new ProjectPlanListVO();
                BeanUtils.copyProperties(temp,voEn);
                listvo.add(voEn);
            });

            pagingVO.success(listvo,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }



    /**
     * 添加
     * */
    @RequestMapping("PLA8001")
    public BaseVO addEntity(ProjectPlanAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.projectPlanService.addEntity(dto);
            if(!OutEnum.SUCCESS.getCode().equals(code)){
                baseVO.error(OutEnum.FAIL.getMessage());
                return  baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }


}
