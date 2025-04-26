package com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class ChatRequest {
    @ApiModelProperty(value = "问题", required = true)
    private String question;
    private String sessionId;

    private Long questionId;
}



