package com.wanke.videofx.boot

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

/**
 * 这个交给springboot去启动，不要在这里启动，否则会报错
 *
 * @author admin
 */
@Component
class Launch : Application() {
    fun changeStage(scene: Scene?) {
        mainStage!!.title = "Hello!"
        mainStage!!.scene = scene
        mainStage!!.show()
    }

    @Throws(Exception::class)
    override fun init() {
        super.init()
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        // 将 Stage 注册为 Spring Bean
        val bootFXService = applicationContext?.getBean(BootFXService::class.java)
        bootFXService?.boot(primaryStage)
        mainStage =primaryStage
        primaryStage.show()
    }

    @Throws(Exception::class)
    override fun stop() {
        //关闭springboot
        applicationContext!!.stop()
        Platform.exit()
        System.exit(0)
    }

    companion object {
        // 任何地方都可以通过这个applicationContext获取springboot的上下文
        var applicationContext: ConfigurableApplicationContext? = null

        //启动参数
        private lateinit var args: Array<String>
        private var mainStage: Stage? = null

        /**
         * 启动方法
         *
         * @param applicationContext
         * @param args
         */
        @JvmStatic
        fun launchThis(applicationContext: ConfigurableApplicationContext?, args: Array<String>) {
            Companion.args = args
            Companion.applicationContext = applicationContext
            launch(Launch::class.java,*args)
        }
    }
}
