package com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.*;
import com.pandahis.dashboard.modules.dev.sendContact.convert.sendContact.PandaSendContactConvert;
import com.pandahis.dashboard.modules.dev.sendContact.dal.dataobject.sendContact.PandaSendContactDO;
import com.pandahis.dashboard.modules.dev.sendContact.service.sendContact.PandaSendContactService;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum.EXPORT;


@Api(tags = "联系人表")
@RestController
@RequestMapping("/panda/send-contact")
@Validated
public class PandaSendContactController {

    @Resource
    private PandaSendContactService sendContactService;

    @PostMapping("/create")
    @ApiOperation("创建联系人表")

    public CommonResult<Long> createSendContact(@Valid @RequestBody PandaSendContactCreateReqVO createReqVO) {
        return CommonResult.success(sendContactService.createSendContact(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新联系人表")

    public CommonResult<Boolean> updateSendContact(@Valid @RequestBody PandaSendContactUpdateReqVO updateReqVO) {
        sendContactService.updateSendContact(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除联系人表")
    @ApiImplicitParam(name = "id", value = "编号", required = true)

    public CommonResult<Boolean> deleteSendContact(@RequestParam("id") Long id) {
        sendContactService.deleteSendContact(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得联系人表")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<PandaSendContactRespVO> getSendContact(@RequestParam("id") Long id) {
        PandaSendContactDO sendContact = sendContactService.getSendContact(id);
        return CommonResult.success(PandaSendContactConvert.INSTANCE.convert(sendContact));
    }

    @RequestMapping("/getSendContactList")
    @ApiOperation("获得联系人表列表")
    public CommonResult<List<PandaSendContactRespVO>> getSendContactList(@RequestBody SysUserDO sysUserDO) {
        List<PandaSendContactDO> list = sendContactService.getSendContactList(sysUserDO);
        return CommonResult.success(PandaSendContactConvert.INSTANCE.convertList(list));
    }

    @RequestMapping("/getSendMsgWithEachOther")
    @ApiOperation("获取双方的聊天信息")
    public CommonResult<List<PandaSendMsgDO>> getSendMsgWithEachOther(@RequestBody PandaSendMsgDO pandaSendMsgDO) {
        List<PandaSendMsgDO> list = sendContactService.getSendMsgWithEachOther(pandaSendMsgDO);
        return CommonResult.success(list);
    }


    @RequestMapping("/getSendMsgWithEachOtherUnreaded")
    @ApiOperation("获取双方的聊天信息未读消息")
    public CommonResult<List<PandaSendMsgDO>> getSendMsgWithEachOtherUnreaded(@RequestBody PandaSendMsgDO pandaSendMsgDO) {
        List<PandaSendMsgDO> list = sendContactService.getSendMsgWithEachOtherUnreaded(pandaSendMsgDO);
        return CommonResult.success(list);
    }

    @GetMapping("/page")
    @ApiOperation("获得联系人表分页")

    public CommonResult<PageResult<PandaSendContactRespVO>> getSendContactPage(@Valid PandaSendContactPageReqVO pageVO) {
        PageResult<PandaSendContactDO> pageResult = sendContactService.getSendContactPage(pageVO);
        return CommonResult.success(PandaSendContactConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出联系人表 Excel")

    @OperateLog(type = EXPORT)
    public void exportSendContactExcel(@Valid PandaSendContactExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PandaSendContactDO> list = sendContactService.getSendContactList(exportReqVO);
        // 导出 Excel
        List<PandaSendContactExcelVO> datas = PandaSendContactConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "联系人表.xls", "数据", PandaSendContactExcelVO.class, datas);
    }






}
