package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.basics.dao.BankCardDao;
import org.fh.general.ecom.basics.model.BankCard;
import org.fh.general.ecom.basics.service.BankCardService;
import org.fh.general.ecom.common.dto.basics.user.bankCard.BankCardInsertInputDTO;
import org.fh.general.ecom.common.dto.basics.user.bankCard.BankCardListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.bankCard.BankCardOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.bankCard.BankCardUpdateInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
@Service
public class BankCardServiceImpl extends ServiceImpl<BankCardDao, BankCard> implements BankCardService {

    @Override
    public String deleteByPrimaryKey(Long id){
        BankCard entity=new BankCard();
        entity.setId(id);
        entity.setStatus("1");
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public BankCardOutputDTO selectByPrimaryKey(Long id){
        BankCardOutputDTO out= new BankCardOutputDTO();
        BankCard card = baseMapper.selectById(id);
        if(card!=null){
            BeanUtils.copyProperties(card,out);
        }else {
            return null;
        }
        return out;
    }
    @Override
    public BankCardOutputDTO selectByPrimaryUserId(Long userId){
        BankCardOutputDTO out= new BankCardOutputDTO();
        BankCard bank=new BankCard();
        bank.setUserId(userId);
        bank.setStatus("0");//正常
        BankCard card =baseMapper.selectOne(bank);
        if(card!=null){
            BeanUtils.copyProperties(card,out);
        }else {
            return null;
        }
        return out;
    }
    @Override
    public String insertBankCard(BankCardInsertInputDTO dto){
        //根据用户查询银行卡
        BankCard bank=new BankCard();
        bank.setUserId(dto.getUserId());
        bank.setStatus("0");//正常
        BankCard card =baseMapper.selectOne(bank);
        if(card!=null){
            return "用户已绑定银行卡！";
        }
        BankCard entity=new BankCard();
        BeanUtils.copyProperties(dto,entity);
        entity.setCreateTime(new Date());
         baseMapper.insert(entity);
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public String updateByPrimaryKeySelective(BankCardUpdateInputDTO dto){
        BankCard entity=new BankCard();
        entity.setId(dto.getId());
        //根据id删除，再添加
        entity.setStatus("1");
        baseMapper.updateById(entity);//移除
        BankCard entity1=new BankCard();//添加
        //客户信息是否关联
        //entity1.setCustId( );
        entity1.setUserId(dto.getUserId());
        entity1.setCreateTime(new Date());
        entity1.setBankCode(dto.getBankCode());
        entity1.setAccountNo(dto.getAccountNo());
        baseMapper.insert(entity1);
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public BankCardListOutputDTO userBankCardList (Long userId){
        BankCardListOutputDTO output=new BankCardListOutputDTO();
        EntityWrapper<BankCard> wrapper = new EntityWrapper<>();
        wrapper.eq("status","0");//正常状态
        wrapper.eq("user_id",userId);//用户id
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<BankCard> list=baseMapper.selectList(wrapper);
        List<BankCardOutputDTO> listpo=new ArrayList<>();
        list.forEach((BankCard temp) -> {
            BankCardOutputDTO po=new BankCardOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        output.setBankCardListOutput(listpo);
        return output;
    }


}
