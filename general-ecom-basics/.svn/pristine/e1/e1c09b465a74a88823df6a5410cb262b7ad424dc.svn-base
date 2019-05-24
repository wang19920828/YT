package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.Customer;
import org.fh.general.ecom.common.dto.basics.user.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-17
 */
public interface CustomerService extends IService<Customer> {
    public String addEntity(CustomerAddInputDTO dto)throws Exception;

    public String deleteEntityById(Long custId)throws Exception;

    public String updateEntity(CustomerUpdateInputDTO dto)throws Exception;

    public CustomerOutputDTO findEntityById(Long custId)throws Exception;

    public CustomerOutputDTO findEntityByUserId(Long userId)throws Exception;

    public CustomerListOutputDTO findPage(CustomerListInputDTO dto)throws Exception;



}
