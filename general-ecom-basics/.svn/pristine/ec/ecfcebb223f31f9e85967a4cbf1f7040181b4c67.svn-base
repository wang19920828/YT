package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.GuideArticleDao;
import org.fh.general.ecom.basics.model.GuideArticle;
import org.fh.general.ecom.basics.service.GuideArticleService;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleInputDTO;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleOutputDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.help.GuideArticleListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyy
 * @since 2018-09-21
 */
@Service
public class GuideArticleServiceImpl extends ServiceImpl<GuideArticleDao, GuideArticle> implements GuideArticleService {

    @Override
    public GuideArticleOutputDTO selectByPrimaryKey(Long id) {
        GuideArticleOutputDTO entity2=new GuideArticleOutputDTO();
        GuideArticle entity=baseMapper.selectById(id);
        if(null==entity){
            return null;
       }
        BeanUtils.copyProperties(entity,entity2);
        return entity2;
    }

    @Override
    public String insertUserGuide(GuideArticleInputDTO dto) {
        GuideArticle entity =new GuideArticle();
        BeanUtils.copyProperties(dto,entity);
        EntityWrapper<GuideArticle> wrapper = new EntityWrapper<GuideArticle>();
        wrapper.where("type={0}",ComEnum.NewsOrGuide.NEWS.getValue())
                .andNew("branch={0}",dto.getBranch()==null? ComEnum.Branch.YUN_TOU.getValue():dto.getBranch())
                .andNew("del={0}",ComEnum.IsDelete.NORMAL.getValue())
                .andNew("title={0}",dto.getTitle());
        int i =  baseMapper.selectCount(wrapper);
        if(i>0){
            return "该数据已经存在";
        }else {
            baseMapper.insert(entity);
            return OutEnum.SUCCESS.getCode();
        }
    }

    @Override
    public String updateGuideArticle(GuideArticleInputDTO dto) {
        GuideArticle entity =new GuideArticle();
        BeanUtils.copyProperties(dto,entity);
        GuideArticle article = this.baseMapper.selectById(dto.getId());
        if(article==null){
            return OutEnum.WARN.getMessage();
        }
        GuideArticle artbt = this.baseMapper.selectByTitle( entity.getTitle());
        if(null!=artbt){
            if(artbt.getId().equals(dto.getId())){
                baseMapper.updateById(entity);
                return OutEnum.SUCCESS.getCode();
            }
        }
       /* EntityWrapper<GuideArticle> wrapper = new EntityWrapper<GuideArticle>();
        wrapper.where("type={0}",ComEnum.NewsOrGuide.NEWS.getValue())
                .andNew("branch={0}",dto.getBranch()==null? ComEnum.Branch.YUN_TOU.getValue():dto.getBranch())
                .andNew("del={0}",ComEnum.IsDelete.NORMAL.getValue())
                .andNew("title={0}",dto.getTitle());
        int i =  baseMapper.selectCount(wrapper);
        if(i>0){
            return "该字典已经存在";
        }else{
            baseMapper.updateById(entity);
            return OutEnum.SUCCESS.getCode();
        }*/
            return "该标题已存在！";
    }

    @Override
    public String deleteByPrimaryKey(Long id) {
        GuideArticle entity=baseMapper.selectById(id);
        if(null!=entity){
            entity.setDel(ComEnum.IsDelete.DEL.getValue());
            baseMapper.updateById(entity);
            return OutEnum.SUCCESS.getCode();
        }else{
            return OutEnum.FAIL.getCode();
        }
    }

    @Override
    public String updatePublish(Long id) {
        GuideArticle entity=baseMapper.selectById(id);
        if(null!=entity){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转换格式
            entity.setPublishDate(sdf.format(new Date()));
            entity.setPublished(ComEnum.published.OREADY.getValue());
            baseMapper.updateById(entity);
            return OutEnum.SUCCESS.getCode();
        }else{

            return OutEnum.FAIL.getCode();
        }

    }

    @Override
    public GuideArticleListOutputDTO findPage(GuideArticleListInputDTO dto) {
        GuideArticleListOutputDTO response=new GuideArticleListOutputDTO();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        EntityWrapper<GuideArticle> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(dto.getStartTime())){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String first=null;
            String last=null;
            if(dto.getStartTime().equals("0")){//StartTime判断传入的月="0",年="1" 全部=""
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MONTH, 0);
                c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
                 first = format.format(c.getTime());
                Calendar ca = Calendar.getInstance();
                ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
                last = format.format(ca.getTime());
            }else if(dto.getStartTime().equals("1")){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.YEAR,0);
                c.set(Calendar.DAY_OF_YEAR,1);
                first = format.format(c.getTime());
                Calendar ca = Calendar.getInstance();
                ca.set(Calendar.DAY_OF_YEAR, ca.getActualMaximum(Calendar.DAY_OF_YEAR));
                last = format.format(ca.getTime());
            }
            wrapper.between("publish_date",first,last);
        }
       if(null!=dto.getGuideId()&&!dto.getGuideId().equals("")){
           wrapper.eq("guide_id",dto.getGuideId());
       }
        wrapper.eq("type",dto.getType());
        wrapper.eq("del",ComEnum.IsDelete.NORMAL.getValue());
        List<GuideArticle> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<GuideArticleListOutPO>  listpo=new ArrayList<GuideArticleListOutPO>();
        list.forEach((GuideArticle temp) -> {
            GuideArticleListOutPO po=new GuideArticleListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }
}
