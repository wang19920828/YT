package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.goldTicket.GoldTicketAddInputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.GoldTicketListInputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.GoldTicketListOutputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.MyGoldTicketInputDTO;
import org.fh.general.ecom.common.po.order.goldTicket.GoldTicketListOutPO;
import org.fh.general.ecom.common.po.order.goldTicket.MyGoldOutPO;
import org.fh.general.ecom.order.model.GoldTicket;
import com.baomidou.mybatisplus.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 代金券表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface GoldTicketService extends IService<GoldTicket> {

    GoldTicketListOutputDTO findPage(GoldTicketListInputDTO dto)throws Exception;

    String addEntity(GoldTicketAddInputDTO dto)throws Exception;

    String delay(Long id) throws Exception;

    List<MyGoldOutPO> findMyGoldList(MyGoldTicketInputDTO dto);

    GoldTicketListOutPO findEntityByTicketSn(String ticketSn);

    Date getDelayTime(Date offTime);

}
