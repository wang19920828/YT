package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class InputProjectAllAddDTO {


    private String  inputProjectAddMainStr;
    private String  inputProjectAddDetailStr;
    private String  inputProjectAddFinancingStr;
    private String  inputProjectAddProgrammeListStr;
    private String  inputProjectAddFilesListStr;


    private InputProjectAddMainDTO inputProjectAddMainDTO;
    //项目详情部分
    private InputProjectAddDetailDTO inputProjectAddDetailDTO;
    //筹资信息
    private InputProjectAddFinancingDTO inputProjectAddFinancingDTO;
    //方案信息
    private InputProjectAddProgrammeListDTO inputProjectAddProgrammeListDTO;
    //项目披露信息
    private  InputProjectAddFilesListDTO  inputProjectAddFilesListDTO;

    private  String  createBy;

    private String branch ;

    private String  branchName;


}
