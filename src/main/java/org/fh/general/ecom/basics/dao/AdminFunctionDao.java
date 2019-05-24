package org.fh.general.ecom.basics.dao;

import org.apache.ibatis.annotations.Mapper;
import org.fh.general.ecom.basics.model.AdminFunction;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 功能菜单 Mapper 接口
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@Mapper
public interface AdminFunctionDao extends BaseMapper<AdminFunction> {

    /**
     * 查询指定层级的下一个编码
     */
    String selectNextCodeByParentId(Map<String, Object> map);

    /**
     * 根据编码更新全部相关菜单
     */
    int updateBySortCode(Map<String, Object> map);

    /**
     * 查询相同记录
     * @param entity
     * @return
     */
    AdminFunction selectSameEntity(AdminFunction entity);

    /**
     * 查询全部菜单按角色授权标记
     */
    List<AdminFunction> selectAllListByRole(Map<String, Object> params);

    /**
     * 查询角色菜单
     */
    List<AdminFunction> selectRoleFuncList(Map<String, Object> params);

    /**
     * 分页查询
     */
    List<AdminFunction> findFunctionList(Map<String, Object> map);

    AdminFunction selectByPrimaryKey(Long id);

}