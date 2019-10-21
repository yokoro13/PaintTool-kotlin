import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.ColorPicker
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.stage.Stage

class PaintTool : Application(){
    val width = 800.0
    val height = 800.0

    override fun start(primaryStage: Stage){
        primaryStage.title = "aaaa"
        primaryStage.show()

        val root = Group()
        val scene = Scene(root, width, height)
        primaryStage.scene = scene

        val canvas = Canvas(width, height - 30)
        canvas.layoutY = 30.0

        val gc = canvas.graphicsContext2D
        root.children.add(canvas)

        val colorPicker = ColorPicker()
        root.children.add(colorPicker)

        gc.lineWidth = 3.0
        gc.stroke = Color.BLACK
        gc.strokeRect(0.0, 0.0, width, height - 30)

        gc.lineWidth = 1.0
        gc.stroke = colorPicker.value

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED) {
            gc.beginPath()
            gc.moveTo(it.x, it.y)
            gc.stroke()
        }

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED){
            gc.lineTo(it.x, it.y)
            gc.stroke()
        }

        colorPicker.valueProperty().addListener { _, _, nv ->
            gc.stroke = nv
        }
    }
}

fun main(args: Array<String>){
    Application.launch(PaintTool()::class.java, *args)
}