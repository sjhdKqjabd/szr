package com.pandahis.dashboard.modules.tool.dal.mysql.codegen;

import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.modules.tool.dal.dataobject.codegen.ToolSchemaColumnDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToolSchemaColumnMapper extends BaseMapperX<ToolSchemaColumnDO> {

    default List<ToolSchemaColumnDO> selectListByTableName(String tableName) {
        return selectList(new QueryWrapper<ToolSchemaColumnDO>().eq("table_name", tableName)
            .orderByAsc("ordinal_position"));
    }

}
