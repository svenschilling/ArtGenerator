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
import javafx.scene.layout.HBox;
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
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

// shortcuts
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class ArtGenerator extends Application {
    ArrayList<Shape> makeShapes;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // primaryStage elements
        BorderPane root = new BorderPane();
        HBox hBox = new HBox();
        TilePane tilePane = new TilePane();
        TextField textField = new TextField();
        ScrollPane scrollPane = new ScrollPane(tilePane);
        // nodeEditorStage
        Stage nodeEditorStage = new Stage();

        // hBox elements primaryStage
        Button buttonGenerateAdd = new Button("generate add");
        Button buttonSaveImages = new Button("save images");
        Button buttonReset = new Button("clear fields");
        Button buttonDeleteSelected = new Button("delete selected");
        Button buttonGenerateNew = new Button("generate new");
        Button buttonNodeEditor = new Button("node editor");
        // hBox elements nodeEditorStage
        Button buttonBackToMain = new Button("to generator");

        double minWidthWindow = 800;
        double minHeightWindow = 600;
        int tileWidth = 50;
        int tileHeight = 50;
        
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
                    makeShapes.clear();
                    tilePane.getChildren().clear();
                    textField.setText("");
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

        //! doesnt work when nothing was generated before 
        buttonGenerateNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int input;
                /*
                // only reset when its not empty array and tilepane from shapes
                if(!makeShapes.isEmpty()) {
                    makeShapes.clear();
                    tilePane.getChildren().clear();
                }
                */
                
                try {
                    input = Integer.parseInt(textField.getText());    
                    makeShapes = createShape(input);
                    tilePane.getChildren().addAll(makeShapes);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } 
        });
        
        //! when i save with too much (~300) images genereted it throws an save error
        buttonSaveImages.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WritableImage image = tilePane.snapshot(new SnapshotParameters(), null);
                
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
                String formattedDate = localDateTime.format(myFormatObj);
                
                File file = new File("art_template" + formattedDate + ".png");
                                
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);    
                } catch (Exception e) {
                    e.getStackTrace();
                }      
            } 
        });

        //! work on node editor -> TODO.md
        buttonNodeEditor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                
            }
        });
        
        //! show a little number in the upper top corner of each box
        //! adjust padding
        // tilepane elements
        tilePane.setPadding(new Insets(10, 10, 10, 10));
        tilePane.setPrefSize(tileWidth, tileHeight);
        tilePane.prefWidthProperty().bind(root.widthProperty());
              
        Scene scene = new Scene(root, 800, 600);

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
            //! pick randomly circle or rectangle  
            //! its creating empty spaces that shouldnt exists!
            switch (randomShapeOperation) {
                case 3:
                    Shape substractShape = Shape.subtract(circle, rectangle);
                    substractShape.setRotate(randomRotation);
                    substractShape.setScaleX(randomScaleX = random.nextDouble());
                    substractShape.setScaleY(randomScaleY = random.nextDouble());
                    
                    shapeList.add(substractShape);
                    break;
                case 2:
                    Shape unionShape = Shape.union(circle, rectangle);
                    unionShape.setRotate(randomRotation);
                    unionShape.setScaleX(randomScaleX = random.nextDouble());
                    unionShape.setScaleY(randomScaleY = random.nextDouble());

                    shapeList.add(unionShape);
                    break;
                case 1:
                    Shape intersectShape = Shape.intersect(circle, rectangle);
                    intersectShape.setRotate(randomRotation);
                    intersectShape.setScaleX(randomScaleX = random.nextDouble());
                    intersectShape.setScaleY(randomScaleY = random.nextDouble());
        
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
}
