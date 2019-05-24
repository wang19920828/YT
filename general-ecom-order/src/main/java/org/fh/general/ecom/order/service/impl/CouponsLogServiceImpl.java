package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.common.dto.order.couponsLog.CouponsLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.couponsLog.CouponsLogListInputDTO;
import org.fh.general.ecom.common.dto.order.couponsLog.CouponsLogListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.couponsLog.CouponsLogListOutPO;
import org.fh.general.ecom.order.model.CouponsLog;
import org.fh.general.ecom.order.dao.CouponsLogDao;
import org.fh.general.ecom.order.service.CouponsLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 优惠券发放记录表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Service
public class CouponsLogServiceImpl extends ServiceImpl<CouponsLogDao, CouponsLog> implements CouponsLogService {



    @Override
    public CouponsLogListOutputDTO findPage(CouponsLogListInputDTO dto)throws Exception {
        CouponsLogListOutputDTO response=new CouponsLogListOutputDTO();
        EntityWrapper<CouponsLog> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        /*
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        List<CouponsLog> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<CouponsLogListOutPO>  listpo=new ArrayList<CouponsLogListOutPO>();
        list.forEach((CouponsLog temp) -> {
            CouponsLogListOutPO po=new CouponsLogListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public String addEntity(CouponsLogAddInputDTO dto)  throws Exception{
        String code="";
        try {
            CouponsLog entity=new CouponsLog();
            BeanUtils.copyProperties(dto,entity );
            baseMapper.insert(entity);
            code= OutEnum.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            code=OutEnum.FAIL.getCode();
        }
        return code;
    }
}
