package com.wanke.videofx.utils

import com.wanke.videofx.boot.Launch
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.Callback
import java.io.IOException
import java.net.URL

/**
 * @author yinyin
 * @date 2025/1/18
 */
object FXMLLoadUtil {
    /**
     * 都应该交由springboot管理
     *
     * @param location the location used to resolve relative path attribute values
     * @since JavaFX 2.1
     */
    @Throws(IOException::class)
    fun loader(location: URL?): Parent {
        // 加载第二个页面的 FXML 文件
        val fxmlLoader = FXMLLoader(location)
        fxmlLoader.controllerFactory =
            Callback { requiredType: Class<*> ->
                Launch.applicationContext!!.getBean(requiredType)
            } //
        return fxmlLoader.load()
    }
}
