package com.pandahis.dashboard.framework.web.core.enums;

/**
 * 过滤器顺序的枚举类，保证过滤器按照符合我们的预期
 *
 * @author 源码乐园
 */
public interface FilterOrderEnum {

    int CORS_FILTER = Integer.MIN_VALUE;

    int REQUEST_BODY_CACHE_FILTER = Integer.MIN_VALUE + 500;

    // OrderedRequestContextFilter 默认为 -105，用于国际化上下文等等

    int API_ACCESS_LOG_FILTER = -104; // 需要保证在 RequestBodyCacheFilter 后面

    int XSS_FILTER = -103;  // 需要保证在 RequestBodyCacheFilter 后面

    // Spring Security Filter 默认为 -100，可见 SecurityProperties 配置属性类

}
