package com.pandahis.dashboard.modules.tool.convert.codegen;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.tool.controller.codegen.vo.ToolCodegenUpdateReqVO.Column;
import com.pandahis.dashboard.modules.tool.controller.codegen.vo.ToolCodegenUpdateReqVO.Table;
import com.pandahis.dashboard.modules.tool.controller.codegen.vo.column.ToolCodegenColumnRespVO;
import com.pandahis.dashboard.modules.tool.controller.codegen.vo.table.ToolCodegenTableRespVO;
import com.pandahis.dashboard.modules.tool.controller.codegen.vo.table.ToolSchemaTableRespVO;
import com.pandahis.dashboard.modules.tool.dal.dataobject.codegen.ToolCodegenColumnDO;
import com.pandahis.dashboard.modules.tool.dal.dataobject.codegen.ToolCodegenTableDO;
import com.pandahis.dashboard.modules.tool.dal.dataobject.codegen.ToolSchemaColumnDO;
import com.pandahis.dashboard.modules.tool.dal.dataobject.codegen.ToolSchemaTableDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class ToolCodegenConvertImpl implements ToolCodegenConvert {

    @Override
    public ToolCodegenTableDO convert(ToolSchemaTableDO bean) {
        if ( bean == null ) {
            return null;
        }

        ToolCodegenTableDO toolCodegenTableDO = new ToolCodegenTableDO();

        toolCodegenTableDO.setCreateTime( bean.getCreateTime() );
        toolCodegenTableDO.setTableName( bean.getTableName() );
        toolCodegenTableDO.setTableComment( bean.getTableComment() );

        return toolCodegenTableDO;
    }

    @Override
    public List<ToolCodegenColumnDO> convertList(List<ToolSchemaColumnDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ToolCodegenColumnDO> list1 = new ArrayList<ToolCodegenColumnDO>( list.size() );
        for ( ToolSchemaColumnDO toolSchemaColumnDO : list ) {
            list1.add( toolSchemaColumnDOToToolCodegenColumnDO( toolSchemaColumnDO ) );
        }

        return list1;
    }

    @Override
    public ToolCodegenTableRespVO convert(ToolSchemaColumnDO bean) {
        if ( bean == null ) {
            return null;
        }

        ToolCodegenTableRespVO toolCodegenTableRespVO = new ToolCodegenTableRespVO();

        toolCodegenTableRespVO.setTableName( bean.getTableName() );

        return toolCodegenTableRespVO;
    }

    @Override
    public ToolCodegenTableRespVO convert(ToolCodegenTableDO bean) {
        if ( bean == null ) {
            return null;
        }

        ToolCodegenTableRespVO toolCodegenTableRespVO = new ToolCodegenTableRespVO();

        toolCodegenTableRespVO.setImportType( bean.getImportType() );
        toolCodegenTableRespVO.setTableName( bean.getTableName() );
        toolCodegenTableRespVO.setTableComment( bean.getTableComment() );
        toolCodegenTableRespVO.setRemark( bean.getRemark() );
        toolCodegenTableRespVO.setModuleName( bean.getModuleName() );
        toolCodegenTableRespVO.setBusinessName( bean.getBusinessName() );
        toolCodegenTableRespVO.setClassName( bean.getClassName() );
        toolCodegenTableRespVO.setClassComment( bean.getClassComment() );
        toolCodegenTableRespVO.setAuthor( bean.getAuthor() );
        toolCodegenTableRespVO.setTemplateType( bean.getTemplateType() );
        toolCodegenTableRespVO.setParentMenuId( bean.getParentMenuId() );
        toolCodegenTableRespVO.setId( bean.getId() );
        toolCodegenTableRespVO.setCreateTime( bean.getCreateTime() );
        toolCodegenTableRespVO.setUpdateTime( bean.getUpdateTime() );

        return toolCodegenTableRespVO;
    }

    @Override
    public PageResult<ToolCodegenTableRespVO> convertPage(PageResult<ToolCodegenTableDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<ToolCodegenTableRespVO> pageResult = new PageResult<ToolCodegenTableRespVO>();

        pageResult.setList( toolCodegenTableDOListToToolCodegenTableRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<ToolCodegenColumnRespVO> convertList02(List<ToolCodegenColumnDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ToolCodegenColumnRespVO> list1 = new ArrayList<ToolCodegenColumnRespVO>( list.size() );
        for ( ToolCodegenColumnDO toolCodegenColumnDO : list ) {
            list1.add( toolCodegenColumnDOToToolCodegenColumnRespVO( toolCodegenColumnDO ) );
        }

        return list1;
    }

    @Override
    public ToolCodegenTableDO convert(Table bean) {
        if ( bean == null ) {
            return null;
        }

        ToolCodegenTableDO toolCodegenTableDO = new ToolCodegenTableDO();

        toolCodegenTableDO.setId( bean.getId() );
        toolCodegenTableDO.setImportType( bean.getImportType() );
        toolCodegenTableDO.setTableName( bean.getTableName() );
        toolCodegenTableDO.setTableComment( bean.getTableComment() );
        toolCodegenTableDO.setRemark( bean.getRemark() );
        toolCodegenTableDO.setModuleName( bean.getModuleName() );
        toolCodegenTableDO.setBusinessName( bean.getBusinessName() );
        toolCodegenTableDO.setClassName( bean.getClassName() );
        toolCodegenTableDO.setClassComment( bean.getClassComment() );
        toolCodegenTableDO.setAuthor( bean.getAuthor() );
        toolCodegenTableDO.setTemplateType( bean.getTemplateType() );
        toolCodegenTableDO.setParentMenuId( bean.getParentMenuId() );

        return toolCodegenTableDO;
    }

    @Override
    public List<ToolCodegenColumnDO> convertList03(List<Column> columns) {
        if ( columns == null ) {
            return null;
        }

        List<ToolCodegenColumnDO> list = new ArrayList<ToolCodegenColumnDO>( columns.size() );
        for ( Column column : columns ) {
            list.add( columnToToolCodegenColumnDO( column ) );
        }

        return list;
    }

    @Override
    public List<ToolSchemaTableRespVO> convertList04(List<ToolSchemaTableDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ToolSchemaTableRespVO> list1 = new ArrayList<ToolSchemaTableRespVO>( list.size() );
        for ( ToolSchemaTableDO toolSchemaTableDO : list ) {
            list1.add( toolSchemaTableDOToToolSchemaTableRespVO( toolSchemaTableDO ) );
        }

        return list1;
    }

    protected ToolCodegenColumnDO toolSchemaColumnDOToToolCodegenColumnDO(ToolSchemaColumnDO toolSchemaColumnDO) {
        if ( toolSchemaColumnDO == null ) {
            return null;
        }

        ToolCodegenColumnDO toolCodegenColumnDO = new ToolCodegenColumnDO();

        toolCodegenColumnDO.setColumnName( toolSchemaColumnDO.getColumnName() );
        toolCodegenColumnDO.setColumnType( toolSchemaColumnDO.getColumnType() );
        toolCodegenColumnDO.setColumnComment( toolSchemaColumnDO.getColumnComment() );
        toolCodegenColumnDO.setNullable( toolSchemaColumnDO.getNullable() );
        toolCodegenColumnDO.setPrimaryKey( toolSchemaColumnDO.getPrimaryKey() );
        toolCodegenColumnDO.setAutoIncrement( toolSchemaColumnDO.getAutoIncrement() );
        toolCodegenColumnDO.setOrdinalPosition( toolSchemaColumnDO.getOrdinalPosition() );

        return toolCodegenColumnDO;
    }

    protected List<ToolCodegenTableRespVO> toolCodegenTableDOListToToolCodegenTableRespVOList(List<ToolCodegenTableDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ToolCodegenTableRespVO> list1 = new ArrayList<ToolCodegenTableRespVO>( list.size() );
        for ( ToolCodegenTableDO toolCodegenTableDO : list ) {
            list1.add( convert( toolCodegenTableDO ) );
        }

        return list1;
    }

    protected ToolCodegenColumnRespVO toolCodegenColumnDOToToolCodegenColumnRespVO(ToolCodegenColumnDO toolCodegenColumnDO) {
        if ( toolCodegenColumnDO == null ) {
            return null;
        }

        ToolCodegenColumnRespVO toolCodegenColumnRespVO = new ToolCodegenColumnRespVO();

        toolCodegenColumnRespVO.setTableId( toolCodegenColumnDO.getTableId() );
        toolCodegenColumnRespVO.setColumnName( toolCodegenColumnDO.getColumnName() );
        toolCodegenColumnRespVO.setColumnType( toolCodegenColumnDO.getColumnType() );
        toolCodegenColumnRespVO.setColumnComment( toolCodegenColumnDO.getColumnComment() );
        toolCodegenColumnRespVO.setNullable( toolCodegenColumnDO.getNullable() );
        toolCodegenColumnRespVO.setPrimaryKey( toolCodegenColumnDO.getPrimaryKey() );
        if ( toolCodegenColumnDO.getAutoIncrement() != null ) {
            toolCodegenColumnRespVO.setAutoIncrement( String.valueOf( toolCodegenColumnDO.getAutoIncrement() ) );
        }
        toolCodegenColumnRespVO.setOrdinalPosition( toolCodegenColumnDO.getOrdinalPosition() );
        toolCodegenColumnRespVO.setJavaType( toolCodegenColumnDO.getJavaType() );
        toolCodegenColumnRespVO.setJavaField( toolCodegenColumnDO.getJavaField() );
        toolCodegenColumnRespVO.setDictType( toolCodegenColumnDO.getDictType() );
        toolCodegenColumnRespVO.setExample( toolCodegenColumnDO.getExample() );
        toolCodegenColumnRespVO.setCreateOperation( toolCodegenColumnDO.getCreateOperation() );
        toolCodegenColumnRespVO.setUpdateOperation( toolCodegenColumnDO.getUpdateOperation() );
        toolCodegenColumnRespVO.setListOperation( toolCodegenColumnDO.getListOperation() );
        toolCodegenColumnRespVO.setListOperationCondition( toolCodegenColumnDO.getListOperationCondition() );
        toolCodegenColumnRespVO.setListOperationResult( toolCodegenColumnDO.getListOperationResult() );
        toolCodegenColumnRespVO.setHtmlType( toolCodegenColumnDO.getHtmlType() );
        toolCodegenColumnRespVO.setId( toolCodegenColumnDO.getId() );
        toolCodegenColumnRespVO.setCreateTime( toolCodegenColumnDO.getCreateTime() );

        return toolCodegenColumnRespVO;
    }

    protected ToolCodegenColumnDO columnToToolCodegenColumnDO(Column column) {
        if ( column == null ) {
            return null;
        }

        ToolCodegenColumnDO toolCodegenColumnDO = new ToolCodegenColumnDO();

        toolCodegenColumnDO.setId( column.getId() );
        toolCodegenColumnDO.setTableId( column.getTableId() );
        toolCodegenColumnDO.setColumnName( column.getColumnName() );
        toolCodegenColumnDO.setColumnType( column.getColumnType() );
        toolCodegenColumnDO.setColumnComment( column.getColumnComment() );
        toolCodegenColumnDO.setNullable( column.getNullable() );
        toolCodegenColumnDO.setPrimaryKey( column.getPrimaryKey() );
        if ( column.getAutoIncrement() != null ) {
            toolCodegenColumnDO.setAutoIncrement( Boolean.parseBoolean( column.getAutoIncrement() ) );
        }
        toolCodegenColumnDO.setOrdinalPosition( column.getOrdinalPosition() );
        toolCodegenColumnDO.setJavaType( column.getJavaType() );
        toolCodegenColumnDO.setJavaField( column.getJavaField() );
        toolCodegenColumnDO.setDictType( column.getDictType() );
        toolCodegenColumnDO.setExample( column.getExample() );
        toolCodegenColumnDO.setCreateOperation( column.getCreateOperation() );
        toolCodegenColumnDO.setUpdateOperation( column.getUpdateOperation() );
        toolCodegenColumnDO.setListOperation( column.getListOperation() );
        toolCodegenColumnDO.setListOperationCondition( column.getListOperationCondition() );
        toolCodegenColumnDO.setListOperationResult( column.getListOperationResult() );
        toolCodegenColumnDO.setHtmlType( column.getHtmlType() );

        return toolCodegenColumnDO;
    }

    protected ToolSchemaTableRespVO toolSchemaTableDOToToolSchemaTableRespVO(ToolSchemaTableDO toolSchemaTableDO) {
        if ( toolSchemaTableDO == null ) {
            return null;
        }

        ToolSchemaTableRespVO toolSchemaTableRespVO = new ToolSchemaTableRespVO();

        toolSchemaTableRespVO.setTableSchema( toolSchemaTableDO.getTableSchema() );
        toolSchemaTableRespVO.setTableName( toolSchemaTableDO.getTableName() );
        toolSchemaTableRespVO.setTableComment( toolSchemaTableDO.getTableComment() );
        toolSchemaTableRespVO.setCreateTime( toolSchemaTableDO.getCreateTime() );

        return toolSchemaTableRespVO;
    }
}
