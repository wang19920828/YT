package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.model.CompanyInformation;
import org.fh.general.ecom.basics.service.CompanyInformationService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationInputDTO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WYY
 * @since 2018-09-20
 */
@RestController
public class CompanyInformationController {
    @Autowired
    private CompanyInformationService companyInformationService;
    /**
     * 根据id查
     */
    @RequestMapping("CI000001")
    public BaseVO selectByPrimaryKey (Long id){
        BaseVO baseVO = new BaseVO();
        CompanyInformationOutputDTO out=companyInformationService.selectByPrimaryKey(id);
        BeanUtils.copyProperties(out,baseVO);
        if(baseVO!=null){
            baseVO.success(out);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }

    /**
     * 添加
     * @param dto
     */
    @RequestMapping("CI000002")
    public BaseVO insertCompanyInformation (CompanyInformationInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=companyInformationService.insertCompanyInformation(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 修改
     */
    @RequestMapping("CI000003")
    public BaseVO updateCompanyInformation (CompanyInformationInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=companyInformationService.updateCompanyInformation(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     * 根据id删除
     */
    @RequestMapping("CI000004")
    public BaseVO deleteByPrimaryKey (Long id){
        BaseVO baseVO=new BaseVO();
        String out=companyInformationService.deleteByPrimaryKey(id);
        if(baseVO!=null){
            baseVO.success();
        }else{
            baseVO.noData();
        }
        return baseVO;
    }
}
