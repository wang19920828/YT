package org.fh.general.ecom.product.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingExtensionVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.product.order.OutputOperUserListDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperAddDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperLogListDTO;
import org.fh.general.ecom.common.dto.product.projectlog.OutputProjectLogDetailDTO;
import org.fh.general.ecom.common.dto.product.projectlog.OutputProjectOperLogListDTO;
import org.fh.general.ecom.common.dto.product.projectlog.UpdateOperaLogInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.enums.ProjectEnum;
import org.fh.general.ecom.common.vo.product.projectlog.ProjectLogDetailVO;
import org.fh.general.ecom.common.vo.product.projectlog.UserListVO;
import org.fh.general.ecom.product.service.ProjectOperLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hlp
 * @since 2018-09-20
 */
@RestController
public class ProjectOperLogController {

    @Autowired
    private ProjectOperLogService projectOperLogService;
    //查询项目的日志
    @RequestMapping("P000011")
    public PagingVO findLogByProjectId(InputProjectOperLogListDTO dto){
        PagingVO pageVO = new PagingVO();
        OutputProjectOperLogListDTO response =projectOperLogService.findPage(dto);
        List<ProjectLogDetailVO> listvo= new ArrayList<ProjectLogDetailVO>();
        List<OutputProjectLogDetailDTO>  list = response.getList();
        if(list!=null && list.size()>0) {
            list.forEach((OutputProjectLogDetailDTO temp) -> {
                ProjectLogDetailVO vo = new ProjectLogDetailVO();
                BeanUtils.copyProperties(temp, vo);
                vo.setProjectSource(ProjectEnum.ProjectSource.codeOf(temp.getProjectSource()).getName());
                listvo.add(vo);
            });
            pageVO.success(listvo, response.getPageInfo());
        }else{
            pageVO.noData();
        }
        return pageVO;
    }


    //查询投资人分页列表
    @RequestMapping("P000012")
    public PagingExtensionVO findUserListByProjectId(InputProjectOperLogListDTO dto){
       return  projectOperLogService.findUserListPage(dto);

    }

    //查询投资人分页列表
    @RequestMapping("P000013")
    public BaseVO findCountUser(InputProjectOperLogListDTO dto){
        BaseVO baseVO=new BaseVO();
        OutputOperUserListDTO outputOperUserListDTO  = projectOperLogService.findCountUser(dto);
        if(outputOperUserListDTO!=null){
            UserListVO vo = new UserListVO();
            BeanUtils.copyProperties(outputOperUserListDTO,vo);
            baseVO.success(vo);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }



    @RequestMapping("RP000013")
    public BaseVO insertProjectOperLog(@RequestBody InputProjectOperAddDTO dto){
        BaseVO baseVO = new BaseVO();
        String code =   projectOperLogService.insertEntity(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error((OutEnum.FAIL.getMessage()));
            return baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    //更新认购+预约操作日志成功or失败状态
    @RequestMapping("RRP000014")
    public BaseVO updateOperaLogRenGouStatus(@RequestBody UpdateOperaLogInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code =   projectOperLogService.updateOperaLogRenGouStatus(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error((OutEnum.FAIL.getMessage()));
            return baseVO;
        }
        baseVO.success();
        return baseVO;
    }





}
