package com.pandahis.dashboard.modules.infra.service.file;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.infra.dal.dataobject.file.InfFileDO;
import com.pandahis.dashboard.modules.infra.controller.file.vo.InfFilePageReqVO;

/**
 * 文件 Service 接口
 *
 * @author 源码乐园
 */
public interface InfFileService {

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param path 文件路径
     * @param content 文件内容
     * @return 文件路径
     */
    CommonResult createFile(String path, byte[] content);

    /**
     * 删除文件
     *
     * @param id 编号
     */
    void deleteFile(String id);

    /**
     * 获得文件
     *
     * @param path 文件路径
     * @return 文件
     */
    InfFileDO getFile(String path);

    /**
     * 获得文件分页
     *
     * @param pageReqVO 分页查询
     * @return 文件分页
     */
    PageResult<InfFileDO> getFilePage(InfFilePageReqVO pageReqVO);

}
