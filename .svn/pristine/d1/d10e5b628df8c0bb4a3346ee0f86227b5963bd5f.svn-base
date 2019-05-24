package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.basics.dao.CustomerDao;
import org.fh.general.ecom.basics.model.Customer;
import org.fh.general.ecom.basics.service.CustomerService;
import org.fh.general.ecom.common.dto.basics.user.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-17
 */
@Slf4j
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, Customer> implements CustomerService {
    @Override
    public CustomerListOutputDTO findPage(CustomerListInputDTO dto)throws Exception {
        CustomerListOutputDTO response=new CustomerListOutputDTO();
        EntityWrapper<Customer> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        /* if(StringUtils.isNotEmpty(dto.getOrderSn())){
            wrapper.eq("order_sn",dto.getOrderSn());
        }*/
        if(StringUtils.isNotEmpty(dto.getIsName())){
            wrapper.eq("is_name",dto.getIsName());
        }
        wrapper.notIn("cust_status","3");//不等于删除状态
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<Customer> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<CustomerOutputDTO>  listpo=new ArrayList<CustomerOutputDTO>();
        list.forEach((Customer temp) -> {
            CustomerOutputDTO po=new CustomerOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


   @Override
    public String addEntity(CustomerAddInputDTO dto)  throws Exception{
        String code="";
        try {
            Customer entity=new Customer();
            BeanUtils.copyProperties(dto,entity );
            baseMapper.insert(entity);
            code= OutEnum.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            code=OutEnum.FAIL.getCode();
        }
        return code;
    }

    @Override
    public String deleteEntityById(Long custId)throws Exception{
        Customer entity=new Customer();
        entity.setCustId(custId);
        entity.setCustStatus("3");
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String updateEntity(CustomerUpdateInputDTO dto) throws Exception{
        Customer entity=new Customer();
        BeanUtils.copyProperties(dto,entity);
        Customer param=new Customer();
        param.setCustId(dto.getCustId());
        Customer findOne= baseMapper.selectOne(param);
        if(findOne==null){
            return OutEnum.WARN.getCode();
        }
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public CustomerOutputDTO findEntityById(Long custId) throws Exception{
        CustomerOutputDTO response=new CustomerOutputDTO();
        Customer entity=baseMapper.selectById(custId);
        if(entity!=null){
            BeanUtils.copyProperties(entity,response );
            return response;
        }
        return null;
    }
    @Override
    public CustomerOutputDTO findEntityByUserId(Long userId)throws Exception{
        CustomerOutputDTO response=new CustomerOutputDTO();
        Customer en= new Customer();
        en.setUserId(userId);
        Customer entity = baseMapper.selectOne(en);
        if(entity!=null){
            BeanUtils.copyProperties(entity,response );
            return response;
        }
        return null;
    }
}
