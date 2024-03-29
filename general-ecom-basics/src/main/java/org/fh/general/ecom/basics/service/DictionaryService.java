package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.Dictionary;
import org.fh.general.ecom.common.dto.basics.dictionary.*;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-08-14
 */
public interface DictionaryService extends IService<Dictionary> {

    public DictionaryListOutDTO findDicListByType(DictionaryListInDTO paramDto);

    public String addDictionary(InputDictionaryAddDTO dto);

    public String updateDictionary(InputDictionaryUpdateDTO dto);

    public String delDictionary(InputDictionaryDelDTO dto);

    public OutputDictionaryDetailDTO findDetail(InputDictionaryDetailDTO dto);

    public OutputDictionaryListDTO findPage(InputDictionaryListDTO dto);

    OutputDictionaryDetailDTO findLabelByValueAndType(InputDictionaryQueryDTO paramDto);
}
