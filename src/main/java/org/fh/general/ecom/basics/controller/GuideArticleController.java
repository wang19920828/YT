package org.fh.general.ecom.basics.controller;


import org.fh.general.ecom.basics.service.GuideArticleService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleInputDTO;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyy
 * @since 2018-09-21
 */
@RestController
public class GuideArticleController {
    @Autowired
    private GuideArticleService guideArticleService;
    /**
     * 根据id查
     */
    @RequestMapping("GA000001")
    public BaseVO selectByPrimaryKey (Long id){
        BaseVO baseVO = new BaseVO();
        GuideArticleOutputDTO out=guideArticleService.selectByPrimaryKey(id);
        BeanUtils.copyProperties(out,baseVO);
        if(baseVO!=null){
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
    @RequestMapping("GA000002")
    public BaseVO insertUserGuide (GuideArticleInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=guideArticleService.insertUserGuide(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 修改
     */
    @RequestMapping("GA000003")
    public BaseVO updateUserGuide (GuideArticleInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=guideArticleService.updateGuideArticle(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     * 根据id删除
     */
    @RequestMapping("GA000004")
    public BaseVO deleteByPrimaryKey (Long id){
        BaseVO baseVO=new BaseVO();
        String out=guideArticleService.deleteByPrimaryKey(id);
        if(baseVO!=null){
            baseVO.success();
        }else{
            baseVO.noData();
        }
        return baseVO;
    }
}
