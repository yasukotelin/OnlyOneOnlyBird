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

        val fxmlLoader = FXMLLoader(javaClass.getResource("fxml/main.fxml"))
        val pane = fxmlLoader.load<Pane>()

        val scene = Scene(pane)

        primaryStage.scene = scene

        primaryStage.show()
    }
}