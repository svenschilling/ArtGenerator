import ArtGeneratorTest;
package controller;
import javafx.scene.Scene;


public class Controller extends ArtGeneratorTest{
    
    //! switched scenes -> IMPLIMENT IT
    public void setScene(Scene scene) {
    
    }

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
