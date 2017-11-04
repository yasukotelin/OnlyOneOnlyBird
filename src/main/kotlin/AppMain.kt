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
        primaryStage.title = "OnlyOneOnlyBird"
        primaryStage.isResizable = false

        val pane = FXMLLoader.load<Pane>(javaClass.getResource("fxml/main.fxml"))

        val scene = Scene(pane)

        primaryStage.scene = scene

        primaryStage.show()
    }
}