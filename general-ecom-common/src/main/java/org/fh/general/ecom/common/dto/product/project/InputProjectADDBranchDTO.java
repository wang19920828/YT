package org.fh.general.ecom.common.dto.product.project;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Author huliping
 * @DATE 2018/9/18
 **/
@Data
public class InputProjectADDBranchDTO {
    private  Long  id ;
    /**
     * 项目id
     */
    @Check(empty = false ,description ="项目id")
    private String projectId;
    @Check(empty = false ,description ="用户id")
    private String adminId;
    @Check(empty = false ,description ="姓名")
    private String name;
    @Check(empty = false ,description ="角色id")
    private String roleId;
    @Check(empty = false ,description ="创建id")
    private String createBy;
}
