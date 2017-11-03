import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage

/**
 * JavaFXのエントリーポイント
 */
class AppMain : Application() {

    override fun start(primaryStage: Stage) {

        val fxmlURL = javaClass.getResource("fxml/main.fxml")
        val pane = FXMLLoader.load<Pane>(fxmlURL)

        val scene = Scene(pane)

        primaryStage.scene = scene

        primaryStage.show()
    }
}