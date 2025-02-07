package com.wanke.videofx.boot

import com.wanke.videofx.boot.Launch.Companion.applicationContext
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.util.Callback
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class BootFXService {
    @Value("\${client.boot.fxml}")
    lateinit var bootFXML: String
    @Value("\${client.boot.width}")
    var bootWidth: Double = 100.0
    @Value("\${client.boot.height}")
    var bootHeight: Double = 100.0
    @Value("\${spring.application.name}")
    lateinit var bootName: String

    fun boot(mainStage: Stage) {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/views/${bootFXML}.fxml"))
        //这里需要注入controller的bean，可以让javafx的其他的类被springboot管理
        fxmlLoader.controllerFactory =
            Callback { requiredType: Class<*> ->
                applicationContext!!.getBean(requiredType)
            }
        val root = VBox()
        val content: Parent = fxmlLoader.load()
        root.children.addAll(content)
        val scene = Scene(root, bootWidth, bootHeight)
        mainStage.title = bootName
        mainStage.scene = scene
    }
}
