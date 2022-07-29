package view;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class View extends Application{
    ArrayList<Shape> makeShapes;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // node attributes
        double minWidthWindow = 800;
        double minHeightWindow = 600;
        double prefTileSize = 200;
        int tileHgap = 5;
        int tileVgap = 5;
        
        // primaryStage elements
        BorderPane root = new BorderPane();
        HBox hBox = new HBox();
        TilePane tilePane = new TilePane();
        TextField textField = new TextField();
        ScrollPane scrollPane = new ScrollPane(tilePane);
        
        // nodeEditorStage
        Stage nodeEditorStage = new Stage();
        StackPane nodeEditorStackPane = new StackPane();
        GridPane nodeEditoGridPane = new GridPane();
        HBox nodeEditorHBox = new HBox();
        Canvas nodeEditorCanvas = new Canvas();  

        // hBox elements primaryStage
        Button buttonGenerateAdd = new Button("generate add");
        Button buttonSaveImages = new Button("save images");
        Button buttonReset = new Button("clear fields");
        Button buttonDeleteSelected = new Button("delete selected");
        Button buttonGenerateNew = new Button("generate new");
        Button buttonNodeEditor = new Button("node editor");
        // hBox elements nodeEditorStage
        Button buttonBackToMain = new Button("to generator");

        nodeEditorHBox.getChildren().addAll(buttonBackToMain);
        
        hBox.getChildren().addAll(textField,buttonGenerateNew,buttonGenerateAdd,buttonSaveImages,buttonDeleteSelected,buttonReset,buttonNodeEditor);
        //! build loadingbar cause sometimes it takes longer and user should know something is working
        //! build a little status bar (ex. label) that show infos like file_xy got saved or you exceeded images creation (beyond 300) 
        //! make it green plus success text (like saved image namedxy at c:/ ..) or red with error message
        //! show a little number in the upper top corner of each box
        
        // tilepane elements
        tilePane.setPadding(new Insets(10, 10, 10, 10));
        tilePane.setHgap(tileHgap);
        tilePane.setVgap(tileVgap);
        
        // set fixed size  
        tilePane.prefTileHeightProperty().setValue(prefTileSize);
        tilePane.prefTileWidthProperty().setValue(prefTileSize);

        // binds the whole tilePane to the width of scrollPane
        tilePane.prefWidthProperty().bind(root.widthProperty());
        tilePane.prefHeightProperty().bind(root.heightProperty());
            
        Scene scene = new Scene(root, 860, 660);

        // stage settings
        primaryStage.setTitle("algorithm prototype");
        // set min | max value for window size
        primaryStage.minWidthProperty().setValue(minWidthWindow);
        primaryStage.minHeightProperty().setValue(minHeightWindow);    
        primaryStage.getIcons().add(new Image(ArtGenerator.class.getResourceAsStream("paintbrush-icon.png")));
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        // root elements     
        root.setTop(hBox);
        root.setCenter(scrollPane);
        
        // setup scrollpane
        scrollPane.setFitToWidth(true); 
        //! as needed still show the scrollbar no matter the size of the window
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED); 
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

   }
} 
