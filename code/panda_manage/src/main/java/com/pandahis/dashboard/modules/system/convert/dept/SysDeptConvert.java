package com.pandahis.dashboard.modules.system.convert.dept;

import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptRespVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptSimpleRespVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDeptConvert {

    SysDeptConvert INSTANCE = Mappers.getMapper(SysDeptConvert.class);

    List<SysDeptRespVO> convertList(List<SysDeptDO> list);

    List<SysDeptSimpleRespVO> convertList02(List<SysDeptDO> list);

    SysDeptRespVO convert(SysDeptDO bean);

    SysDeptDO convert(SysDeptCreateReqVO bean);

    SysDeptDO convert(SysDeptUpdateReqVO bean);

}
