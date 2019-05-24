package org.fh.general.ecom.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.product.organization.*;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.product.organization.OrganizationLogOutputPO;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.product.client.BasicClient;
import org.fh.general.ecom.product.dao.OrganizationInfoDao;
import org.fh.general.ecom.product.model.OrganizationInfo;
import org.fh.general.ecom.product.service.OrganizationInfoService;
import org.fh.general.ecom.product.service.OrganizationLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 机构信息表 服务实现类
 * </p>
 *
 * @author hlp
 * @since 2018-09-13
 */
@Slf4j
@Service
@Transactional
public class OrganizationInfoServiceImpl extends ServiceImpl<OrganizationInfoDao, OrganizationInfo> implements OrganizationInfoService
{
    @Autowired
    private OrganizationLogService organizationLogService;

    @Autowired
    private BasicClient basicClient;

    @Override
    public String delOrganization(InputOrganizationDelDTO dto) {
        OrganizationInfo organizationInfo = baseMapper.selectById(dto.getId());
        if(organizationInfo==null){
            return OutEnum.SUCCESS.getCode();
        }
        organizationInfo.setStatus(ComEnum.IsDelete.DEL.getValue());
        int success = baseMapper.updateById(organizationInfo);
        if(success>0){
            return OutEnum.SUCCESS.getCode();
        }
        return  OutEnum.FAIL.getCode();
    }

