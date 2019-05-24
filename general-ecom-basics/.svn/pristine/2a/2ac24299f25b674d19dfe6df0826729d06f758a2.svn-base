package org.fh.general.ecom.basics.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.fh.general.ecom.basics.model.TransJournal;
import org.fh.general.ecom.common.dto.basics.user.fundJournal.FundJournalListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.fundJournal.FundJournalListOutPO;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
@Mapper
public interface TransJournalDao extends BaseMapper<TransJournal> {
    TransJournal findByParams(Map<String, Object> queryParams);
    List<FundJournalListOutPO> finddownloadExcel(FundJournalListInputDTO paramPO);
}