package com.pandahis.dashboard.common.chat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaiDuToken {

    private String access_token;
    private Integer expires_in;
    private String error;
    private String error_description;

}
