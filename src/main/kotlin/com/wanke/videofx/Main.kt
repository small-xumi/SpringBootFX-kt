package com.wanke.videofx

import com.wanke.videofx.boot.Launch
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class Main

fun main(args: Array<String>) {
    // 启动springboot
    val context: ConfigurableApplicationContext =
        SpringApplication.run(Main::class.java, *args);
    // 启动javafx
    Launch.launchThis(context, args);
}
