package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.order.goldTicket.GoldTicketAddInputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.GoldTicketListInputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.GoldTicketListOutputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.MyGoldTicketInputDTO;
import org.fh.general.ecom.common.dto.order.order.OrderListOutputDTO;
import org.fh.general.ecom.common.enumeration.order.GoldTicketEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.goldTicket.GoldTicketListOutPO;
import org.fh.general.ecom.common.po.order.goldTicket.GoldTicketListParamPO;
import org.fh.general.ecom.common.po.order.goldTicket.MyGoldOutPO;
import org.fh.general.ecom.common.po.order.order.OrderListOutPO;
import org.fh.general.ecom.common.po.order.order.OrderListParamPO;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.vo.product.project.PlanEntityVO;
import org.fh.general.ecom.order.client.ProductClient;
import org.fh.general.ecom.order.model.GoldTicket;
import org.fh.general.ecom.order.dao.GoldTicketDao;
import org.fh.general.ecom.order.service.GoldTicketService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 代金券表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Slf4j
@Service
public class GoldTicketServiceImpl extends ServiceImpl<GoldTicketDao, GoldTicket> implements GoldTicketService {


    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductClient productClient;



    @Override
    public GoldTicketListOutputDTO findPage(GoldTicketListInputDTO dto)throws Exception {
        GoldTicketListOutputDTO response=new GoldTicketListOutputDTO();
        GoldTicketListParamPO paramPO=new GoldTicketListParamPO();
        BeanUtils.copyProperties(dto,paramPO);
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        List<GoldTicketListOutPO>  list=baseMapper.findList(paramPO);
        PageInfo pageInfo=new PageInfo(list);

        if(list !=null && list.size()>0){
            response.setList(list);
            response.setPageInfo(pageInfo);
        }
        return response;
    }


    @Override
    public String addEntity(GoldTicketAddInputDTO dto)  throws Exception{
        String code="";
        try {
            GoldTicket entity=new GoldTicket();
            BeanUtils.copyProperties(dto,entity );
            baseMapper.insert(entity);
            code= OutEnum.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            code=OutEnum.FAIL.getCode();
        }
        return code;
    }

    //过期时间往后延180天
    @Override
    public String delay(Long id) {
        GoldTicket entity=baseMapper.selectById(id);
        if(entity.getDelayNum()>0){
            entity.setDelayNum(entity.getDelayNum()-1);
            entity.setOffTime(this.getDelayTime(entity.getOffTime()));
            baseMapper.updateById(entity);
            return OutEnum.SUCCESS.getCode();
        }else{
            return "可用延期次数已经是0，不可再延期";
        }
    }


    @Override
    public Date getDelayTime(Date offTime){
        if(offTime==null){
            offTime=new Date();
        }
        String value=this.orderService.getDictionaryOneValue("gold_tick_days",null); //默认180天
        if(StringUtils.isEmpty(value)){
            value="180";
        }
        Date offDate=DateUtils.getDateAfter(offTime,Integer.valueOf(value));
        log.info("代金券原到期时间是（"+DateUtils.formatDate(offTime,DateUtils.DATE_FROMAT2)+"）延期（"+value+"）天后，到期时间是（"+DateUtils.formatDate(offDate,DateUtils.DATE_FROMAT2)+"）");
        return offDate;
    }



    @Override
    public List<MyGoldOutPO> findMyGoldList(MyGoldTicketInputDTO dto){
        GoldTicketListParamPO paramPO=new GoldTicketListParamPO();
        paramPO.setUserId(dto.getUserId());
        paramPO.setUseState(GoldTicketEnum.UseState.USE_STATE_WAIT.getValue());
        paramPO.setMyOffTimeStart(DateUtils.formatDate(new Date(),DateUtils.DATE_FROMAT2));
        List<GoldTicketListOutPO>  list=baseMapper.findList(paramPO);
        List<MyGoldOutPO> poList=new ArrayList<MyGoldOutPO>();
        if(list!=null && list.size()>0){
            List<String>  couponIdList=this.findPlanCouponIds(dto.getPlanId());
            list.forEach((GoldTicketListOutPO temp)->{
                MyGoldOutPO entity=new MyGoldOutPO();
                BeanUtils.copyProperties(temp,entity);
                entity.setCouponId(temp.getId());
              /* if(couponIdList!=null && couponIdList.size()>0){
                   //方案挂了这张代金券则往集合添加
                   boolean flag = couponIdList.contains(temp.getId());
                   if(flag){
                       poList.add(entity);
                   }
               }else{*/
                    //直接添加到返回集合里
                    poList.add(entity);
               // }

            });

        }
        return poList;
    }


    //查询方案挂的优惠券ID
    public List<String> findPlanCouponIds(Long planId){
        if(StringUtils.isNotEmpty(planId) && planId !=0L){
            PlanEntityVO planEn=this.productClient.findProgrammeEntityById(planId);
            if(planEn!=null && StringUtils.isNotEmpty(planEn.getCouponId())){
                String[] array=planEn.getCouponId().split(",");//String[] array = {"1","2","3","4"};
                List<String> list=Arrays.asList(array);
                //boolean flag = Arrays.asList(array).contains("2");
                return list;
            }
        }
        return null;
    }


    @Override
    public GoldTicketListOutPO  findEntityByTicketSn(String ticketSn){
        GoldTicketListOutPO  poEn=new GoldTicketListOutPO();
        GoldTicket t=new GoldTicket();
        t.setTicketSn(ticketSn);
        GoldTicket  entity=this.baseMapper.selectOne(t);
        if(entity ==null){
            return null;
        }
        BeanUtils.copyProperties(entity,poEn);
        return poEn;
    }

}
