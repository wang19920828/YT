package org.fh.general.ecom.common.dto.basics.user.userMessage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/22 15:46
 * @Description:
 */
@Data
public class UserMessageAllOutputDTO {
    private String  mStatus;//站内信
    private String  mContent;
    private String pStatus; //项目
    private String pContent;
    private String eStatus ;//账户
    private String eContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date mTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date pTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date eTime;


}
