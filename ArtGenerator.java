import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.shape.SVGPath;
import javafx.geometry.Insets;

// needed to save the image
import java.io.File;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

// shortcuts
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ArtGenerator extends Application {

    ArrayList<Shape> makeShapes;
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        // node attributes
        double minWidthWindow = 800;
        double minHeightWindow = 600;
        int tileWidth = 200;
        int tileHeight = 200;
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
                System.out.println("empty");
                input = Integer.parseInt(textField.getText());    
                makeShapes = createShape(input);
                tilePane.getChildren().addAll(makeShapes);    
                // when shapes was already
                if (!makeShapes.isEmpty()) {
                    // if there was already shapes
                    System.out.println("making shapes");
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
        // button event -> swithc to node editor
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
        
        //! size isnt always the same | when setting min and max size then children are shown vertical (scrollpane also isnt working then)
        // set fixed size 
        tilePane.setPrefSize(tileWidth, tileHeight);
        // tilePane.setMinSize(tileWidth, tileHeight);
        // tilePane.setMaxSize(tileWidth, tileHeight);

        // binds the whole tilePane to the width of scrollPane
        tilePane.prefWidthProperty().bind(root.widthProperty());
        tilePane.prefHeightProperty().bind(root.heightProperty());
              
        Scene scene = new Scene(root, 820, 620);

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
        // prevents hScrollBar 
        scrollPane.setFitToWidth(true); 

        // key shortcuts
        //! set shortcut to generate button -> ENTER
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
            };
        });


    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static int random_int(int Min, int Max)
    {
         return (int) (Math.random()*(Max-Min))+Min;
    }
    
    public ArrayList<Shape> createShape(int numberOfShapes) {
        ArrayList<Shape> shapeList = new ArrayList<>();
        ArrayList<Shape> mergedShapeList = new ArrayList<>();
           
        Random random = new Random();
        
        int randomRadius, 
            randomWidth,  
            randomHeight, 
            randomShapeOperation,
            randomMergedShapeOperation,
            randomRotation,
            randomShape1,
            randomShape2;

        double randomScaleX,
               randomScaleY; 
        
        int condition = 0;
        int iterations = 10;
        int counter = 0;

        // needs times of iterations the amount of shapes
        for (int i = 0; i < numberOfShapes * iterations; i++) {
            randomShapeOperation = random_int(1, 4);
            randomShape1 = random_int(1, 4);
            randomShape2 = random_int(1, 4);
            randomWidth = random_int(20, 60);
            randomHeight = random_int(20, 70);
            randomRadius = random_int(20, 90);
            randomRotation = random_int(0, 180);
            
            //! ho deeper into configuration of shapes + make use of other like bezier curve
            // triangle
            SVGPath triangle = new SVGPath();      
            //! make these creation numbers also random 
            String path = "M 100 100 L 300 100 L 200 300 z";  
            triangle.setContent(path);  
            
            Circle circle = new Circle(randomRadius, Color.BLACK);
            Rectangle rectangle = new Rectangle(randomWidth, randomHeight, Color.BLACK);
            
            // shape operations
            //! adjust algo cause sometimes shapes get completly cleared
            //! pick randomly circle or rectangle  
            //! its creating empty spaces that shouldnt exists!
            switch (randomShapeOperation) {
                case 3:
                    Shape substractShape = Shape.subtract(circle, rectangle);
                    substractShape.setRotate(randomRotation);
                    // substractShape.setScaleX(randomScaleX = random.nextDouble());
                    // substractShape.setScaleY(randomScaleY = random.nextDouble());
                    
                    shapeList.add(substractShape);
                    break;
                case 2:
                    Shape unionShape = Shape.union(circle, rectangle);
                    unionShape.setRotate(randomRotation);
                    // unionShape.setScaleX(randomScaleX = random.nextDouble());
                    // unionShape.setScaleY(randomScaleY = random.nextDouble());

                    shapeList.add(unionShape);
                    break;
                case 1:
                    Shape intersectShape = Shape.intersect(circle, rectangle);
                    intersectShape.setRotate(randomRotation);
                    // intersectShape.setScaleX(randomScaleX = random.nextDouble());
                    // intersectShape.setScaleY(randomScaleY = random.nextDouble());
        
                    shapeList.add(intersectShape);
                    break;   
            }
        }
        
        for (int i = 0; i < numberOfShapes; i++) {      
            do { 
                randomShapeOperation = random_int(1, 4);
                randomRotation = random_int(0, 360);

                switch (randomShapeOperation) {
                    case 3:
                        Shape substractShape = Shape.subtract(shapeList.get(counter), shapeList.get(counter+1));
                        substractShape.setRotate(randomRotation);
                        // substractShape.setScaleX(randomScaleX = random.nextDouble());
                        // substractShape.setScaleY(randomScaleY = random.nextDouble());

                        mergedShapeList.add(substractShape);
                        break;
                    case 2:
                        Shape unionShape = Shape.union(shapeList.get(counter), shapeList.get(counter+1));
                        unionShape.setRotate(randomRotation);
                        //unionShape.setScaleX(randomScaleX = random.nextDouble());
                        //unionShape.setScaleY(randomScaleY = random.nextDouble());

                        mergedShapeList.add(unionShape);
                        break;
                    case 1:
                        Shape intersectShape = Shape.intersect(shapeList.get(counter), shapeList.get(counter+1));
                        intersectShape.setRotate(randomRotation);
                        //intersectShape.setScaleX(randomScaleX = random.nextDouble());
                        //intersectShape.setScaleY(randomScaleY = random.nextDouble());
            
                        mergedShapeList.add(intersectShape);
                        break;           
                }
                condition++; 
                counter++;
            } while (condition == iterations * numberOfShapes);
        }
        
        return mergedShapeList;
    }

    // mark selected with a black border
    public void markSelectedGraphics () {
        //! build mouse event that pick ups marked numbers and then show a border around them    
    }

    protected ArrayList<Prefab> savePrefabList() {

        ArrayList<Prefab> prefabConfigs = null;
        

        return prefabConfigs;
    }


    
    
}
