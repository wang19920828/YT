package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.UserGuideService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideInputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.help.UserGuideListOutPo;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.help.UserGuideListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
 * @since 2018-09-21
 */
@RestController
public class UserGuideController {
    @Autowired
    private UserGuideService userGuideService;
    /**
     * 根据id查
     */
    @RequestMapping("USGD000001")
    public BaseVO selectByPrimaryKey (Long id){
        BaseVO baseVO = new BaseVO();
        UserGuideOutputDTO out=userGuideService.selectByPrimaryKey(id);
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
    @RequestMapping("USGD000002")
    public BaseVO insertUserGuide (UserGuideInputDTO dto){
        BaseVO baseVO = new BaseVO();
        dto.setType("0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转换格式
        dto.setCreateTime(sdf.format(new Date()));
        dto.setDel("0");
        String code=userGuideService.insertUserGuide(dto);
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
    @RequestMapping("USGD000003")
    public BaseVO updateUserGuide (UserGuideInputDTO dto){
        BaseVO baseVO = new BaseVO();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转换格式
        dto.setUpdateTime(sdf.format(new Date()));
        String code=userGuideService.updateUserGuide(dto);
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
    @RequestMapping("USGD000004")
    public BaseVO deleteByPrimaryKey (@RequestParam(name = "id", required = true) String id){
        BaseVO baseVO=new BaseVO();
        long l=Long.parseLong(id);
        String out=userGuideService.deleteByPrimaryKey(l);
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
    @RequestMapping("USGD000005")
    public PagingVO findPage(UserGuideListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            UserGuideListOutputDTO dtoEntity= this.userGuideService.findPage(dto);
            List<UserGuideListOutPo> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;            }
            List<UserGuideListVO> listvo=new ArrayList<UserGuideListVO>();
            list.forEach((UserGuideListOutPo temp) -> {
                UserGuideListVO voEn=new UserGuideListVO();
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
