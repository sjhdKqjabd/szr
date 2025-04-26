package com.pandahis.dashboard.modules.system.enums.dict;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典类型的类型枚举
 */
@Getter
@AllArgsConstructor
public enum SysDictTypeEnum {

    USER_TYPE("user_type"), // 用户类型

    SYS_USER_SEX("sys_user_sex"), // 用户性别
    SYS_COMMON_STATUS("sys_common_status"), // 系统状态
    SYS_OPERATE_TYPE("sys_operate_type"), // 操作类型
    SYS_LOGIN_RESULT("sys_login_result"), // 登陆结果
    SYS_CONFIG_TYPE("sys_config_type"), // 参数配置类型
    SYS_BOOLEAN_STRING("sys_boolean_string"), // Boolean 是否类型

    INF_REDIS_TIMEOUT_TYPE("inf_redis_timeout_type"),  // Redis 超时类型
    INF_JOB_STATUS("inf_job_status"), // 定时任务状态的枚举
    INF_JOB_LOG_STATUS("inf_job_log_status"), // 定时任务日志状态的枚举
    INF_API_ERROR_LOG_PROCESS_STATUS("inf_api_error_log_process_status"), // API 错误日志的处理状态的枚举

    ;


    /**
     * 值
     */
    private final String value;

}
