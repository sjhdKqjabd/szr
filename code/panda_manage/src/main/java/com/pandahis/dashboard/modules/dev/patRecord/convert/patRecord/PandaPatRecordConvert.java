package com.pandahis.dashboard.modules.dev.patRecord.convert.patRecord;

import java.util.*;

import com.pandahis.dashboard.common.pojo.PageResult;

import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordCreateReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordExcelVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordRespVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordUpdateReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.dal.dataobject.patRecord.PandaPatRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 就诊 Convert
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaPatRecordConvert {

    PandaPatRecordConvert INSTANCE = Mappers.getMapper(PandaPatRecordConvert.class);

    PandaPatRecordDO convert(PandaPatRecordCreateReqVO bean);

    PandaPatRecordDO convert(PandaPatRecordUpdateReqVO bean);

    PandaPatRecordRespVO convert(PandaPatRecordDO bean);

    List<PandaPatRecordRespVO> convertList(List<PandaPatRecordDO> list);

    PageResult<PandaPatRecordRespVO> convertPage(PageResult<PandaPatRecordDO> page);

    List<PandaPatRecordExcelVO> convertList02(List<PandaPatRecordDO> list);

}
