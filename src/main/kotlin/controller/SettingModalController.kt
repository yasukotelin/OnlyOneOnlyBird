package controller

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField

class SettingModalController {

    // OnlyOneSetting
    @FXML lateinit var onlyOneTargetName: TextField
    @FXML lateinit var isOnlyOneActive: CheckBox
    @FXML lateinit var onlyOneAcceptBtn: Button
    @FXML lateinit var onlyOneOKBtn: Button
    var isOnlyOneAccept: Boolean = false

    /**
     * OnlyOneSettingのAcceptボタンクリックイベント
     * 確定したフラグを変更して、モーダル呼び出し側が変更を反映する
     **/
    @FXML fun onOnlyOneAcceptBtn() {
        SettingResourceManager.onlyOneNameSave(this.onlyOneTargetName.text)
        isOnlyOneAccept = true
        onlyOneAcceptBtn.isDisable = true
    }

    /** OKボタンイベント */
    @FXML fun onOkBtn() {
        onlyOneOKBtn.scene.window.hide()
    }
}