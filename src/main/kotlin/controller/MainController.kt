package controller

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.stage.Modality
import javafx.stage.Stage
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
    private var isOnlyOneMode = false

    /** アプリケーション終了イベント */
    @FXML fun doClose() {
        timer?.cancel()
        Platform.exit()
    }

    /** OnlyOneモードの切り替えイベント */
    @FXML fun changeIsOnlyOneMode() {
        isOnlyOneMode = when (isOnlyOneMode) {
            true -> false
            false -> true
        }
    }

    @FXML fun openOnlyOneSettingModal() {
        val modal = Stage()
//
        val fxmlLoader = FXMLLoader(javaClass.getResource("fxml/OnlyOneSetting.fxml"))
        val pane = fxmlLoader.load<Pane>()
//        modal.scene = Scene(pane)

        modal.initModality(Modality.WINDOW_MODAL)
//        modal.initOwner(label.scene.window)
        modal.show()
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
        label.text = "当選：${getTheWinner()}"
        state = RouletteState.STOP
    }

    /** 当選者を返却する */
    private fun getTheWinner(): String {
        return if (isOnlyOneMode) {
            "藤島さん"
        } else {
            nameList[Random().nextInt(nameList.size)]
        }
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