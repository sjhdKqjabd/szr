package com.pandahis.dashboard.modules.dev.importCondition.convert.importCondition;

import java.util.*;

import com.pandahis.dashboard.common.pojo.PageResult;

import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionCreateReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionExcelVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionRespVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionUpdateReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.dal.dataobject.importCondition.PandaImportConditionDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 导医信息 Convert
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaImportConditionConvert {

    PandaImportConditionConvert INSTANCE = Mappers.getMapper(PandaImportConditionConvert.class);

    PandaImportConditionDO convert(PandaImportConditionCreateReqVO bean);

    PandaImportConditionDO convert(PandaImportConditionUpdateReqVO bean);

    PandaImportConditionRespVO convert(PandaImportConditionDO bean);

    List<PandaImportConditionRespVO> convertList(List<PandaImportConditionDO> list);

    PageResult<PandaImportConditionRespVO> convertPage(PageResult<PandaImportConditionDO> page);

    List<PandaImportConditionExcelVO> convertList02(List<PandaImportConditionDO> list);

}
