package org.fh.general.ecom.product.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.product.appraise.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.vo.product.appraise.ProjectAppraiseDetailVO;
import org.fh.general.ecom.product.service.ProjectAppraiseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目评价信息 前端控制器
 * </p>
 *
 * @author hlp
 * @since 2018-09-21
 */
@RestController
public class ProjectAppraiseController {

    @Autowired
    private ProjectAppraiseService projectAppraiseService;

    @RequestMapping("PA00001")
    public PagingVO  findPage(InputProjectAppraiseListDTO dto){
        PagingVO pageVO = new PagingVO();
        OutputProjectAppraiseListDTO  response = this.projectAppraiseService.findPage(dto);
        List<ProjectAppraiseDetailVO> listvo= new ArrayList<ProjectAppraiseDetailVO>();
        List<OutputProjectAppriaiseDetailDTO>  list = response.getList();
        if(list!=null && list.size()>0) {
            list.forEach((OutputProjectAppriaiseDetailDTO temp) -> {
                ProjectAppraiseDetailVO vo = new ProjectAppraiseDetailVO();
                BeanUtils.copyProperties(temp, vo);
                listvo.add(vo);
            });
            pageVO.success(listvo, response.getPageInfo());
        }else{
            pageVO.noData();
        }
        return pageVO;
    }


    @RequestMapping("PA00002")
    public BaseVO findDetail(String  id ){
        BaseVO baseVO = new BaseVO();
        OutputProjectAppriaiseDetailDTO detailDTO = this.projectAppraiseService.findById(id);
        if(detailDTO==null){
            baseVO.error(OutEnum.WARN.getMessage());
            return baseVO;
        }
        ProjectAppraiseDetailVO vo = new ProjectAppraiseDetailVO();
        BeanUtils.copyProperties(detailDTO,vo );
        baseVO.setData(vo);
        baseVO.success();
        return baseVO;
    }

    @RequestMapping("PA00003")
    public BaseVO delDetail(InputProjectAppraiseDetailDTO dto ){
        BaseVO baseVO = new BaseVO();
        String code  = this.projectAppraiseService.delById(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
           baseVO.error(code);
           return baseVO;
        }
        baseVO.success();
        return baseVO;
    }


    @RequestMapping("PA00004")
    public BaseVO updateDetail(InputProjectAppraiseDetailDTO dto ){
        BaseVO baseVO = new BaseVO();
        String code = this.projectAppraiseService.updateDetail(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    @RequestMapping("PA00005")
    public BaseVO  addEntity(InputProjectAppraiseAddDTO dto ){
        BaseVO baseVO = new BaseVO();
        String code = this.projectAppraiseService.addEntity(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return baseVO;
        }
        baseVO.success();
        return baseVO;
    }

}
