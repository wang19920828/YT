package org.fh.general.ecom.product.client;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.basics.admin.AdminDetailOutputDTO;
import org.fh.general.ecom.common.dto.basics.dictionary.InputDictionaryQueryDTO;
import org.fh.general.ecom.common.dto.basics.dictionary.OutputDictionaryDetailDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageInsertInputDTO;
import org.fh.general.ecom.common.dto.product.user.OutputUserDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author huliping
 * @DATE 2018/9/21
 **/
@FeignClient(name = "basics")
public interface BasicClient {


    //查询项目预约总金额
    @RequestMapping("RUSER1001")
    public OutputUserDetailDTO findUserByUserId(@RequestBody Long userId    );

    @RequestMapping("DIST80003")
    public String findbySortCode(@RequestBody  String sortCode    );


    @RequestMapping("BAS90008")
    public OutputDictionaryDetailDTO findLabelByValueAndType(@RequestBody InputDictionaryQueryDTO dto    );

    @RequestMapping("RADMIN005")
    public AdminDetailOutputDTO findAdminEntityById(@RequestBody Long userId    );

    @RequestMapping("UMES000005")
    public BaseVO insertUserMessage (@RequestBody UserMessageInsertInputDTO dto);



}
