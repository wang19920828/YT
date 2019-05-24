package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.AdverService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.help.Adver.AdverInputDTO;
import org.fh.general.ecom.common.dto.basics.help.Adver.AdverListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.Adver.AdverListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.Adver.AdverOutputDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.help.AdverListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.help.AdverListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyy
 * @since 2018-10-26
 */
@RestController
public class AdverController {
    @Autowired
    private AdverService adverService;
    /**
     * 根据id查
     */
    @RequestMapping("AV000001")
    public BaseVO selectByPrimaryKey (Long adverId){
        BaseVO baseVO = new BaseVO();
        if(null==adverId||adverId.equals("")){
            baseVO.mustParam();
            return baseVO;
        }
        AdverOutputDTO out=adverService.selectByPrimaryKey(adverId);
        if(out!=null){
            BeanUtils.copyProperties(out,baseVO);
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
    @RequestMapping("AV000002")
    public BaseVO insertUserGuide (AdverInputDTO dto){
        BaseVO baseVO = new BaseVO();
        dto.setLinkSrc(ComEnum.IsDelete.NORMAL.getValue());//写入删除状态
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转换格式
        dto.setCreateTime(sdf.format(new Date()));
        String code=adverService.insertAdver(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            baseVO.setBusAlert(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 修改
     */
    @RequestMapping("AV000003")
    public BaseVO updateUserGuide (AdverInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=adverService.updateAdver(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            baseVO.setBusAlert(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     * 根据id删除
     */
    @RequestMapping("AV000004")
    public BaseVO deleteByPrimaryKey (Long adverId){
        BaseVO baseVO=new BaseVO();
        String out=adverService.deleteByPrimaryKey(adverId);
        if(baseVO!=null){
            baseVO.success();
        }else{
            baseVO.noData();
        }
        return baseVO;
    }

    /**
     *修改发布状态
     */
    @RequestMapping("AV000005")
    public BaseVO updatePublish (AdverInputDTO dto){
        BaseVO baseVO=new BaseVO();
        String out=adverService.updatePublish(dto);
        if(baseVO!=null){
            baseVO.success();
        }else{
            baseVO.noData();
        }
        return baseVO;
    }

    /**
     * 分页列表
     * */
    @RequestMapping("AV000006")
    public PagingVO findPage(AdverListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            AdverListOutputDTO dtoEntity= this.adverService.findPage(dto);
            List<AdverListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;            }
            List<AdverListVO> listvo=new ArrayList<AdverListVO>();
            list.forEach((AdverListOutPO temp) -> {
                AdverListVO voEn=new AdverListVO();
                BeanUtils.copyProperties(temp,voEn);
                listvo.add(voEn);
            });

            pagingVO.success(listvo,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }
}
