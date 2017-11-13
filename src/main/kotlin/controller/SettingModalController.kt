package controller

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.TextArea
import javafx.scene.control.TextField

class SettingModalController {

    // MemberSetting
    @FXML lateinit var nameListTextArea: TextArea
    @FXML lateinit var memberAcceptBtn: Button
    var isMemberAccept: Boolean = false

    // OnlyOneSetting
    @FXML lateinit var onlyOneTargetName: TextField
    @FXML lateinit var isOnlyOneActive: CheckBox
    @FXML lateinit var onlyOneAcceptBtn: Button
    @FXML lateinit var onlyOneOKBtn: Button
    var isOnlyOneAccept: Boolean = false


    /** MemberSettingのAcceptボタンイベント */
    @FXML fun onMemberAcceptBtn() {
        SettingResourceManager.nameListSave(nameListTextArea.text)
        isMemberAccept = true
        memberAcceptBtn.isDisable = true
    }

    /** OnlyOneSettingのAcceptボタンクリックイベント **/
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