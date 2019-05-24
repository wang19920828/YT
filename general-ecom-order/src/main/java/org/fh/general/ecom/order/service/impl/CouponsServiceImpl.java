package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.order.coupons.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.coupons.CouponsListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.product.organization.OrganizationDetailVO;
import org.fh.general.ecom.order.client.ProductClient;
import org.fh.general.ecom.order.dao.CouponsDao;
import org.fh.general.ecom.order.model.Coupons;
import org.fh.general.ecom.order.service.CouponsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
@Slf4j
@Service
public class CouponsServiceImpl extends ServiceImpl<CouponsDao, Coupons> implements CouponsService {

    @Autowired
    private ProductClient productClient;

    @Override
    public CouponsListOutputDTO findPage(CouponsListInputDTO dto)throws Exception {
        CouponsListOutputDTO response=new CouponsListOutputDTO();
        EntityWrapper<Coupons> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        if(StringUtils.isNotEmpty(dto.getCouponName())){
            wrapper.like("coupon_name",dto.getCouponName());
        }
        if(StringUtils.isNotEmpty(dto.getType())){
            wrapper.eq("type",dto.getType());
        }
        if(StringUtils.isNotEmpty(dto.getStatus())){
            wrapper.eq("status",dto.getStatus());
        }
        if(StringUtils.isNotEmpty(dto.getAddTimeStart())){
            wrapper.gt("add_time",dto.getAddTimeStart());
        }
        if(StringUtils.isNotEmpty(dto.getAddTimeEnd())){
            wrapper.lt("add_time",dto.getAddTimeEnd());
        }
        List<Coupons> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<CouponsListOutPO>  listpo=new ArrayList<CouponsListOutPO>();
        list.forEach((Coupons temp) -> {
            CouponsListOutPO po=new CouponsListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public String addEntity(CouponsAddInputDTO dto)  throws Exception{
        String code="";
        try {
            Coupons entity=new Coupons();
            BeanUtils.copyProperties(dto,entity );
            entity.setAddTime(new Date());
            //企业名称
            String companyNo=dto.getCompanyNo();
            if(StringUtils.isNotEmpty(companyNo)){
                OrganizationDetailVO companyEn=this.productClient.findDetailByCompanyNo(companyNo);
                if(companyEn==null){
                    return "企业编号["+dto.getCompanyNo()+"]有误，查无数据!";
                }
                entity.setCompanyName(companyEn.getCompanyName());
            }
            baseMapper.insert(entity);
            code= OutEnum.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            code=OutEnum.FAIL.getCode();
        }
        return code;
    }

    @Override
    public String deleteEntityById(Long id) {
        Coupons entity=new Coupons();
        entity.setId(id);
        //entity.setIsDelete(ComEnum.IsDelete.DEL.getValue());
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String updateEntity(CouponsUpdateInputDTO dto) {
        Coupons entity=new Coupons();
        BeanUtils.copyProperties(dto,entity);
        Coupons param=new Coupons();
        param.setId(dto.getId());
        Coupons findOne= baseMapper.selectOne(param);
        if(findOne==null){
            return OutEnum.WARN.getCode();
        }
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public CouponsDetailOutputDTO findEntityById(Long id) {
        CouponsDetailOutputDTO response=new CouponsDetailOutputDTO();
        Coupons entity=baseMapper.selectById(id);
        if(entity!=null){
            BeanUtils.copyProperties(entity,response );
            return response;
        }
        return null;
    }


    /**
     * 根据订单号查询操作日志列表
     * @param orderSn
     * @return
     */
    @Override
    public CouponsListOutputDTO findListByOrderSn(String orderSn) {
        CouponsListOutputDTO dto=new CouponsListOutputDTO();
        EntityWrapper<Coupons> wrapper = new EntityWrapper<>();
        wrapper.eq("order_sn",orderSn);
        List<Coupons> list=baseMapper.selectList(wrapper);
        dto.setList(changeToCouponsListOutPO(list));
        return dto;
    }

    @Override
    public CouponsListOutputDTO findLByIds(String ids) {
        CouponsListOutputDTO response=new CouponsListOutputDTO();
        if(StringUtils.isNotEmpty(ids)){
            List<Long> list = StringUtils.strToLongList(ids);
            if(list!=null && list.size()>0) {
                EntityWrapper<Coupons> wrapper = new EntityWrapper<>();
                wrapper.in("id", list);
                wrapper.eq("status","1");
                List<Coupons> coupons = this.baseMapper.selectList(wrapper);
                response.setList(changeToCouponsListOutPO(coupons));
                return response;
            }
        }
        return null;
    }

    private  List<CouponsListOutPO> changeToCouponsListOutPO( List<Coupons> coupons) {
        List<CouponsListOutPO> listpo = new ArrayList<CouponsListOutPO>();
        if (coupons != null && coupons.size() > 0) {
            coupons.forEach((Coupons temp) -> {
                CouponsListOutPO po = new CouponsListOutPO();
                BeanUtils.copyProperties(temp, po);
                listpo.add(po);
            });
        }
        return listpo;
    }


}