    @Override
    public OutputOrganizationListDTO findList(InputOrganizationListDTO dto) {
        OutputOrganizationListDTO organizationListOutputDTO = new OutputOrganizationListDTO();
        EntityWrapper wrapper=new EntityWrapper();
        if(StringUtils.isNotEmpty(dto.getStatus())){
            wrapper.eq("status",dto.getStatus());
        }
        if(StringUtils.isNotEmpty(dto.getCompanyName())){
            wrapper.like("company_name",dto.getCompanyName());
        }
        if(StringUtils.isNotEmpty(dto.getCompanyNo())){
            wrapper.eq("company_no",dto.getCompanyNo());
        }
        if(StringUtils.isNotEmpty(dto.getContacts())){
            wrapper.like("contacts",dto.getContacts());
        }
        if(StringUtils.isNotEmpty(dto.getContactsTel())){
            wrapper.eq("contacts_tel",dto.getContactsTel());
        }
        if(StringUtils.isNotEmpty(dto.getCustomerManager())){
            wrapper.eq("customer_manager",dto.getCustomerManager());
        }
        if(StringUtils.isNotEmpty(dto.getRegisteredAddress())){
            wrapper.eq("registered_address",dto.getRegisteredAddress());
        }
        List<OrganizationInfo> list=baseMapper.selectList(wrapper);
        List<OutputOrganizationDetailDTO>  listpo=new ArrayList<OutputOrganizationDetailDTO>();
        list.forEach((OrganizationInfo temp) -> {
            OutputOrganizationDetailDTO po=new OutputOrganizationDetailDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        organizationListOutputDTO.setList(listpo);
        return organizationListOutputDTO;
    }



    @Override
    public OutputOrganizationListDTO findPage(InputOrganizationListDTO dto) {
        OutputOrganizationListDTO organizationListOutputDTO = new OutputOrganizationListDTO();
        EntityWrapper wrapper=new EntityWrapper();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        if(StringUtils.isNotEmpty(dto.getStatus())){
            wrapper.eq("status",dto.getStatus());
        }
        if(StringUtils.isNotEmpty(dto.getCompanyName())){
            wrapper.like("company_name",dto.getCompanyName());
        }
        if(StringUtils.isNotEmpty(dto.getCompanyNo())){
            wrapper.eq("company_no",dto.getCompanyNo());
        }
        if(StringUtils.isNotEmpty(dto.getContacts())){
            wrapper.like("contacts",dto.getContacts());
        }
        if(StringUtils.isNotEmpty(dto.getContactsTel())){
            wrapper.eq("contacts_tel",dto.getContactsTel());
        }
        if(StringUtils.isNotEmpty(dto.getCustomerManager())){
            wrapper.like("customer_manager",dto.getCustomerManager());
        }
        if(StringUtils.isNotEmpty(dto.getRegisteredAddress())){
            wrapper.like("registered_address",dto.getRegisteredAddress());

        }
        List<OrganizationInfo> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);

        List<OutputOrganizationDetailDTO>  listpo=new ArrayList<OutputOrganizationDetailDTO>();

        Map<String,String> districtMap = new HashMap<String,String>();
        list.forEach((OrganizationInfo temp) -> {
            OutputOrganizationDetailDTO dto1=new OutputOrganizationDetailDTO();
            BeanUtils.copyProperties(temp,dto1);
            if(StringUtils.isNotEmpty(temp.getRegisteredAddress())) {
                String code = temp.getRegisteredAddress();
                if(districtMap.containsKey(code)){
                    String name =districtMap.get(code);
                    dto1.setDistrict(name);
                }else{
                    String name = this.basicClient.findbySortCode(code);
                    dto1.setDistrict(name);
                    districtMap.put(code,name);
                }
            }
            listpo.add(dto1);
        });
        organizationListOutputDTO.setList(listpo);
        organizationListOutputDTO.setPageInfo(pageInfo);
        return organizationListOutputDTO;
    }

    @Override
    public String addOrganization(InputOrganizationAddDTO dto) {
        OrganizationInfo organizationInfo = new OrganizationInfo();
        // 机构名称，统一社会代码不能重复

        EntityWrapper wrapper=new EntityWrapper();
        wrapper.where("status={0}",ComEnum.IsDelete.NORMAL.getValue())
                .andNew("company_name={0} or social_credit_code={1}",dto.getCompanyName(),dto.getSocialCreditCode());
        int i =  baseMapper.selectCount(wrapper);
        if(i>0){
            return OutEnum.TIPS.getCode();
        }

        BeanUtils.copyProperties(dto,organizationInfo);
        organizationInfo.setTermOfValidit(DateUtils.getDate(dto.getTermOfValidit(), DateUtils.DATE_FROMAT1));
        organizationInfo.setCompanyNo(DateUtils.formatDate(new Date(), DateUtils.DATE_FROMAT4));
        organizationInfo.setCreateDate(new Date());
        organizationInfo.setUpdateBy(organizationInfo.getCreateBy());
        organizationInfo.setUpdateDate(new Date());
        organizationInfo.setStatus(ComEnum.IsDelete.NORMAL.getValue());
        organizationInfo.setBranch(dto.getBranch()==null? ComEnum.Branch.YUN_TOU.getValue():dto.getBranch());
        organizationInfo.setBranchName(dto.getBranchName()==null?ComEnum.Branch.YUN_TOU.getName():dto.getBranchName());
        organizationInfo.setChannel(dto.getChannel()==null?ComEnum.Channel.WEB.getValue():dto.getChannel());
        int success= baseMapper.insert(organizationInfo);
        if(success>0){
            return OutEnum.SUCCESS.getCode();
        }
        return  OutEnum.FAIL.getCode();
    }

    @Override
    public OutputOrganizationDetailDTO findEntity(String id) {
        OutputOrganizationDetailDTO response = new OutputOrganizationDetailDTO();
        OrganizationInfo organizationInfo  = baseMapper.selectById(id);
        List<OrganizationLogOutputPO> listPo  = new ArrayList<OrganizationLogOutputPO>();
        if(organizationInfo!=null){
             listPo = organizationLogService.findList(id);
        }
        if(organizationInfo!=null) {
            BeanUtils.copyProperties(organizationInfo, response);
        }
        response.setList(listPo);
        return response;
    }

    @Override
    public String updateOrganization(InputOrganizationUpdateDTO dto) {
        OrganizationInfo entity = baseMapper.selectById(dto.getId());
        if(entity==null){
            return OutEnum.SUCCESS.getCode();
        }
        // 机构名称，统一社会代码不能重复
        EntityWrapper wrapper=new EntityWrapper();
        wrapper.where("status={0}",ComEnum.IsDelete.NORMAL.getValue()).andNew("id !={0}",dto.getId())
                .andNew("company_name={0} or social_credit_code={1}",dto.getCompanyName(),dto.getSocialCreditCode());
        int i =  baseMapper.selectCount(wrapper);
        if(i>0){
            return OutEnum.TIPS.getCode();
        }


        OrganizationInfo organizationInfo=new OrganizationInfo();
        BeanUtils.copyProperties(dto,organizationInfo);
        organizationInfo.setTermOfValidit(DateUtils.getDate(dto.getTermOfValidit(), DateUtils.DATE_FROMAT1));

        organizationInfo.setCreateBy(entity.getCreateBy());
        organizationInfo.setBranch(entity.getBranch());
        organizationInfo.setBranchName(entity.getBranchName());
        organizationInfo.setCompanyNo(entity.getCompanyNo());
        int success= baseMapper.updateById(organizationInfo);

        if(success<=0){
            return OutEnum.FAIL.getCode();
        }
        organizationLogService.insertEntity(dto);
        return  OutEnum.SUCCESS.getCode();
    }

    @Override
    public OutputOrganizationDetailDTO findDetailByCompanyNo(String companyNo) {
        OutputOrganizationDetailDTO response = new OutputOrganizationDetailDTO();
        OrganizationInfo one=new OrganizationInfo();
        one.setCompanyNo(companyNo);
        OrganizationInfo organizationInfo  = baseMapper.selectOne(one);
        if(organizationInfo==null){
            return null;
        }
        BeanUtils.copyProperties(organizationInfo,response);
        return response;
    }



}
