package com.pandahis.dashboard.modules.system.controller.dict.vo.data;

import com.pandahis.dashboard.framework.excel.core.annotations.DictFormat;
import com.pandahis.dashboard.framework.excel.core.convert.DictConvert;
import com.pandahis.dashboard.modules.system.enums.dict.SysDictTypeEnum;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 字典数据 Excel 导出响应 VO
 */
@Data
public class SysDictDataExcelVO {

    @ExcelProperty("字典编码")
    private Long id;

    @ExcelProperty("字典排序")
    private Integer sort;

    @ExcelProperty("字典标签")
    private String label;

    @ExcelProperty("字典键值")
    private String value;

    @ExcelProperty("字典类型")
    private String dictType;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat(SysDictTypeEnum.SYS_COMMON_STATUS)
    private Integer status;

}
