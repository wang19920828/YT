package org.fh.general.ecom.product.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectProgrammeListDTO;
import org.fh.general.ecom.common.vo.product.project.PlanEntityVO;
import org.fh.general.ecom.common.vo.product.project.PlanNameVO;
import org.fh.general.ecom.common.po.product.project.ProjectProgrammeListOutputPO;
import org.fh.general.ecom.product.model.ProjectProgramme;
import org.fh.general.ecom.product.service.ProjectProgrammeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目权益方案 前端控制器
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
@RestController
public class ProjectProgrammeController {

    @Autowired
    private ProjectProgrammeService projectProgrammeService;

    @RequestMapping("RPRO0001")
    public List<PlanEntityVO> findProjectProgrammeList(@RequestBody Long projectId){
        List<PlanEntityVO> poList=new ArrayList<PlanEntityVO>();
        OutputProjectProgrammeListDTO pgDto=this.projectProgrammeService.findList(projectId);
        List<ProjectProgrammeListOutputPO> pgList= pgDto.getProjectProgrammeListOutputPOList();
       if(pgList!=null && pgList.size()>0){
           pgList.forEach((ProjectProgrammeListOutputPO temp)->{
               PlanEntityVO voEn=new PlanEntityVO();
               BeanUtils.copyProperties(temp,voEn);
               poList.add(voEn);
           });
       }
        return poList;
    }

    /**
     * 分红申请详情-查询项目方案最低年化收益率列表
     * @param projectId
     * @return
     */
    @RequestMapping("PRO0002")
    public BaseVO findPlanNameList(Long projectId){
        BaseVO baseVO=new BaseVO();
        try {
           List<PlanNameVO> poList=new ArrayList<PlanNameVO>();
            OutputProjectProgrammeListDTO pgDto=this.projectProgrammeService.findList(projectId);
            if(pgDto==null){
                baseVO.noData();
                return baseVO;
            }
            List<ProjectProgrammeListOutputPO> pgList= pgDto.getProjectProgrammeListOutputPOList();
            if(pgList!=null && pgList.size()>0){
                pgList.forEach((ProjectProgrammeListOutputPO temp)->{
                    PlanNameVO voEn=new PlanNameVO();
                    BeanUtils.copyProperties(temp,voEn);
                    poList.add(voEn);
                });
            }
            baseVO.setData(poList);
            baseVO.success();
        } catch (Exception e) {
            baseVO.exception();
            e.printStackTrace();
        }
        return baseVO;
    }


    /**
     * 根据主键查询方案
     * @param id
     * @return
     */
    @RequestMapping("PRO0003")
    public PlanEntityVO findProgrammeEntityById(@RequestBody  Long id){
        PlanEntityVO vo=new PlanEntityVO();
        ProjectProgramme entity=  this.projectProgrammeService.selectById(id);
        if(entity!=null) {
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }else {
            return null;
        }
    }

    @RequestMapping("WEBO0010")
    public BaseVO findWebProgrammeEntityById(  Long id){
        BaseVO baseVo=new BaseVO();
        PlanEntityVO vo=new PlanEntityVO();
        ProjectProgrammeListOutputPO entity=  this.projectProgrammeService.findById(id);
        BeanUtils.copyProperties(entity,vo);
        baseVo.success(vo);
        return baseVo;
    }

}
