
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
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
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

        //! test setall to just overwrite old hbox
        nodeEditorHBox.getChildren().addAll(buttonBackToMain);
        //! build loadingbar cause sometimes it takes longer and user should know something is working
        //! build a little status bar (ex. label) that show infos like file_xy got saved or you exceeded images creation (beyond 300) 
        //! make it green plus success text (like saved image namedxy at c:/ ..) or red with error message

        hBox.getChildren().addAll(textField,buttonGenerateNew,buttonGenerateAdd,buttonSaveImages,buttonDeleteSelected,buttonReset,buttonNodeEditor);

        

        //! build implementation of deleteSelected buttons
        // button event -> delete selected graphics
        buttonDeleteSelected.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("deleting selected items");
                
            }
        });

        // button event -> delete all images
        buttonReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    textField.setText("");
                    try {
                        makeShapes.clear();
                        tilePane.getChildren().clear();
                        
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });

        // button event -> add generated art 
        buttonGenerateAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int input;
                try {
                    input = Integer.parseInt(textField.getText());    
                    makeShapes = createShape(input);
                    tilePane.getChildren().addAll(makeShapes);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } 
        });

        // button event -> generate new set of shapes
        buttonGenerateNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int input;           
                
                // when its empty
                input = Integer.parseInt(textField.getText());    
                makeShapes = createShape(input);
                tilePane.getChildren().addAll(makeShapes);    
                // when shapes was already
                if (!makeShapes.isEmpty()) {
                    // if there was already shapes
                    makeShapes.clear();
                    tilePane.getChildren().clear();
                    input = Integer.parseInt(textField.getText());    
                    // creates shapes
                    makeShapes = createShape(input);
                    tilePane.getChildren().addAll(makeShapes);      
                }     
            } 
        });
        
        // limited to save 300 images at once
        buttonSaveImages.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //! mb build something that it always save it at the same format (x,y) 
                //! or split them every 300 to a new image
                //! when i save with too much (~300) images genereted it throws an save error -> maybe here lies the problem ?! -> mb build image with bufferedstream
                WritableImage image = tilePane.snapshot(new SnapshotParameters(), null);
                        
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
                String formattedDate = localDateTime.format(myFormatObj);
                File file = new File("art_template" + formattedDate + ".png");
                
                
                if (makeShapes.size() < 300) {
                    System.out.println("makeshape < 300");
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);    
                    } catch (Exception e) {
                        e.getStackTrace();
                    }      
                    
                } else {
                    System.out.println("cant save images cause it exceed the limit of 300.");
                }                   
            } 
        });

        //! work on node editor -> TODO.md
        // button event -> switch to node editor
        buttonNodeEditor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                
            }
        });
        
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
        
        // setup crollpane
        scrollPane.setFitToWidth(true); 
        //! as needed still show the scrollbar no matter the size of the window
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED); 
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

        // key shortcuts
        //! set shortcut to generate button -> ENTER MAKE IT alt + 1-6
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    System.out.println("Key Pressed: " + ke.getCode());
                    ke.consume(); // <-- stops passing the event to next node
                }
            }
        });

        // catch mouse event 
        //! (get position in array) compare (OBJECTS !!!) clicked element with elements of the arraylist<shapes> to get the position inside of that 
        //! (make it an obj with wrapperclass Integer)
        //! (save position to arraylist<positions>)
        //! (test if position was clicked before then remove it from arraylist<positions>)
        // tilePane.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> System.out.println(e));
        //! set listener tilepane to mark down which nodes are clicked by mouse
        //! set REGION otherwise porogram just notices when clicked exactly on the shape
        tilePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event);
                Node node = (Node)event.getSource();
                 
                System.out.println("id: " + node.getId());
            }
        });
   }
} 
