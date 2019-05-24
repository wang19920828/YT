package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.OtherParameterDao;
import org.fh.general.ecom.basics.model.OtherParameter;
import org.fh.general.ecom.basics.service.OtherParameterService;
import org.fh.general.ecom.common.dto.basics.OtherParameter.*;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.OtherParameterOutPO.OtherParameterListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 其它参数 服务实现类
 * </p>
 *
 * @author wyy
 * @since 2018-10-09
 */
@Service
public class OtherParameterServiceImpl extends ServiceImpl<OtherParameterDao, OtherParameter> implements OtherParameterService {

    @Override
    public OtherParameterListOutDTO findOthListByType(OtherParameterListInDTO paramDto) {
        EntityWrapper<OtherParameter> wrapper = new EntityWrapper<OtherParameter>();
        wrapper.eq("sys_Type",paramDto.getSysType());
        wrapper.eq("branch",paramDto.getBranch());
        List<OtherParameter> list=baseMapper.selectList(wrapper);
        //改成从缓存里查 TODO

        List<OtherParameterListOutPO> listPo= new ArrayList<OtherParameterListOutPO>();
        list.forEach((OtherParameter  temp)->{
            OtherParameterListOutPO poEn=new OtherParameterListOutPO();
            BeanUtils.copyProperties(temp,poEn);
            listPo.add(poEn);
        });
        OtherParameterListOutDTO toEn=new OtherParameterListOutDTO();
        toEn.setList(listPo);
        return toEn;
    }

    @Override
    public String addOtherParameter(InputOtherParameterAddDTO dto) {
        EntityWrapper<OtherParameter> wrapper = new EntityWrapper<OtherParameter>();
        wrapper.where("status={0}",ComEnum.IsDelete.NORMAL.getValue())
                .andNew("param_key={0} or param_name={1}",dto.getParamKey(),dto.getParamName())
                .andNew("sys_type={0}",dto.getSysType())
                .andNew("branch={0}",dto.getBranch()==null? ComEnum.Branch.YUN_TOU.getValue():dto.getBranch());
        int i =  baseMapper.selectCount(wrapper);
        if(i>0){
            return "该字典已经存在";
        }

        OtherParameter entity = new OtherParameter();
        BeanUtils.copyProperties(dto,entity);
        entity.setContent(ComEnum.IsDelete.NORMAL.getValue());
        int success = this.baseMapper.insert(entity);
        if(success>0){
            return  "";
        }
        return OutEnum.FAIL.getMessage();
    }

    @Override
    public String updateOtherParameter(InputOtherParameterUpdateDTO dto) {
        OtherParameter entity = this.baseMapper.selectById(dto.getId());
        if(entity==null){
            return OutEnum.WARN.getMessage();
        }

        EntityWrapper<OtherParameter> wrapper = new EntityWrapper<OtherParameter>();
        wrapper.where("status={0}",ComEnum.IsDelete.NORMAL.getValue())
                .andNew("param_key={0} or param_name={1}",dto.getParamKey(),dto.getParamName())
                .andNew("sys_type={0}",dto.getSysType())
                .andNew("branch={0}",dto.getBranch()==null? ComEnum.Branch.YUN_TOU.getValue():dto.getBranch());
        int i =  baseMapper.selectCount(wrapper);
        if(i>0){
            return "该字典已经存在";
        }
        OtherParameter updateEntity = new OtherParameter();
        BeanUtils.copyProperties(dto,updateEntity);
        updateEntity.setBranch(updateEntity.getBranch());
        updateEntity.setStatus(updateEntity.getStatus());
        updateEntity.setContent(updateEntity.getContent());
        updateEntity.setBranchName(updateEntity.getBranchName());
        updateEntity.setParamKey(updateEntity.getParamKey());
        updateEntity.setParamName(updateEntity.getParamName());
        updateEntity.setParamValue(updateEntity.getParamValue());
        int success =this.baseMapper.updateById(updateEntity);
        if(success>0){
            return  "";
        }
        return  OutEnum.FAIL.getMessage();
    }

    @Override
    public String delOtherParameter(InputOtherParameterDelDTO dto) {
        if(StringUtils.isEmpty(dto.getIds())){
            return OutEnum.MUSTPARAMS.getMessage();
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("status",ComEnum.IsDelete.DEL.getValue());
        map.put("list",StringUtils.strToList(dto.getIds()));
        this.baseMapper.deleteByIds(map);
        return "";
    }

    @Override
    public OutputOtherParameterDetailDTO findDetail(InputOtherParameterDetailDTO dto) {
        OtherParameter otherParameter=this.baseMapper.selectById(dto.getId());
        if(null==otherParameter){
            return null;
        }
        OutputOtherParameterDetailDTO outputOtherParameterDetailDTO=new OutputOtherParameterDetailDTO();
        BeanUtils.copyProperties(otherParameter,outputOtherParameterDetailDTO);
        return outputOtherParameterDetailDTO;
    }

    @Override
    public OutputOtherParameterListDTO findPage(InputOtherParameterListDTO dto) {
        OutputOtherParameterListDTO outputOtherParameterListDTO = new OutputOtherParameterListDTO();
        EntityWrapper wrapper=new EntityWrapper();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }

        List<OtherParameter> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<OutputOtherParameterDetailDTO>  listpo=new ArrayList<OutputOtherParameterDetailDTO>();
        list.forEach((OtherParameter temp) -> {
            OutputOtherParameterDetailDTO dto1=new OutputOtherParameterDetailDTO();
            BeanUtils.copyProperties(temp,dto1);
            listpo.add(dto1);
        });
        outputOtherParameterListDTO.setList(listpo);
        outputOtherParameterListDTO.setPageInfo(pageInfo);
        return outputOtherParameterListDTO;
    }
}
