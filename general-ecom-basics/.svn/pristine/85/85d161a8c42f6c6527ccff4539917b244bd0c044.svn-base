package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.basics.dao.JournalSnDao;
import org.fh.general.ecom.basics.model.JournalSn;
import org.fh.general.ecom.basics.service.JournalSnService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-10-08
 */
@Service
public class JournalSnServiceImpl extends ServiceImpl<JournalSnDao, JournalSn> implements JournalSnService {
    private static final String  REFUND_JOURNAL_NAME = "refund_journal_sn";
    private static final String  PAY_JOURNAL_NAME = "pay_journal_sn";
    private static final String  ORDER_JOURNAL_NAME = "order_journal_sn";
    private static final int JOURNAL_DATE_LENGTH = 8;

    @Override
    public String orderJournalSn() {
        String journal_sn = "";
        Map<String, Object> param = new ConcurrentHashMap<String, Object>();
        param.put("journal_name", ORDER_JOURNAL_NAME);
        param.put("num", JOURNAL_DATE_LENGTH);
        param.put("len", 7);
        journal_sn = this.baseMapper.generateJournal(param);
        return journal_sn;
    }
    @Override
    public String payJournalSn() {
        String journal_sn = "";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("journal_name", PAY_JOURNAL_NAME);
        param.put("num", 14);
        param.put("len", 6);
        journal_sn = this.baseMapper.generateJournal(param);
        return journal_sn;
    }
    @Override
    public String refundJournalSn() {
        String journal_sn = "";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("journal_name", REFUND_JOURNAL_NAME);
        param.put("num", JOURNAL_DATE_LENGTH);
        param.put("len", 9);
        journal_sn = this.baseMapper.generateJournal(param);

        return journal_sn;
    }
}
