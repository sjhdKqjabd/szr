package com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg;

import com.pandahis.dashboard.common.chat.*;
import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.*;
import com.pandahis.dashboard.modules.dev.sendMsg.convert.sendMsg.PandaSendMsgConvert;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;
import com.pandahis.dashboard.modules.dev.sendMsg.service.sendMsg.PandaSendMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum.EXPORT;


@Api(tags = "发送消息表")
@RestController
@RequestMapping("/panda/send-msg")
@Validated
public class PandaSendMsgController {

    @Resource
    private PandaSendMsgService sendMsgService;


    @Resource
    BaiduEventSourceListener baiduEventSourceListener;


    @Value("${baidu.appkey}")
    private String appKey;


    @Value("${baidu.secretKey}")
    private String secretKey;


    @PostMapping("/create")
    @ApiOperation("创建发送消息表")

    public CommonResult<Long> createSendMsg(@Valid @RequestBody PandaSendMsgDO pandaSendMsgDO) {
        return CommonResult.success(sendMsgService.createSendMsg(pandaSendMsgDO));
    }

    @PutMapping("/update")
    @ApiOperation("更新发送消息表")

    public CommonResult<Boolean> updateSendMsg(@Valid @RequestBody PandaSendMsgUpdateReqVO updateReqVO) {
        sendMsgService.updateSendMsg(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除发送消息表")
    @ApiImplicitParam(name = "id", value = "编号", required = true)

    public CommonResult<Boolean> deleteSendMsg(@RequestParam("id") Long id) {
        sendMsgService.deleteSendMsg(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得发送消息表")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<PandaSendMsgRespVO> getSendMsg(@RequestParam("id") Long id) {
        PandaSendMsgDO sendMsg = sendMsgService.getSendMsg(id);
        return CommonResult.success(PandaSendMsgConvert.INSTANCE.convert(sendMsg));
    }

    @GetMapping("/list")
    @ApiOperation("获得发送消息表列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)

    public CommonResult<List<PandaSendMsgRespVO>> getSendMsgList(@RequestParam("ids") Collection<Long> ids) {
        List<PandaSendMsgDO> list = sendMsgService.getSendMsgList(ids);
        return CommonResult.success(PandaSendMsgConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得发送消息表分页")

    public CommonResult<PageResult<PandaSendMsgRespVO>> getSendMsgPage(@Valid PandaSendMsgPageReqVO pageVO) {
        PageResult<PandaSendMsgDO> pageResult = sendMsgService.getSendMsgPage(pageVO);
        return CommonResult.success(PandaSendMsgConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出发送消息表 Excel")

    @OperateLog(type = EXPORT)
    public void exportSendMsgExcel(@Valid PandaSendMsgExportReqVO exportReqVO,
                                   HttpServletResponse response) throws IOException {
        List<PandaSendMsgDO> list = sendMsgService.getSendMsgList(exportReqVO);
        // 导出 Excel
        List<PandaSendMsgExcelVO> datas = PandaSendMsgConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "发送消息表.xls", "数据", PandaSendMsgExcelVO.class, datas);
    }


    @PostMapping("/chat")
    @ApiOperation("AI对话接口")
    public CommonResult<ChatResponse> chat(@RequestBody ChatRequest request) {

        BaiduService baiduService = new BaiduService(appKey, secretKey);
        ErnieBotTurboStreamParam ernieBotTurboStreamParam = new ErnieBotTurboStreamParam();


        BaiduChatMessage baiduChatMessage = new BaiduChatMessage();
        baiduChatMessage.setRole("user");
        baiduChatMessage.setContent(request.getQuestion());

        ErnieBotTurboStreamParam build = ernieBotTurboStreamParam.builder()
              .messages(Collections.singletonList(baiduChatMessage))
              .stream(true)
              .user_id("123")
              .build();

        CopyOnWriteArrayList<Session> sessions = WebSocketServiceChat.sessions;
        Optional<Session> first = sessions.stream().filter(each -> each.getId().equals(request.getSessionId())).findFirst();
        if (first.isPresent()) {
            baiduEventSourceListener.setSession(first.get());
            baiduEventSourceListener.setQuestionId(new Date().getTime());
            baiduService.ernieBotTurboStream(build, baiduEventSourceListener);
        }else{
            throw new RuntimeException("会话不存在，请刷新页面");
        }


        return CommonResult.success(null);
    }


}
