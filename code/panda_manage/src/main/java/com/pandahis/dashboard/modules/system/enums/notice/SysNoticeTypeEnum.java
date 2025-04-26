package com.pandahis.dashboard.modules.system.enums.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通知类型
 *
 * @author 源码乐园
 */
@Getter
@AllArgsConstructor
public enum SysNoticeTypeEnum {

    NOTICE(1),
    ANNOUNCEMENT(2);

    /**
     * 类型
     */
    private final Integer type;

}
