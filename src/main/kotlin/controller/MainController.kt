package controller

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import java.util.*

class MainController {

    @FXML lateinit var label: Label
    @FXML lateinit var btn: Button

    enum class RouletteState {
        STOP, ACTIVE
    }
    private var state = RouletteState.STOP
    private var timer: Timer? = null
    private var nameList = NameListManager.load()

    /** アプリケーション終了イベント */
    @FXML fun doClose() {
        timer?.cancel()
        Platform.exit()
    }

    /** 抽選開始 */
    @FXML fun doRoulette() {
        when (state) {
            RouletteState.STOP -> startRoulette()
            RouletteState.ACTIVE -> stopRoulette()
        }
    }

    /** ルーレット開始処理 */
    private fun startRoulette() {
        btn.text = "抽選中..."
        timer = Timer()
        timer?.schedule(RouletteTask(), 0, 100)
        state = RouletteState.ACTIVE
    }

    /** ルーレット停止（当選処理） */
    private fun stopRoulette() {
        timer?.cancel()
        btn.text = "Fly High Again!"
        val target = Random().nextInt(nameList.size)
        label.text = "当選：${nameList[target]}"
        state = RouletteState.STOP
    }

    /** ルーレット描画タスククラス */
    inner class RouletteTask: TimerTask() {
        private var rouletteIndex = 0
        private val nameListSize = nameList.size

        override fun run() {
            // 別スレッドでJavaFXの要素を変更する場合
            // Platform#runLaterを使って競合を起こさないようにする必要がある
            Platform.runLater { label.text = nameList[rouletteIndex]}

            if (rouletteIndex == nameListSize - 1) {
                rouletteIndex = 0
            } else {
                rouletteIndex++
            }
        }
    }
}