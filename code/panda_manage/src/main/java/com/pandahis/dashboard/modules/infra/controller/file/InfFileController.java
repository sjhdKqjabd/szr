package com.pandahis.dashboard.modules.infra.controller.file;

import cn.hutool.core.io.IoUtil;
import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.infra.controller.file.vo.InfFilePageReqVO;
import com.pandahis.dashboard.modules.infra.controller.file.vo.InfFileRespVO;
import com.pandahis.dashboard.modules.infra.convert.file.InfFileConvert;
import com.pandahis.dashboard.modules.infra.dal.dataobject.file.InfFileDO;
import com.pandahis.dashboard.modules.infra.service.file.InfFileService;
import com.pandahis.dashboard.util.servlet.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Api(tags = "文件存储")
@RestController
@RequestMapping("/infra/file")
@Validated
@Slf4j
public class InfFileController {

    @Resource
    private InfFileService fileService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public CommonResult uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        CommonResult result=fileService.createFile(file.getOriginalFilename(), IoUtil.readBytes(file.getInputStream()));
        return result;
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除文件")
    @ApiImplicitParam(name = "id", value = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('infra:file:delete')")
    public CommonResult<Boolean> deleteFile(@RequestParam("id") String id) {
        fileService.deleteFile(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get/{path}")
    public void getFile(HttpServletResponse response, @PathVariable("path") String path) throws IOException {
        InfFileDO file = fileService.getFile(path);
        if (file == null) {
            log.warn("[getFile][path({}) 文件不存在]", path);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        ServletUtils.writeAttachment(response, path, file.getContent());
    }

    @GetMapping("/page")
    @ApiOperation("获得文件分页")
    @PreAuthorize("@ss.hasPermission('infra:file:query')")
    public CommonResult<PageResult<InfFileRespVO>> getFilePage(@Valid InfFilePageReqVO pageVO) {
        PageResult<InfFileDO> pageResult = fileService.getFilePage(pageVO);
        return CommonResult.success(InfFileConvert.INSTANCE.convertPage(pageResult));
    }

}
