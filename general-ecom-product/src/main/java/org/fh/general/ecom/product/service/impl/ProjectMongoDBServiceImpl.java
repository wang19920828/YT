package org.fh.general.ecom.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.product.mongo.InputMongoDBProjectDTO;
import org.fh.general.ecom.common.dto.product.mongo.OutputMongoDBPageDTO;
import org.fh.general.ecom.common.dto.product.mongo.OutputMongoProjectDTO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectMainInfoDTO;
import org.fh.general.ecom.common.dto.product.web.InputWebProjectDetailDTO;
import org.fh.general.ecom.common.enums.PageEnum;
import org.fh.general.ecom.common.enums.ProjectEnum;
import org.fh.general.ecom.common.mongo.MongoDbUtil;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.utils.ObjectUtils;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.product.service.ProjectMongoDBService;
import org.fh.general.ecom.product.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author huliping
 * @DATE 2018/9/25
 **/
@Slf4j
@Service
public class ProjectMongoDBServiceImpl implements ProjectMongoDBService {

    @Autowired
    private ProjectService projectService;
    @Override
    public void updateOne(OutputMongoProjectDTO dto) {
        this.insertOne(dto);
    }
    @Override
    public void insertOne(OutputMongoProjectDTO dto) {
        Map<String,Object>  map  = ObjectUtils.beanToMap(dto);
        try {
            Map<String,Object> delMap = new HashMap<String,Object>();
            delMap.put("id",dto.getId());
            delMap.put("branch",dto.getBranch());
            MongoDbUtil.deleteMany("tb_product",delMap);
            MongoDbUtil.insertOne("tb_product",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  List<String>  getListString(String str){
        List<String> list=new ArrayList<String>();
        if(StringUtils.isNotEmpty(str)){
                String[] strArr = str.split(",");
                for (String s : strArr) {
                    list.add(s);
                }
        }
        return list;
    }
    @Override
    public  OutputMongoDBPageDTO findWebHotProjectPage(InputWebProjectDetailDTO dto){
        OutputMongoDBPageDTO response= new OutputMongoDBPageDTO();
        Long id = dto.getId();
        OutputProjectMainInfoDTO detail = this.projectService.findMainInfoByProjectId(id+"");
        if(detail!=null){
            InputMongoDBProjectDTO inputMongoDBProjectDTO=new InputMongoDBProjectDTO();
            inputMongoDBProjectDTO.setAreaAddress(detail.getAreaAddress());
            inputMongoDBProjectDTO.setProjectType(detail.getProjectType());
            inputMongoDBProjectDTO.setProjectStatus(detail.getProjectStatus());
            inputMongoDBProjectDTO.setNotId(id+"");
            inputMongoDBProjectDTO.setSortType("publishDate");
            inputMongoDBProjectDTO.setSortDesc("-1");
            inputMongoDBProjectDTO.setCurrentPageNum(dto.getCurrentPageNum()==null? Integer.valueOf(PageEnum.CURRENT_NUM.getCode()):dto.getCurrentPageNum());
            inputMongoDBProjectDTO.setPageSize(dto.getPageSize()==null?Integer.valueOf(PageEnum.HOT_PAGE_COUNT.getCode()):dto.getPageSize());
            return  findListFromMongoDB(inputMongoDBProjectDTO);
        }
        return response;
    }
    @Override
    public OutputMongoDBPageDTO findListFromMongoDB(InputMongoDBProjectDTO dto) {
        OutputMongoDBPageDTO response= new OutputMongoDBPageDTO();
        Map<String,Object>  map  = ObjectUtils.beanToMongoMap(dto);
        try {
            Map<String,Object> listMap = getSearchMap(map);
            String sortType = "id";
            if(StringUtils.isNotEmpty(dto.getSortType())){
                sortType = dto.getSortType();
            }
            String  sortDesc = "1";
            if(StringUtils.isNotEmpty(dto.getSortDesc()) && org.apache.commons.lang.StringUtils.isNumeric(sortDesc)){
                sortDesc = dto.getSortDesc();
            }

            Map<String,Object>  countMap = getSearchMap(map);
            countMap.remove("pageSize");
            countMap.remove("currentPageNum");
            long totalCount = MongoDbUtil.findCount("tb_product",countMap);
            if(totalCount>0) {
                Map<String, Object> sortMap = new HashMap<String, Object>();
                sortMap.put(sortType+".SORT", Integer.valueOf(sortDesc));

                log.info("【=========sortMap=========】"+sortMap);
                List<String> list = MongoDbUtil.findListPageSort("tb_product", listMap, sortMap);

                PageInfo pageInfo=new PageInfo(list);
                List<OutputMongoProjectDTO> outputMongoProjectDTOList=new ArrayList<OutputMongoProjectDTO>() ;
                list.forEach((String obj)->{
                    JSONObject jsonObject = JSONObject.parseObject(obj);
                    OutputMongoProjectDTO outputMongoProjectDTO =   JSONObject.toJavaObject(jsonObject,OutputMongoProjectDTO.class);
                    if(obj.contains("$date")){
                        changeDateFormat(jsonObject, outputMongoProjectDTO);
                    }
                    outputMongoProjectDTOList.add(outputMongoProjectDTO);
                });
                response.setList(outputMongoProjectDTOList);
                response.setPageInfo(pageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private void changeDateFormat(JSONObject jsonObject, OutputMongoProjectDTO outputMongoProjectDTO) {
        String startTime = jsonObject.get("startTime").toString();
        if(StringUtils.isNotEmpty(startTime)) {
            String st = JSONObject.parseObject(startTime).get("$date").toString();
            Date stDate = DateUtils.getDate(Long.valueOf(st));
            outputMongoProjectDTO.setStartTime(stDate);
        }

        String endTime = jsonObject.get("endTime").toString();
        if(StringUtils.isNotEmpty(endTime)) {
            String et = JSONObject.parseObject(endTime).get("$date").toString();
            Date etDate = DateUtils.getDate(Long.valueOf(et));
            outputMongoProjectDTO.setEndTime(etDate);
        }

        String purchaseStart = jsonObject.get("purchaseStartTime").toString();
        if(StringUtils.isNotEmpty(purchaseStart)) {
            String ps = JSONObject.parseObject(purchaseStart).get("$date").toString();
            Date psDate = DateUtils.getDate(Long.valueOf(ps));
            outputMongoProjectDTO.setPurchaseStartTime(psDate);
        }

        String purchaseEndTime = jsonObject.get("purchaseEndTime").toString();
        if(StringUtils.isNotEmpty(purchaseEndTime)) {
            String pe = JSONObject.parseObject(purchaseEndTime).get("$date").toString();
            Date peDate = DateUtils.getDate(Long.valueOf(pe));
            outputMongoProjectDTO.setPurchaseEndTime(peDate);
        }
    }

    private String getSortDescValue(Map<String, Object> map, String sortDesc) {
        if(map.containsKey("sortDesc") && map.get("sortDesc")!=null && !"".equals(map.get("sortDesc"))){
            sortDesc = map.get("sortDesc").toString();
            map.remove("sortDesc");
        }
        return sortDesc;
    }

    private String getSortTypeValue(Map<String, Object> map, String sortType) {
        if(map.containsKey("sortType") && map.get("sortType")!=null && !"".equals(map.get("sortType"))){
           sortType = map.get("sortType").toString();
            map.remove("sortType");
        }
        return sortType;
    }

    private Map<String, Object>  getSearchMap(Map<String, Object> paramMap) {
        Map<String, Object>  map = new HashMap<String,Object>();
        map.putAll(paramMap);
        if(map.containsKey("areaAddresses") && map.get("areaAddresses")!=null && !"".equals(map.get("areaAddresses"))){
            map.put("likebefore.areaAddress",map.get("areaAddresses").toString());
            map.remove("areaAddresses");
        }
        if(map.containsKey("rightsTypes") && map.get("rightsTypes")!=null && !"".equals(map.get("rightsTypes"))){
            map.put("rightsType",map.get("rightsTypes").toString());
            map.remove("rightsTypes");
        }

        if(map.containsKey("projectName") && map.get("projectName")!=null && !"".equals(map.get("projectName"))){
            map.put("like.projectName",map.get("projectName").toString());
            map.remove("projectName");
        }

        if(map.containsKey("projectTypes") && map.get("projectTypes")!=null && !"".equals(map.get("projectTypes"))){
            if("00".equals(map.get("projectTypes").toString())){
                map.put("publishDate.LT", new Date()); //小于
                map.put("publishDate.GT", DateUtils.getDate(DateUtils.getbeforeMonth()));//大于
            }else if("01".equals(map.get("projectTypes").toString())){
                map.put("projectStatus", ProjectEnum.ProjectStatus.AUDIT_PASS);
            }else {
                map.put("projectType", map.get("projectTypes"));
            }
            map.remove("projectTypes");
        }

        if(map.containsKey("notId") && map.get("notId")!=null && !"".equals(map.get("notId"))){
            map.put("id.NE",map.get("notId").toString());
            map.remove("notId");
        }

        map.remove("sortDesc");
        map.remove("sortType");
        return map;
    }

    public void delOne(OutputMongoProjectDTO dto){
        Map<String,Object> delMap = new HashMap<String,Object>();
        delMap.put("id",dto.getId());
        delMap.put("branch",dto.getBranch());
        try {
            MongoDbUtil.deleteMany("tb_product",delMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
