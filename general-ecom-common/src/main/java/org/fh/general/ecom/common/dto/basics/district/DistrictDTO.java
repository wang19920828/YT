package org.fh.general.ecom.common.dto.basics.district;

import lombok.Data;

import java.util.List;

@Data
public class DistrictDTO {
    private Long districtId =0L;//区域id
    private String district = "";//名称
    private String pinCode="";//拼音
    private String areaCode="";//行政区号
    private String areaType="";//区域类型
    private String parentId="0";
    private String childSK = "";//
    private Integer priority = 0;//优先级
    private String sortCode = "";  //编码
    private Long creatorId = Long.parseLong("0");//创建人
    private Long createTime = System.currentTimeMillis();//创建时间


    private List<DistrictDTO> childList;

    public DistrictDTO() {
    }

    public DistrictDTO(Long districtId, String district, String pinCode, String areaCode, String areaType, String parentId, String childSK, Integer priority, String sortCode, Long creatorId, Long createTime) {
        this.districtId = districtId;
        this.district = district;
        this.pinCode = pinCode;
        this.areaCode = areaCode;
        this.areaType = areaType;
        this.parentId = parentId;
        this.childSK = childSK;
        this.priority = priority;
        this.sortCode = sortCode;
        this.creatorId = creatorId;
        this.createTime = createTime;
    }
}
