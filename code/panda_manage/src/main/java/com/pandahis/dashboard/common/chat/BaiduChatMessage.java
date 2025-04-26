
package com.pandahis.dashboard.common.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaiduChatMessage implements Serializable {
    private String role;
    private String content;
}
