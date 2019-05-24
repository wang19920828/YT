package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.TransJournal;
import org.fh.general.ecom.common.dto.basics.user.fundJournal.FundJournalListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.fundJournal.FundJournalListOutputDTO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
public interface TransJournalService extends IService<TransJournal> {
    /**
     * 添加订单支付流水信息
     * @param journal
     * @throws Exception
     */
    public String addTransJournal(TransJournal journal)throws Exception;

    public String saveTransJournal(TransJournal journal) throws Exception;

    public TransJournal findJournal(String journalNo)throws Exception;
    /**
     * 修改支付支付流水信息
     * @param journal
     */
    public void mdifyJournal(TransJournal journal);
    public TransJournal findByParams(Map<String, Object> queryParams);
    FundJournalListOutputDTO findPage(FundJournalListInputDTO dto);
    FundJournalListOutputDTO findToPage(FundJournalListInputDTO dto);
    String downloadExcel(FundJournalListInputDTO dto) throws Exception;
}
