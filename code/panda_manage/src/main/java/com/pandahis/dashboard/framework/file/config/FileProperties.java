package com.pandahis.dashboard.framework.file.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "system.file")
@Validated
@Data
public class FileProperties {

    /**
     * 对应 {@link InfFileController#}
     */
    @NotNull(message = "基础文件路径不能为空")
    private String basePath;

    // TODO 七牛、等等

}
