package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.OtherParameterService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.OtherParameter.*;
import org.fh.general.ecom.common.po.basics.OtherParameterOutPO.OtherParameterListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.OtherParameter.OtherParameterDetailVo;
import org.fh.general.ecom.common.vo.basics.OtherParameter.OtherParameterListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 其它参数 前端控制器
 * </p>
 *
 * @author wyy
 * @since 2018-10-09
 */
@RestController
public class OtherParameterController {
    @Autowired
    private OtherParameterService otherParameterService;
    /**
     *其他参数列表
     *
     * @param paramDto
     * @return
     */
    @RequestMapping("OP0001")
    public List<OtherParameterListVo> findDicListByType( OtherParameterListInDTO paramDto) {
         BaseVO baseVo = new BaseVO();
        List<OtherParameterListVo> voList = new ArrayList<OtherParameterListVo>();
        OtherParameterListOutDTO response =  this.otherParameterService.findOthListByType(paramDto);
        response.getList().forEach((OtherParameterListOutPO temp) -> {
            OtherParameterListVo voEn = new OtherParameterListVo();
            BeanUtils.copyProperties(temp, voEn);
            voList.add(voEn);
        });
        return voList;
    }



    @RequestMapping("OP0002")
    public BaseVO addDictionary(InputOtherParameterAddDTO dto) {
        BaseVO baseVo = new BaseVO();
        String msg = this.otherParameterService.addOtherParameter(dto);
        if(StringUtils.isNotEmpty(msg)){
            baseVo.setBusAlert(msg);
            return baseVo;
        }
        baseVo.success();
        return baseVo;
    }


    @RequestMapping("OP0003")
    public BaseVO updateOtherParameter(InputOtherParameterUpdateDTO dto) {
        BaseVO baseVo = new BaseVO();
        String msg = this.otherParameterService.updateOtherParameter(dto);
        if(StringUtils.isNotEmpty(msg)){
            baseVo.setBusAlert(msg);
            return baseVo;
        }
        baseVo.success();
        return baseVo;
    }


    @RequestMapping("OP0004")
    public BaseVO delOtherParameter(InputOtherParameterDelDTO dto) {
        BaseVO baseVo = new BaseVO();
        String msg = this.otherParameterService.delOtherParameter(dto);
        if(StringUtils.isNotEmpty(msg)){
            baseVo.setBusAlert(msg);
            return baseVo;
        }
        baseVo.success();
        return baseVo;
    }


    @RequestMapping("OP0005")
    public BaseVO findDetail(InputOtherParameterDetailDTO dto) {
        BaseVO baseVo = new BaseVO();
        OutputOtherParameterDetailDTO outputOtherParameterDetailDTO = this.otherParameterService.findDetail(dto);
        if(null==dto.getId()||dto.getId().equals("")){
            baseVo.mustParam();
            return baseVo;
        }
        if(outputOtherParameterDetailDTO==null){
            baseVo.noData();
            return baseVo;
        }
        OtherParameterDetailVo vo = new OtherParameterDetailVo();
        BeanUtils.copyProperties(outputOtherParameterDetailDTO,vo);
        baseVo.setData(vo);
        baseVo.success();
        return baseVo;
    }

    @RequestMapping("OP0006")
    public PagingVO findPage(InputOtherParameterListDTO dto){
        PagingVO pageVO = new PagingVO();
        OutputOtherParameterListDTO response =otherParameterService.findPage(dto);
        List<OtherParameterListVo> listvo= new ArrayList<OtherParameterListVo>();
        List<OutputOtherParameterDetailDTO>  list = response.getList();
        if(list!=null && list.size()>0) {
            list.forEach((OutputOtherParameterDetailDTO temp) -> {
                OtherParameterListVo vo = new OtherParameterListVo();
                BeanUtils.copyProperties(temp, vo);
                listvo.add(vo);
            });
            pageVO.success(listvo, response.getPageInfo());
        }else{
            pageVO.noData();
        }
        return pageVO;
    }


}
