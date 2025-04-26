package com.pandahis.dashboard.modules.system.dal.dataobject.notice;

import com.pandahis.dashboard.common.enums.CommonStatusEnum;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;
import com.pandahis.dashboard.modules.system.enums.notice.SysNoticeTypeEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通知公告表
 *
 *
 */
@TableName("sys_notice")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysNoticeDO extends BaseDO {

    /**
     * 公告ID
     */
    private Long id;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告类型
     *
     * 枚举 {@link SysNoticeTypeEnum}
     */
    @TableField("notice_type")
    private Integer type;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 公告状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;



    private String readed;

}
