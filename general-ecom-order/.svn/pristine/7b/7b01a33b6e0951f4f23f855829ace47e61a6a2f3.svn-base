package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.projectFormula.ProjectFormulaAddInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.ProjectFormulaListInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.ProjectFormulaListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.projectFormula.ProjectFormulaListOutPO;
import org.fh.general.ecom.common.vo.order.projectFormula.ProjectFormulaListVO;
import org.fh.general.ecom.order.service.ProjectFormulaService;
import org.fh.general.ecom.order.service.impl.TimedTaskShareRed;
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
 * 分红试算/日历详情表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@RestController
public class ProjectFormulaController {

    @Autowired
    private TimedTaskShareRed timedTaskShareRed;
    @Autowired
    private ProjectFormulaService projectFormulaService;


    /**
     * 分页列表
     * */
    @RequestMapping("PRJ8005")
    public PagingVO findPage(ProjectFormulaListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            ProjectFormulaListOutputDTO dtoEntity= this.projectFormulaService.findPage(dto);
            List<ProjectFormulaListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<ProjectFormulaListVO> listvo=new ArrayList<ProjectFormulaListVO>();
            list.forEach((ProjectFormulaListOutPO temp) -> {
                ProjectFormulaListVO voEn=new ProjectFormulaListVO();
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
    @RequestMapping("PRJ8001")
    public BaseVO addEntity(ProjectFormulaAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.projectFormulaService.addEntity(dto);
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

    /**
     * 发放分红金额定时任务
     * @return
     */
    @RequestMapping("PRJ8002")
    public BaseVO timedTaskShareRed(){
        BaseVO  baseVO=this.timedTaskShareRed.executeShareRed();
        return baseVO;
    }


}
