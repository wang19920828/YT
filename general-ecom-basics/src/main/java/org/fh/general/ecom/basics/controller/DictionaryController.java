package org.fh.general.ecom.basics.controller;


import org.fh.general.ecom.basics.service.DictionaryService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.dictionary.*;
import org.fh.general.ecom.common.po.basics.dictionary.DicListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.dictionary.DictionaryDetailVo;
import org.fh.general.ecom.common.vo.basics.dictionary.DictionaryListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-08-14
 */
@RestController
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 数据字典列表项
     *
     * @param paramDto
     * @return
     */
    @RequestMapping("BAS80001")
    public BaseVO  findDicListByType(DictionaryListInDTO paramDto) {
        BaseVO baseVo = new BaseVO();
        List<DictionaryListVo> voList = new ArrayList<DictionaryListVo>();
        DictionaryListOutDTO response = this.dictionaryService.findDicListByType(paramDto);
        if(response.getList()!=null) {
            response.getList().forEach((DicListOutPO temp) -> {
                DictionaryListVo voEn = new DictionaryListVo();
                BeanUtils.copyProperties(temp, voEn);
                voList.add(voEn);
            });
            baseVo.success();
            baseVo.setData(voList);
        }else{baseVo.noData();}
        return baseVo;
    }




    @RequestMapping("BAS90002")
    public BaseVO addDictionary(InputDictionaryAddDTO dto) {
        BaseVO baseVo = new BaseVO();
        String msg = this.dictionaryService.addDictionary(dto);
        if(StringUtils.isNotEmpty(msg)){
            baseVo.setBusAlert(msg);
            return baseVo;
        }
        baseVo.success();
        return baseVo;
    }

    @RequestMapping("BAS90003")
    public BaseVO updateDictionary(InputDictionaryUpdateDTO dto) {
        BaseVO baseVo = new BaseVO();
        String msg = this.dictionaryService.updateDictionary(dto);
        if(StringUtils.isNotEmpty(msg)){
            baseVo.setBusAlert(msg);
            return baseVo;
        }
        baseVo.success();
        return baseVo;
    }


    @RequestMapping("BAS90004")
    public BaseVO delDictionary(InputDictionaryDelDTO dto) {
        BaseVO baseVo = new BaseVO();
        String msg = this.dictionaryService.delDictionary(dto);
        if(StringUtils.isNotEmpty(msg)){
            baseVo.setBusAlert(msg);
            return baseVo;
        }
        baseVo.success();
        return baseVo;
    }

    @RequestMapping("BAS90005")
    public BaseVO findDetail(InputDictionaryDetailDTO dto) {
        BaseVO baseVo = new BaseVO();
        OutputDictionaryDetailDTO outputDictionaryDetailDTO = this.dictionaryService.findDetail(dto);
        if(outputDictionaryDetailDTO==null){
             baseVo.noData();
            return baseVo;
        }
        DictionaryDetailVo vo = new DictionaryDetailVo();
        BeanUtils.copyProperties(outputDictionaryDetailDTO,vo);
        baseVo.setData(vo);
        baseVo.success();
        return baseVo;
    }

    @RequestMapping("BAS90006")
    public PagingVO findPage(InputDictionaryListDTO dto){
        PagingVO pageVO = new PagingVO();
        OutputDictionaryListDTO response =dictionaryService.findPage(dto);
        List<DictionaryListVo> listvo= new ArrayList<DictionaryListVo>();
        List<OutputDictionaryDetailDTO>  list = response.getList();
        if(list!=null && list.size()>0) {
            list.forEach((OutputDictionaryDetailDTO temp) -> {
                DictionaryListVo vo = new DictionaryListVo();
                BeanUtils.copyProperties(temp, vo);
                listvo.add(vo);
            });
            pageVO.success(listvo, response.getPageInfo());
        }else{
            pageVO.noData();
        }
        return pageVO;
    }
    @RequestMapping("BAS90007")
    public List<DictionaryListVo> findDicListByKey(@RequestBody   DictionaryListInDTO paramDto) {
        DictionaryListOutDTO response = this.dictionaryService.findDicListByType(paramDto);
        if(response==null){
            return null;
        }
        List<DictionaryListVo> voList = new ArrayList<DictionaryListVo>();
        response.getList().forEach((DicListOutPO temp) -> {
            DictionaryListVo voEn = new DictionaryListVo();
            BeanUtils.copyProperties(temp, voEn);
            voList.add(voEn);
        });
        return voList;
    }


    @RequestMapping("BAS90008")
    public OutputDictionaryDetailDTO findLabelByValueAndType(@RequestBody   InputDictionaryQueryDTO paramDto) {
        BaseVO baseVo = new BaseVO();
        List<DictionaryListVo> voList = new ArrayList<DictionaryListVo>();
        OutputDictionaryDetailDTO dto  = this.dictionaryService.findLabelByValueAndType(paramDto);
        return dto;
    }

}
