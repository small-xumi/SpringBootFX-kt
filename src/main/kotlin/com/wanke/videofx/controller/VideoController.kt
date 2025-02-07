package com.wanke.videofx.controller

import javafx.beans.value.ObservableValue
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import org.springframework.stereotype.Controller
import java.io.File
import java.net.MalformedURLException
import java.net.URL
import java.util.*

/**
 * @author yinyin
 * @date 2025/1/18
 */
@Controller
class VideoController : Initializable {
    @FXML
    private val episodeList: ListView<String>? = null // 选集列表

    @FXML
    private val mediaView: MediaView? = null // 视频播放区域

    @FXML
    private val playButton: Button? = null // 播放按钮

    @FXML
    private val pauseButton: Button? = null // 暂停按钮
    private var mediaPlayer: MediaPlayer? = null // 媒体播放器
    private var videoFiles: ArrayList<String>? = null // 视频文件列表
    private var currentIndex = 0 // 当前播放的视频索引

    override fun initialize(url: URL, resourceBundle: ResourceBundle?) {
        run {
            // 初始化视频文件列表
            videoFiles = ArrayList()
            videoFiles!!.add("video1.mp4")
            videoFiles!!.add("video2.mp4")
            videoFiles!!.add("video3.mp4")

            // 将视频文件列表显示在 ListView 中
            episodeList!!.items.addAll(videoFiles!!)

            // 默认加载第一个视频
            loadVideo(currentIndex)

            // 监听选集列表的点击事件
            episodeList.selectionModel.selectedIndexProperty()
                .addListener { observable: ObservableValue<out Number?>?, oldValue: Number?, newValue: Number? ->
                    if (newValue != null && newValue.toInt() != currentIndex) {
                        currentIndex = newValue.toInt()
                        loadVideo(currentIndex)
                    }
                }
        }
    }

    // 加载视频
    private fun loadVideo(index: Int) {
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
        }

        val videoFile = videoFiles!![index]
        try {
            val media = Media(File(videoFile).toURI().toURL().toString())
            mediaPlayer = MediaPlayer(media)
            mediaView!!.mediaPlayer = mediaPlayer
            mediaPlayer!!.play()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }

    // 播放或暂停
    @FXML
    private fun playOrPause() {
        if (mediaPlayer != null) {
            if (mediaPlayer!!.status == MediaPlayer.Status.PLAYING) {
                mediaPlayer!!.pause()
            } else {
                mediaPlayer!!.play()
            }
        }
    }

    // 播放上一集
    @FXML
    private fun playPrevious() {
        if (currentIndex > 0) {
            currentIndex--
            loadVideo(currentIndex)
            episodeList!!.selectionModel.select(currentIndex)
        }
    }

    // 播放下一集
    @FXML
    private fun playNext() {
        if (currentIndex < videoFiles!!.size - 1) {
            currentIndex++
            loadVideo(currentIndex)
            episodeList!!.selectionModel.select(currentIndex)
        }
    }
}
