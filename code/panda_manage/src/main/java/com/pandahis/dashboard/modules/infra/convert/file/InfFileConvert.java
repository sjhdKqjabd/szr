package com.pandahis.dashboard.modules.infra.convert.file;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.infra.dal.dataobject.file.InfFileDO;
import com.pandahis.dashboard.modules.infra.controller.file.vo.InfFileRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InfFileConvert {

    InfFileConvert INSTANCE = Mappers.getMapper(InfFileConvert.class);

    InfFileRespVO convert(InfFileDO bean);

    PageResult<InfFileRespVO> convertPage(PageResult<InfFileDO> page);

}
