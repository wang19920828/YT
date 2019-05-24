package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.model.TransJournal;
import org.fh.general.ecom.basics.service.TransJournalService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.user.fundJournal.FundJournalListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.fundJournal.FundJournalListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.fundJournal.FundJournalOutputDTO;
import org.fh.general.ecom.common.dto.order.order.ExcelOutDTO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
@RestController
public class TransJournalController {
    @Autowired
    private TransJournalService transJournalService;

    /**
     * 查根据Id查
     */
    @RequestMapping("FJ000003")
    public BaseVO selectById(Long id){
        BaseVO baseVO = new BaseVO();
        TransJournal transJournal = transJournalService.selectById(id);
        if(transJournal==null){
            baseVO.noData();
            return  baseVO;
        }
        baseVO.success(transJournal);
        return baseVO;
    }

    /**
     * 分页列表
     * */
    @RequestMapping("FJ000004")
    public PagingVO findPage(FundJournalListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            FundJournalListOutputDTO dtoEntity= this.transJournalService.findPage(dto);
            List<FundJournalOutputDTO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            pagingVO.success(list,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }
    /**
     * 导出流水excel
     * @param dto
     * @return
     */
    @RequestMapping("FJ000005")
    public BaseVO downLoadExcel(FundJournalListInputDTO dto){
        BaseVO baseVO=new BaseVO();
        try{
            String downpath = this.transJournalService.downloadExcel(dto);
            if(StringUtils.isNotEmpty(downpath)){
                ExcelOutDTO outDTO=new ExcelOutDTO();
                outDTO.setDownpath(downpath);
                baseVO.success(outDTO);
            }else{
                baseVO.error("导出流水excel出错");
            }
        }catch (Exception e){
            e.printStackTrace();
            baseVO.exception();
        }
        return baseVO;
    }
    /**
     * 资金流水分页列表
     * */
    @RequestMapping("FJ000002")
    public PagingVO findToPage(FundJournalListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            FundJournalListOutputDTO dtoEntity= this.transJournalService.findToPage(dto);
            List<FundJournalOutputDTO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            pagingVO.success(list,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }
}
