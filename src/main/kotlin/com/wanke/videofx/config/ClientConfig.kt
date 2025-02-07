package com.wanke.videofx.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties("client.config")
class ClientConfig(var myAppName: String? = null) {
    /**
     * 自定义客户端名称
     */
}
