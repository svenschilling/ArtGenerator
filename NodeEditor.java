import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NodeEditor extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox();
        Button btnAddNode = new Button("add node");
        Button btnDeleteNode = new Button("delete node");
        StackPane stackPane = new StackPane();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        // need for graphics
        Canvas canvas = new Canvas(500,500);
        G
        

        hBox.getChildren().addAll(btnAddNode, btnDeleteNode);
        stackPane.getChildren().addAll(canvas);

        root.setTop(hBox);
        root.setCenter(stackPane);

        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
