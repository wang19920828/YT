package org.fh.general.ecom.basics.controller;

import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.service.UserGuidePlayService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.help.UserGuidePlay.*;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyy
 * @since 2018-10-22
 */
@RestController
public class UserGuidePlayController {
    @Autowired
    private UserGuidePlayService userGuidePlayService;
    /**
     * 根据id查
     */
    @RequestMapping("USGP000001")
    public BaseVO selectByPrimaryKey (Long id){
        BaseVO baseVO = new BaseVO();
        UserGuidePlayOutputDTO out=userGuidePlayService.selectByPrimaryKey(id);
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
    @RequestMapping("USGP000002")
    public BaseVO insertUserGuidePlay (UserGuidePlayInputDTO dto){
        BaseVO baseVO = new BaseVO();
        /* dto.setType(ComEnum.NewsOrGuide.NEWS.getValue());*/
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转换格式
        dto.setDel(ComEnum.IsDelete.NORMAL.getValue());
        String code=userGuidePlayService.insertUserGuidePlay(dto);
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
    @RequestMapping("USGP000003")
    public BaseVO updateUserGuidePlay (UserGuidePlayInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=userGuidePlayService.updateUserGuidePlay(dto);
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
    @RequestMapping("USGP000004")
    public BaseVO deleteByPrimaryKey (@RequestParam(name = "id", required = true) String id){
        BaseVO baseVO=new BaseVO();
        long l=Long.parseLong(id);
        String out=userGuidePlayService.deleteByPrimaryKey(l);
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
    @RequestMapping("USGP000005")
    public PagingVO findPage(UserGuidePlayListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {

            UserGuidePlayListOutputDTO dtoEntity= this.userGuidePlayService.findPage(dto);
            List<UserGuidePlayListOutPo> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;            }
            List<UserGuidePlayListVO> listvo=new ArrayList<UserGuidePlayListVO>();
            list.forEach((UserGuidePlayListOutPo temp) -> {
                UserGuidePlayListVO voEn=new UserGuidePlayListVO();
                BeanUtils.copyProperties(temp,voEn);
                listvo.add(voEn);
            });

            pagingVO.success(listvo,new PageInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }
}
