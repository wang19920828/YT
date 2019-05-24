package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.CompanyInformationService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationInputDTO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationOutputDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.help.CompanyInformationListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.help.CompanyInformationListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转换格式
        dto.setCreateTime(sdf.format(new Date()));
        dto.setDel(ComEnum.IsDelete.NORMAL.getValue());
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
    /**
     * 分页列表
     * */
    @RequestMapping("CI000005")
    public PagingVO findPage(CompanyInformationListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            CompanyInformationListOutputDTO dtoEntity= this.companyInformationService.findPage(dto);
            List<CompanyInformationListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;}
            List<CompanyInformationListVo> listvo=new ArrayList<CompanyInformationListVo>();
            list.forEach((CompanyInformationListOutPO temp) -> {
                CompanyInformationListVo voEn=new CompanyInformationListVo();
                BeanUtils.copyProperties(temp,voEn);
                listvo.add(voEn);
            });
            pagingVO.success(listvo,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }
}
