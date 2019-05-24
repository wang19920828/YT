package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fh.general.ecom.basics.dao.TransJournalDao;
import org.fh.general.ecom.basics.model.TransJournal;
import org.fh.general.ecom.basics.service.DictionaryService;
import org.fh.general.ecom.basics.service.TransJournalService;
import org.fh.general.ecom.basics.service.UserService;
import org.fh.general.ecom.common.comm.ExcelHeader;
import org.fh.general.ecom.common.comm.ExportEntity;
import org.fh.general.ecom.common.dto.basics.dictionary.InputDictionaryQueryDTO;
import org.fh.general.ecom.common.dto.basics.dictionary.OutputDictionaryDetailDTO;
import org.fh.general.ecom.common.dto.basics.user.UserPhoneOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.fundJournal.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.upload.UploadService;
import org.fh.general.ecom.common.upload.UploadServiceImpl;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.utils.ExcelUtils;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
@Service
@Slf4j
public class TransJournalServiceImpl extends ServiceImpl<TransJournalDao, TransJournal> implements TransJournalService {
    /**
     * 支付状态-支付中
     */
    private final static String PAY_STATUS_IN = "0101";

    /**
     * 支付状态-支付成功
     */
    private final static String PAY_STATUS_SUCCESS = "0202";

    /**
     * 支付状态-支付失败
     */
    private final static String PAY_STATUS_FAIL = "0303";

    @Autowired
    private UserService userService;
    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public TransJournal findJournal(String journalNo) throws Exception {
        TransJournal tj=new TransJournal();
        tj.setJournalNo(journalNo);
        return baseMapper.selectOne(tj);
    }
    @Override
    public String saveTransJournal(TransJournal journal) throws Exception {
        if(journal.getAddTime()==null){
            journal.setAddTime(new Date().getTime());
        }
        if(StringUtils.isNotEmpty(journal.getUserId())){
            UserPhoneOutputDTO phoneOne = userService.findPhoneOne(journal.getUserId());
            journal.setAccountNo(phoneOne.getAccount());
            journal.setCashAccount(phoneOne.getCashAmount());
        }
        this.baseMapper.insert(journal);
        return OutEnum.SUCCESS.getCode();
    }
    /**
     * 添加订单支付流水
     */
    @Override
    public String addTransJournal(TransJournal journal) throws Exception {
        if(StringUtils.isNotBlank(journal.getOrderSn())){
            TransJournal tj=new TransJournal();
            tj.setOrderSn(journal.getOrderSn());
            TransJournal transJournal = baseMapper.selectOne(tj);
            journal.setTransStuts(PAY_STATUS_IN);
            if(null != transJournal){
                //支付中或支付失败的流水进行更新
                if(PAY_STATUS_IN.equals(transJournal.getTransStuts())||PAY_STATUS_FAIL.equals(transJournal.getTransStuts())){
                    journal.setId(transJournal.getId());
                    log.info("-------------JournalNo:"+journal.getJournalNo());
                    baseMapper.updateById(journal);
                    return OutEnum.SUCCESS.getCode();
                }else{
                    return "此订单已经支付成功，请勿重复支付";
                }
            }else{
                this.baseMapper.insert(journal);
                return OutEnum.SUCCESS.getCode();
            }
        }else{
            this.baseMapper.insert(journal);
            return OutEnum.SUCCESS.getCode();
        }
    }
    @Override
    public void mdifyJournal(TransJournal journal) {
        baseMapper.updateById(journal);
    }
    @Override
    public TransJournal findByParams(Map<String, Object> queryParams) {
        return baseMapper.findByParams(queryParams);
    }


