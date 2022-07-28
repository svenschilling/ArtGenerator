import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class ShapeCreator {
    private int numberOfShapes;

    ShapeCreator(int numberOfShapes){
        this.numberOfShapes = numberOfShapes;
    }
    
    private static int randomInt(int min, int max)
    {
         return (int) (Math.random()*(max-min))+min;
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
            randomShapeOperation = randomInt(1, 4);
            randomShape1 = randomInt(1, 4);
            randomShape2 = randomInt(1, 4);
            randomWidth = randomInt(20, 60);
            randomHeight = randomInt(20, 70);
            randomRadius = randomInt(20, 90);
            randomRotation = randomInt(0, 180);
            
            //! go deeper into configuration of shapes + make use of other like bezier curve
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
                randomShapeOperation = randomInt(1, 4);
                randomRotation = randomInt(0, 360);

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
                        // unionShape.setScaleX(randomScaleX = random.nextDouble());
                        // unionShape.setScaleY(randomScaleY = random.nextDouble());

                        mergedShapeList.add(unionShape);
                        break;
                    case 1:
                        Shape intersectShape = Shape.intersect(shapeList.get(counter), shapeList.get(counter+1));
                        intersectShape.setRotate(randomRotation);
                        // intersectShape.setScaleX(randomScaleX = random.nextDouble());
                        // intersectShape.setScaleY(randomScaleY = random.nextDouble());
            
                        mergedShapeList.add(intersectShape);
                        break;           
                }
                condition++; 
                counter++;
            } while (condition == iterations * numberOfShapes);
        }
        
        return mergedShapeList;
    }
}
