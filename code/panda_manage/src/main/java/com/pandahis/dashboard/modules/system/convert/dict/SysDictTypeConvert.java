package com.pandahis.dashboard.modules.system.convert.dict;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.*;
import com.pandahis.dashboard.modules.system.dal.dataobject.dict.SysDictTypeDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictTypeConvert {

    SysDictTypeConvert INSTANCE = Mappers.getMapper(SysDictTypeConvert.class);

    PageResult<SysDictTypeRespVO> convertPage(PageResult<SysDictTypeDO> bean);

    SysDictTypeRespVO convert(SysDictTypeDO bean);

    SysDictTypeDO convert(SysDictTypeCreateReqVO bean);

    SysDictTypeDO convert(SysDictTypeUpdateReqVO bean);

    List<SysDictTypeSimpleRespVO> convertList(List<SysDictTypeDO> list);

    List<SysDictTypeExcelVO> convertList02(List<SysDictTypeDO> list);

}
