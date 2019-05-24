package org.fh.general.ecom.common.dto.basics.help.GuideArticle;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
    @Data
public class GuideArticleListInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
    private  String guideId;
    private  String startTime;
    private  String endTime;
    private  String type;
}
