package org.fh.general.ecom.product.controller;


import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.product.consulting.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.product.consulting.ConsultingProjectOutputPO;
import org.fh.general.ecom.common.vo.product.consulting.ConsultingProjectDetailVo;
import org.fh.general.ecom.common.vo.product.consulting.ConsultingProjectListVO;
import org.fh.general.ecom.product.service.ConsultingProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 咨询项目表 前端控制器
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
@RestController
public class ConsultingProjectController {

    @Autowired
    private ConsultingProjectService consultingProjectService;

    @RequestMapping("PC00001")
    public PagingVO findPage(InputConsultingProjectListDTO dto){
        PagingVO pageVO = new PagingVO();
        OutputConsultingProjectListDTO response =consultingProjectService.findPage(dto);
        List<ConsultingProjectListVO> listvo= new ArrayList<ConsultingProjectListVO>();
        List<ConsultingProjectOutputPO>  list = response.getList();
        if(list!=null && list.size()>0) {
            list.forEach((ConsultingProjectOutputPO temp) -> {
                ConsultingProjectListVO vo = new ConsultingProjectListVO();
                BeanUtils.copyProperties(temp, vo);
                listvo.add(vo);
            });
            pageVO.success(listvo, response.getPageInfo());
        }else{
            pageVO.noData();
        }
        return pageVO;
    }


    @RequestMapping("PC00003")
    public BaseVO findDetail(String id){
        BaseVO baseVo = new BaseVO();
        OutputConsultingProjectDetailDTO response = consultingProjectService.findEntity(id);
        if(response==null){
            baseVo.error(OutEnum.WARN.getMessage());
            return baseVo;
        }
        ConsultingProjectDetailVo vo = new ConsultingProjectDetailVo();
        BeanUtils.copyProperties(response,vo );


        baseVo.success(vo);
        return baseVo;
    }


    @RequestMapping("PC00004")
    public BaseVO updateConsultingProject(InputConsultingProjectUpdateDTO dto){
        BaseVO baseVo = new BaseVO();
        String code= consultingProjectService.updateConsultingProject(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            getTipMsg(baseVo, code);
            return  baseVo;
        }
        baseVo.success();
        return baseVo;
    }

    @RequestMapping("PC00005")
    public BaseVO delConsultingProject(InputConsultingProjectDelDTO dto){
        BaseVO baseVo = new BaseVO();
        String code= consultingProjectService.delConsultingProject(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            getTipMsg(baseVo, code);
            return  baseVo;
        }
        baseVo.success();
        return baseVo;
    }



    @RequestMapping("PC00006")
    public BaseVO findDetailByProjectId(String projectId){
        BaseVO baseVo = new BaseVO();
        OutputConsultingProjectDetailDTO response = consultingProjectService.findDetailByProjectId(projectId);
        if(response==null){
            baseVo.error(OutEnum.WARN.getMessage());
            return baseVo;
        }
        ConsultingProjectDetailVo vo = new ConsultingProjectDetailVo();
        BeanUtils.copyProperties(response,vo );
        baseVo.success(vo);
        return baseVo;
    }




    private void  getTipMsg(BaseVO baseVo, String code) {
        if(OutEnum.TIPS.getCode().equals(code)){
            baseVo.setBusAlert("项目名称不得重复");
        }else {
            baseVo.error(OutEnum.FAIL.getMessage());
        }
    }



}
