package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.basics.dao.FundJournalDao;
import org.fh.general.ecom.basics.model.FundJournal;
import org.fh.general.ecom.basics.service.FundJournalService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账目流水 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-27
 */
@Service
public class FundJournalServiceImpl extends ServiceImpl<FundJournalDao, FundJournal> implements FundJournalService {

   /* @Override
    public String insertJournal( FundJournalInsertInputDTO dto) {
        FundJournal fj=new FundJournal();
        if(StringUtils.isEmpty(dto.getJournalNo())){
            return "资金交易流水号为空！";
        }
        if(StringUtils.isEmpty(dto.getDzqbAmt())){
            return "电子钱包余额为空！";
        }
        if(StringUtils.isEmpty(dto.getEleAcct())){
            return "电子账户为空！";
        }
        if(StringUtils.isEmpty(dto.getSzType())){
            return "收支类型为空！";
        }
        if(StringUtils.isEmpty(dto.getTransAmt())){
            return "交易金额为空！";
        }
//        if(StringUtils.isEmpty(dto.getAdminId())){
//            return "用户id为空！";
//        }
//        if(StringUtils.isEmpty(dto.getAdminName())){
//            return "用户名为空！";
//        }
        if(StringUtils.isEmpty(dto.getPayType())){
            return "支付方式为空！";
        }
        if(StringUtils.isEmpty(dto.getBranch())){
            return "平台编号为空！";
        }
        BeanUtils.copyProperties(dto,fj );
        fj.setTransTime(new Date().getTime());//交易时间
        baseMapper.insert(fj);
       return OutEnum.SUCCESS.getCode();
    }
    @Override
    public String deleteJournal( Long id) {
//        FundJournal fj=new FundJournal();
//        baseMapper.updateById();
       return OutEnum.SUCCESS.getCode();
    }
    @Override
    public FundJournalOutputDTO selectById(Long id) {
        FundJournalOutputDTO fj=new FundJournalOutputDTO();
        FundJournal fundJournal = baseMapper.selectById(id);
        if(null==fundJournal){
            return null;
        }
        BeanUtils.copyProperties(fundJournal,fj );
        return fj;
    }

    @Override
    public FundJournalOutputDTO getFundJournal(FundJournalfindInputDTO dto) {
        FundJournal fj=new FundJournal();
        BeanUtils.copyProperties(dto,fj );
        FundJournal fundJournal = baseMapper.selectOne(fj);
        FundJournalOutputDTO out=new FundJournalOutputDTO();
        BeanUtils.copyProperties(fundJournal,out );
        return out;
    }*/
   /* @Override
    public FundJournalListOutputDTO findPage(FundJournalListInputDTO dto){
        FundJournalListOutputDTO response=new FundJournalListOutputDTO();
        EntityWrapper<FundJournal> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }
        if(StringUtils.isNotEmpty(dto.getId())){
            wrapper.eq("id",dto.getId());
        }
        if(StringUtils.isNotEmpty(dto.getJournalNo())){
            wrapper.eq("journal_no",dto.getJournalNo());
        }
        if(StringUtils.isNotEmpty(dto.getEleAcct())){
            wrapper.eq("ele_acct",dto.getEleAcct());
        }
        if(StringUtils.isNotEmpty(dto.getSzType())){
            wrapper.eq("sz_type",dto.getSzType());
        }
        if(StringUtils.isNotEmpty(dto.getAdminId())){
            wrapper.eq("admin_id",dto.getAdminId());
        }
        if(StringUtils.isNotEmpty(dto.getAdminName())){
            wrapper.eq("admin_name",dto.getAdminName());
        }
        if(StringUtils.isNotEmpty(dto.getPayType())){
            wrapper.eq("payType",dto.getPayType());
        }

        if(StringUtils.isNotEmpty(dto.getTimeStart()) && StringUtils.isNotEmpty(dto.getTimeEnd())){
            wrapper.between("create_time",dto.getTimeStart(),dto.getTimeEnd());
        }
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<FundJournal> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<FundJournalOutputDTO>  listpo=new ArrayList<FundJournalOutputDTO>();
        list.forEach((FundJournal temp) -> {
            FundJournalOutputDTO po=new FundJournalOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }
*/
}
