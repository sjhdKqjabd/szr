package com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class ChatResponse {
    @ApiModelProperty(value = "回答内容")
    private String answer;

    private Long questionId;
}
