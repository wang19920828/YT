package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class InputProjectUpdateDTO {
    @Check(empty = false,description = "项目id")
    private Long  id ;


    private String projectAddMainStr;
    private String projectAddDetailStr;
    private String projectFinancingStr;
    private String projectProgrammeStr;
    private String projectFilesStr;


    private InputProjectAddMainDTO inputProjectAddMainDTO;
    //项目详情部分
    private InputProjectAddDetailDTO inputProjectAddDetailDTO;
   //筹资信息
    private InputProjectAddFinancingDTO inputProjectAddFinancingDTO;
    //方案信息
    private InputProjectAddProgrammeListDTO inputProjectAddProgrammeListDTO;
    //项目披露信息
    private  InputProjectAddFilesListDTO  inputProjectAddFilesListDTO;

    private  String  updateBy;



}
