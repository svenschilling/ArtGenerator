import java.util.HashMap;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class NodeEditor extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // layouts
        HBox hBox = new HBox();
        StackPane stackPane = new StackPane();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        // buttons
        Button btnAddNode = new Button("add node");
        Button btnDeleteNode = new Button("delete node");
        Button btnConnectNodes = new Button("connect nodes");
        // need for graphics
        Canvas canvas = new Canvas(500,500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Color white = new Color(0, 0, 0, 0.5);
        
        // hashmap to save nodes
        HashMap<Integer, String> prefabHashmap = new HashMap<>();

        // coordinates
        int x,y;
        int mouseX,mouseY;

        hBox.getChildren().addAll(btnAddNode, btnDeleteNode, btnConnectNodes);
        stackPane.getChildren().addAll(canvas);

        root.setTop(hBox);
        root.setCenter(stackPane);

        primaryStage.setScene(scene);
        primaryStage.show();
        gc.setFill(white);


        //MouseEvent mouseEvent = new MouseEvent(EventType.ROOT, x, y, null, null, MouseButton.PRIMARY, 1, null, null, null, null, true, null, null, null, null, null, 1);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
