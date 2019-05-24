package org.fh.general.ecom.common.dto.basics.dictionary;

import lombok.Data;
import org.fh.general.ecom.common.po.basics.dictionary.DicListOutPO;

import java.util.Dictionary;
import java.util.List;

@Data
public class DictionaryListOutDTO {

    private List<DicListOutPO> list;


}
