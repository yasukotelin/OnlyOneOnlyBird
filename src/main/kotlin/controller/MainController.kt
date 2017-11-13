package controller

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TabPane
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
    private var nameList = SettingResourceManager.nameListLoad()
    private var onlyOneTargetName = SettingResourceManager.onlyOneNameLoad()
    private var isOnlyOneMode = false


    /** アプリケーション終了イベント */
    @FXML fun doClose() {
        timer?.cancel()
        Platform.exit()
    }

    /** 設定モーダル表示イベント */
    @FXML fun openSettingModal() {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/fxml/SettingModal.fxml"))
        val tabPane = fxmlLoader.load<TabPane>()
        val settingCtl = fxmlLoader.getController<SettingModalController>()

        val modal = Stage()
        modal.title  = "Setting"
        modal.initOwner(label.scene.window)
        modal.initModality(Modality.APPLICATION_MODAL)
        modal.isResizable = false
        modal.scene = Scene(tabPane)

        // 設定モーダルコントローラーにプロパティをセット
        settingCtl.nameListTextArea.text = nameList.joinToString("\n")
        settingCtl.onlyOneTargetName.text = onlyOneTargetName
        settingCtl.isOnlyOneActive.isSelected = isOnlyOneMode

        // 設定モーダルが閉じるまでここで処理が停止する
        modal.showAndWait()

        val isMemberAccept = settingCtl.isMemberAccept
        val isOnlyOneAccept = settingCtl.isOnlyOneAccept
        when  {
            // MemberとOnlyOne両方ともAcceptされていたとき
            isMemberAccept && isOnlyOneAccept -> {
                doMemberAccept(settingCtl)
                doOnlyOneAccept(settingCtl)
            }
            // MemberがAcceptされていたとき
            isMemberAccept -> doMemberAccept(settingCtl)
            // OnlyOneがAcceptされていたとき
            isOnlyOneAccept -> doOnlyOneAccept(settingCtl)
        }
    }

    /** MemberSettingのAcceptボタンが押されていた時の処理 */
    private fun doMemberAccept(settingCtl: SettingModalController) {
        // 設定されたメンバー情報を画面に反映する
        this.nameList = settingCtl.nameListTextArea.text.split("\n")
    }

    /** OnlyOneSettingのAcceptボタンが押されていた時の処理 */
    private fun doOnlyOneAccept(settingCtl: SettingModalController) {
        // 設定されたOnlyOne対象者を反映する
        this.onlyOneTargetName = settingCtl.onlyOneTargetName.text
        this.isOnlyOneMode = settingCtl.isOnlyOneActive.isSelected
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
            this.onlyOneTargetName
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