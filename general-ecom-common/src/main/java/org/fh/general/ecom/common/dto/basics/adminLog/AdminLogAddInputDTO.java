package org.fh.general.ecom.common.dto.basics.adminLog;

import lombok.Data;

@Data
public class AdminLogAddInputDTO {

    private Long adminId;

    private Long roleId;

    private String operModule;

    private String operResult;

    private String remark;

}
