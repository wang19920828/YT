package org.fh.general.ecom.product.controller;

import org.fh.general.ecom.common.dto.product.project.FinancingListInputDTO;
import org.fh.general.ecom.common.dto.product.project.FinancingListOutputDTO;
import org.fh.general.ecom.common.po.product.project.ProjectFinancingOutputPO;
import org.fh.general.ecom.product.service.ProjectFinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 项目筹资信息 前端控制器
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
@RestController
public class ProjectFinancingController {

    @Autowired
    private ProjectFinancingService  projectFinancingService;


    /**
     * 查询项目列表
     * @param dto
     * @return
     */
    @RequestMapping("FIN0011")
    public List<ProjectFinancingOutputPO>  findFinancingList(@RequestBody FinancingListInputDTO dto){
        FinancingListOutputDTO  outDto=this.projectFinancingService.findFinancingList(dto);
        if(outDto!=null && outDto.getFinancingList()!=null && outDto.getFinancingList().size()>0){
            List<ProjectFinancingOutputPO>   list=  outDto.getFinancingList();
            return list;
        }
        return null;
    }
	
}