    @Override
    public FundJournalListOutputDTO findPage(FundJournalListInputDTO dto){
        FundJournalListOutputDTO response=new FundJournalListOutputDTO();
        EntityWrapper<TransJournal> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
            wrapper.eq("branch",dto.getBranch());
            wrapper.eq("state","1");
            wrapper.eq("trans_stuts","0202");
        if(StringUtils.isNotEmpty(dto.getId())){
            wrapper.eq("id",dto.getId());
        }
        if(StringUtils.isNotEmpty(dto.getJournalNo())){
            wrapper.eq("journal_no",dto.getJournalNo());
        }
        if(StringUtils.isNotEmpty(dto.getPayType())){
            wrapper.eq("pay_type",dto.getPayType());
        }
        if(StringUtils.isNotEmpty(dto.getOrderSn())){
            wrapper.eq("order_sn",dto.getOrderSn());
        }
        if(StringUtils.isNotEmpty(dto.getTransType())){
            wrapper.eq("trans_type",dto.getTransType());
        }
        if(StringUtils.isNotEmpty(dto.getTransStuts())){
            wrapper.eq("trans_stuts",dto.getTransStuts());
        }
        if(StringUtils.isNotEmpty(dto.getChannelJournal())){
            wrapper.eq("channel_journal",dto.getChannelJournal());
        }
        if(StringUtils.isNotEmpty(dto.getAccountNo())){
            wrapper.eq("account_no",dto.getAccountNo());
        }
        if(StringUtils.isNotEmpty(dto.getUserId())){
            wrapper.eq("user_id",dto.getUserId());
        }
        if(StringUtils.isNotEmpty(dto.getTimeStart()) && StringUtils.isNotEmpty(dto.getTimeEnd())){
            wrapper.between("add_time",dto.getTimeStart(),dto.getTimeEnd());
        }
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<TransJournal> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<FundJournalOutputDTO>  listpo=new ArrayList<FundJournalOutputDTO>();
        list.forEach((TransJournal temp) -> {
            FundJournalOutputDTO po=new FundJournalOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }
    @Override
    public FundJournalListOutputDTO findToPage(FundJournalListInputDTO dto){
       // 1-购买、2-充值、3-提现、4-退款、5-分红 10-预约消费 11-预约认购消费 12-公开认购消费，40-预约消费 41-预约认购消费 42-公开认购消费
        //************************将交易类型解开
        if(StringUtils.isNotEmpty(dto.getTransType())){
            if(dto.getTransType().equals("10")){
                dto.setTransType("1");dto.setOrderType("0");
            }else if(dto.getTransType().equals("11")){
                dto.setTransType("1");dto.setOrderType("1");
            }else if(dto.getTransType().equals("12")){
                dto.setTransType("1");dto.setOrderType("2");
            }else if(dto.getTransType().equals("40")){
                dto.setTransType("4");dto.setOrderType("0");
            }else if(dto.getTransType().equals("41")){
                dto.setTransType("4");dto.setOrderType("1");
            }else if(dto.getTransType().equals("42")){
                dto.setTransType("4");dto.setOrderType("2");
            }
        }
        FundJournalListOutputDTO response=new FundJournalListOutputDTO();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        List<FundJournalListOutPO>  list=baseMapper.finddownloadExcel(dto);
        PageInfo pageInfo=new PageInfo(list);
        List<FundJournalOutputDTO>  listpo=new ArrayList<FundJournalOutputDTO>();
        list.forEach((FundJournalListOutPO temp) -> {
            FundJournalOutputDTO po=new FundJournalOutputDTO();
            BeanUtils.copyProperties(temp,po);
            po.setTransType(getTypeText(temp.getTransType(),temp.getOrderType()));
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }

    @Override
    public String downloadExcel(FundJournalListInputDTO dto) throws Exception {
        //************************将交易类型解开
        if(StringUtils.isNotEmpty(dto.getTransType())){
            if(dto.getTransType().equals("10")){
                dto.setTransType("1");dto.setOrderType("0");
            }else if(dto.getTransType().equals("11")){
                dto.setTransType("1");dto.setOrderType("1");
            }else if(dto.getTransType().equals("12")){
                dto.setTransType("1");dto.setOrderType("2");
            }else if(dto.getTransType().equals("40")){
                dto.setTransType("4");dto.setOrderType("0");
            }else if(dto.getTransType().equals("41")){
                dto.setTransType("4");dto.setOrderType("1");
            }else if(dto.getTransType().equals("42")){
                dto.setTransType("4");dto.setOrderType("2");
            }
        }
        List<FundJournalListOutPO>  list=baseMapper.finddownloadExcel(dto);
        List<FundJournalExcelOutPO>  listpo=new ArrayList<>();
        list.forEach((FundJournalListOutPO temp) -> {
            FundJournalExcelOutPO po=new FundJournalExcelOutPO();
            po.setJournalNo(temp.getJournalNo());
            if(StringUtils.isNotEmpty(temp.getOrderSn())){
            po.setOrderSn(temp.getOrderSn());}
            po.setOverTime(DateUtils.formatDate(temp.getOverTime(),"yyyy-MM-dd HH:mm:ss"));
            po.setPayType(Long.valueOf(temp.getPayType())==1L?"电子账户":"微信");
            po.setTransAmt(temp.getTransAmt());
            po.setTransType(getTypeText(temp.getTransType(),temp.getOrderType()));
            listpo.add(po);
        });
        Map<String, Object> map = new HashMap();

        map.put("list", listpo);
        map.put("titles", this.setTitleList());

        String json = JSONObject.fromObject(map).toString();
        ExportEntity entity = ExportEntity.resultToExport(json);
        UploadService uploadService = new UploadServiceImpl();
        HSSFWorkbook workbook = ExcelUtils.export(entity, 17f, (short) 20, "");
        String filePath="";
        if (workbook != null) {
            // 获取当前时间用作文件名
            String filename = DateUtils.formatDateForWx(System.currentTimeMillis()) + ".xls";
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            filePath = uploadService.uploadFile(os.toByteArray(), filename);
        }
        return filePath;
    }
    private String getTypeText(String transType,String orderType){
        //交易类型( 1-购买、2-充值、3-提现、4-退款、5-分红)  订单类型: 0-预约 1-预约认购 2-公开认购
        if(transType.equals("1")||transType.equals("4")){
            String str1 = findLabel(orderType, "order_type");
            String str2 = findLabel(transType, "trans_type");
            return str1+str2;
        }
        return findLabel(transType, "trans_type");
    }
    private String findLabel(String str,String type){
        InputDictionaryQueryDTO ll=new InputDictionaryQueryDTO();
        ll.setType(type);
        if(StringUtils.isEmpty(str)){
            return "";
        }
        ll.setValue(str);
        OutputDictionaryDetailDTO labelByValueAndType = dictionaryService.findLabelByValueAndType(ll);
        return labelByValueAndType.getLabel();
    }
    private List<ExcelHeader> setTitleList(){
        List<ExcelHeader> titleList = new ArrayList<ExcelHeader>();
        ExcelHeader header1 = new ExcelHeader("journalNo", "支付流水号", 0);
        ExcelHeader header2 = new ExcelHeader("orderSn", "订单编号", 0);
        ExcelHeader header3 = new ExcelHeader("transType", "交易类型", 0);
        ExcelHeader header4 = new ExcelHeader("transAmt", "交易金额", 0);
        ExcelHeader header5 = new ExcelHeader("payType", "支付方式", 0);
        ExcelHeader header6 = new ExcelHeader("overTime", "交易时间", 0);

        titleList.add(header1);
        titleList.add(header2);
        titleList.add(header3);
        titleList.add(header4);
        titleList.add(header5);
        titleList.add(header6);

        return titleList;
    }


}
