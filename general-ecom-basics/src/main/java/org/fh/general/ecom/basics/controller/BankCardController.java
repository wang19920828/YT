package org.fh.general.ecom.basics.controller;

import net.sf.json.JSONObject;
import org.fh.general.ecom.basics.service.BankCardService;
import org.fh.general.ecom.basics.service.DictionaryService;
import org.fh.general.ecom.basics.service.PhoneVacodeService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.comm.OutCodeEntity;
import org.fh.general.ecom.common.dto.basics.dictionary.InputDictionaryQueryDTO;
import org.fh.general.ecom.common.dto.basics.dictionary.OutputDictionaryDetailDTO;
import org.fh.general.ecom.common.dto.basics.user.bankCard.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户银行卡信息
 *
 * @author wzy
 * @since 2018-09-18
 */

@RestController
public class BankCardController {
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private PhoneVacodeService phoneVacodeService;

    /**
     *删除
     */
    @RequestMapping("BC000001")
    public BaseVO deleteById (BankCardUpdateInputDTO dto){
        BaseVO baseVO=new BaseVO();
        //短信验证************************
        OutCodeEntity outCodeEntity = phoneVacodeService.verifyPhoneVacode(dto.getPhone(), dto.getVaCode(), null, dto.getBranch());
        if(!OutEnum.SUCCESS.getCode().equals(outCodeEntity.getCode())){
            baseVO.error(outCodeEntity.getMessage());
            return baseVO;
        }
        String code=this.bankCardService.deleteByPrimaryKey(dto.getId());
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return  baseVO;
    }


    /**
     * 根据id查
     */
    @RequestMapping("BC000002")
    public BaseVO selectByPrimaryKey (Long id){
        BaseVO baseVO = new BaseVO();
        BankCardOutputDTO out=bankCardService.selectByPrimaryKey(id);
        if(out!=null){
            baseVO.success(out);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }

    /**
     * 添加
     * @param dto
     */
    @RequestMapping("BC000003")
    public BaseVO insertBankCard (BankCardInsertInputDTO dto){
        BaseVO baseVO = new BaseVO();
        //短信验证************************
        OutCodeEntity outCodeEntity = phoneVacodeService.verifyPhoneVacode(dto.getPhone(), dto.getVaCode(), null, dto.getBranch());
        if(!OutEnum.SUCCESS.getCode().equals(outCodeEntity.getCode())){
            baseVO.error(outCodeEntity.getMessage());
            return baseVO;
        }
        String code=bankCardService.insertBankCard(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.warn(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     * 修改
     * @param dto
     */
    @RequestMapping("BC000004")
    public BaseVO updateByPrimaryKeySelective (BankCardUpdateInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=bankCardService.updateByPrimaryKeySelective(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 用户卡集合查询
     * @return
     */
    @RequestMapping("BC000005")
    public BaseVO userBankCardList(Long userId) {
        BaseVO baseVO = new BaseVO();
        BankCardListOutputDTO out=bankCardService.userBankCardList(userId);
        if(0==out.getBankCardListOutput().size()){
            baseVO.noData();
        }else{
            baseVO.success(out);
        }
        return baseVO;
    }
    /**
     * 银行卡简单验证
     */
    @RequestMapping("BC000006")
    public BaseVO verifyBankCard(String accountNo) {
        BaseVO baseVO = new BaseVO();
        BankCardVerifyVO vo=new BankCardVerifyVO();
        String url="https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo="+accountNo+"&cardBinCheck=true";
        String s = HttpUtils.doGet(url);
        JSONObject json = JSONObject.fromObject(s);
        Boolean validated = (Boolean) json.get("validated");
        if(validated){
            vo.setValidated("1");
            String bankName=(String)json.get("bank");
            InputDictionaryQueryDTO paramDto=new InputDictionaryQueryDTO();
            paramDto.setType("bank");
            paramDto.setValue(bankName);
            OutputDictionaryDetailDTO labelByValueAndType = dictionaryService.findLabelByValueAndType(paramDto);
            vo.setBank(labelByValueAndType.getLabel());
            vo.setCardType((String) json.get("cardType"));
        }else{
            vo.setValidated("0");
        }
        vo.setKey(accountNo);
        vo.setStat((String) json.get("stat"));
        baseVO.success(vo);
        return baseVO;
    }
    /**
     * 根据UserId查银行卡
     */
    @RequestMapping("BC000007")
    public BaseVO selectByPrimaryUserId (Long userId){
        BaseVO baseVO = new BaseVO();
        BankCardOutputDTO out=bankCardService.selectByPrimaryUserId(userId);
        if(out!=null){
            baseVO.success(out);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }

}
