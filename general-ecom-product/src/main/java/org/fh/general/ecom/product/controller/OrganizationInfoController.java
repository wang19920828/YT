package org.fh.general.ecom.product.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.product.organization.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.product.organization.OrganizationLogOutputPO;
import org.fh.general.ecom.common.vo.product.organization.OrganizationDetailVO;
import org.fh.general.ecom.common.vo.product.organization.OrganizationListVO;
import org.fh.general.ecom.common.vo.product.organization.OrganizationLogVO;
import org.fh.general.ecom.product.service.OrganizationInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 机构信息表 前端控制器
 * </p>
 *
 * @author hlp
 * @since 2018-09-13
 */
@RestController
public class OrganizationInfoController {
    @Autowired
    private OrganizationInfoService organizationInfoService;


    @RequestMapping("PG00001")
    public PagingVO  findPage(InputOrganizationListDTO dto){
        PagingVO pageVO = new PagingVO();
        OutputOrganizationListDTO response =organizationInfoService.findPage(dto);
        List<OrganizationListVO> listvo= new ArrayList<OrganizationListVO>();
        List<OutputOrganizationDetailDTO>  list = response.getList();
        if(list!=null && list.size()>0) {
            list.forEach((OutputOrganizationDetailDTO temp) -> {
                OrganizationListVO vo = new OrganizationListVO();
                BeanUtils.copyProperties(temp, vo);
                listvo.add(vo);
            });
            pageVO.success(listvo, response.getPageInfo());
        }else{
            pageVO.noData();
        }
        return pageVO;
    }

    @RequestMapping("PG00002")
    public BaseVO  addOrganization(InputOrganizationAddDTO dto){
        BaseVO baseVo = new BaseVO();
        String code = organizationInfoService.addOrganization(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            getTipMsg(baseVo, code);
            return  baseVo;
        }
        baseVo.success();
        return baseVo;
    }


    @RequestMapping("PG00003")
    public BaseVO findDetail(String  id ){
        BaseVO baseVo = new BaseVO();
        OutputOrganizationDetailDTO response = organizationInfoService.findEntity(id);
        if(response==null){
            baseVo.error(OutEnum.WARN.getMessage());
            return baseVo;
        }
        OrganizationDetailVO vo = new OrganizationDetailVO();
        BeanUtils.copyProperties(response,vo );

        List<OrganizationLogOutputPO> dtos = response.getList();
        if(dtos!=null && dtos.size()>0) {
            List<OrganizationLogVO> listVo = new ArrayList<OrganizationLogVO>();
            dtos.forEach((OrganizationLogOutputPO temp) -> {
                OrganizationLogVO vo1 = new OrganizationLogVO();
                BeanUtils.copyProperties(temp, vo1);
                listVo.add(vo1);
            });
            vo.setList(listVo);

        }
        baseVo.success(vo);
        return baseVo;
    }

    @RequestMapping("PG00004")
    public BaseVO updateOrganization(InputOrganizationUpdateDTO dto){
        BaseVO baseVo = new BaseVO();
        String code= organizationInfoService.updateOrganization(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            getTipMsg(baseVo, code);
            return  baseVo;
        }
        baseVo.success();
        return baseVo;
    }

    private void  getTipMsg(BaseVO baseVo, String code) {
        if(OutEnum.TIPS.getCode().equals(code)){
            baseVo.setBusAlert("企业名称/统一社会信用代码不得重复");
        }else {
            baseVo.error(OutEnum.FAIL.getMessage());
        }
    }

    @RequestMapping("PG00005")
    public BaseVO delOrganization(InputOrganizationDelDTO dto){
        BaseVO baseVo = new BaseVO();
        String code= organizationInfoService.delOrganization(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVo.error(OutEnum.FAIL.getMessage());
            return  baseVo;
        }
        baseVo.success();
        return baseVo;
    }


    @RequestMapping("PG00006")
    public BaseVO findList(InputOrganizationListDTO dto){
        BaseVO baseVo = new BaseVO();
        OutputOrganizationListDTO response = organizationInfoService.findList(dto);
        List<OrganizationListVO> listvo= new ArrayList<OrganizationListVO>();
        List<OutputOrganizationDetailDTO>  list = response.getList();
        if(list!=null && list.size()>0) {
            list.forEach((OutputOrganizationDetailDTO temp) -> {
                OrganizationListVO po = new OrganizationListVO();
                BeanUtils.copyProperties(temp, po);
                listvo.add(po);
            });
            baseVo.success();
            baseVo.setData(listvo);
        }else{
            baseVo.noData();
        }
        return baseVo;
    }

    @RequestMapping("RPG00007")
    public OrganizationDetailVO findDetailByCompanyNo(@RequestBody String companyNo){
        OutputOrganizationDetailDTO response = organizationInfoService.findDetailByCompanyNo(companyNo);
        if(response !=null){
            OrganizationDetailVO vo = new OrganizationDetailVO();
            BeanUtils.copyProperties(response,vo );
            return vo;
        }
        return null;
    }

}
