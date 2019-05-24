package org.fh.general.ecom.common.dto.product.consulting;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

/**
 * @Author huliping
 * @DATE 2018/9/17
 **/
@Data
public class InputConsultingProjectTeamAddDTO {

    /**
     *姓名
     */
    @Check(empty = true,description ="姓名")
    private String name;
    /**
     * 年龄
     */
    @Check(empty = true,regexType = RegexType.NUMBER,description ="年龄")
    private Long age;
    /**
     * 职位
     */
    @Check(empty = true,description ="职位")
    private String position;
    /**
     * 从业经验
     */
    @Check(empty = true,description ="从业经验")
    private String practiceExperience;

}
