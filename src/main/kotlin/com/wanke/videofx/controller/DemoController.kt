package com.wanke.videofx.controller

import com.wanke.videofx.boot.Launch
import com.wanke.videofx.config.ClientConfig
import com.wanke.videofx.utils.FXMLLoadUtil
import jakarta.annotation.Resource
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.Label
import org.springframework.stereotype.Controller
import java.io.IOException
import java.net.URL
import java.util.*

/**
 * @author admin
 */
@Controller
class DemoController : Initializable {
    @FXML
    private val welcomeText: Label? = null

    @Resource
    private val clientConfig: ClientConfig? = null

    @Resource
    private val launch: Launch? = null

    @FXML
    @Throws(IOException::class)
    protected fun onHelloButtonClick() {
        welcomeText?.text = "Welcome to JavaFX Application!" + clientConfig?.myAppName
        // 加载第二个页面的 FXML 文件
        val resource = javaClass.getResource("/views/hello-view.fxml")
        val scene: Scene = Scene(FXMLLoadUtil.loader(resource), 500.0, 500.0)
        launch?.changeStage(scene)
    }

    override fun initialize(url: URL, resourceBundle: ResourceBundle?) {
    }
}