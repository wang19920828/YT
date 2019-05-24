package org.fh.general.ecom.common.vo.product.consulting;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Author huliping
 * @DATE 2018/9/17
 **/
@Data
public class ConsultingProjectTeamVO  implements Serializable{

    /**
     *姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Long age;
    /**
     * 职位
     */
    private String position;
    /**
     * 从业经验
     */
    private String practiceExperience;

}
