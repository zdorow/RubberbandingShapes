package rubberbanding;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 *
 * @author ZD
 */
public class Rubberbanding extends Application
{
    private Circle currentCircle;
    private Rectangle currentRectangle;
    private Ellipse currentEllipse;
    private Line radiusLine;
    private Group root;
    private double x1, x2, y1, y2;
    

    //--------------------------------------------------------------------
    //  Displays an initially empty scene, waiting for the user to
    //  draw circles with the mouse.
    //--------------------------------------------------------------------
    public void start(Stage primaryStage)
    {

        root = new Group();
        
        Scene scene = new Scene(root, 1000, 1000, Color.BLACK);
        
        scene.setOnMousePressed(this::processMousePress);
        scene.setOnMouseDragged(this::processMouseDrag);

        primaryStage.setTitle("Rubber Circles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //--------------------------------------------------------------------
    //  Adds a new circle to the scene when the mouse button is pressed.
    //--------------------------------------------------------------------
    public void processMousePress(MouseEvent event)
    {
        radiusLine = new Line(event.getX(), event.getY(), event.getX(), event.getY());
        if (null == event.getButton()) {
        } else {
            switch (event.getButton()) {
                case PRIMARY:
                    x1 = event.getX();
                    y1 = event.getY();
                    currentCircle = new Circle(event.getX(), event.getY(), 1 );
                    currentCircle.setStroke(Color.CYAN);
                    currentCircle.setStrokeWidth(3);
                    currentCircle.setFill(Color.TRANSPARENT);
                    root.getChildren().add(currentCircle);
                    break;
                case SECONDARY:
                    x1 = event.getX();
                    y1 = event.getY();
                    currentRectangle = new Rectangle(event.getX(), event.getY(), 1 , 1);
                    currentRectangle.setStroke(Color.MAROON);
                    currentRectangle.setStrokeWidth(3);
                    currentRectangle.setFill(Color.TRANSPARENT);
                    root.getChildren().add(currentRectangle);
                    break;
                case MIDDLE:
                    x1 = event.getX();
                    y1 = event.getY();
                    currentEllipse = new Ellipse (event.getX(), event.getY(), 1 , 1);                    
                    currentEllipse.setStroke(Color.BLANCHEDALMOND);
                    currentEllipse.setStrokeWidth(3);
                    currentEllipse.setFill(Color.TRANSPARENT);
                    root.getChildren().add(currentEllipse);
                    break;
                default:
                    break;
            }
        }
    }

    //--------------------------------------------------------------------
    //  Updates the end point of the radius line as the mouse is
    //  dragged, creating the rubber band effect for the radius.
    //--------------------------------------------------------------------
    public void processMouseDrag(MouseEvent event)
    {
        double length = Math.sqrt( ( ( x2 - x1 ) * ( x2 - x1 ) ) + ( ( y2 - y1 ) * ( y2 - y1 ) ) );
        double width = Math.sqrt((x2 - x1 ) * ( x2 - x1 ));
        double height = Math.sqrt(( y2 - y1 ) * ( y2 - y1 ));
        
        if (null != event.getButton())
        switch (event.getButton()) {
            case PRIMARY:{
                x2 = event.getX();
                y2 = event.getY();
                radiusLine.setEndX(event.getX());
                radiusLine.setEndY(event.getY());
                
                currentCircle.setRadius(length);
                break;
                }
            case SECONDARY:
                x2 = event.getX();
                y2 = event.getY();
                radiusLine.setEndX(event.getX());
                radiusLine.setEndY(event.getY());
                currentRectangle.setHeight(height);
                currentRectangle.setWidth(width);
                break;
            case MIDDLE:{
                x2 = event.getX();
                y2 = event.getY();
                radiusLine.setEndX(event.getX());
                radiusLine.setEndY(event.getY());                
                double radiusY = height;
                double radiusX = length + width;
                currentEllipse.setRadiusX(radiusX);
                currentEllipse.setRadiusY(radiusY);
                break;
                }
            default:
                break;
        }
    }
  
    public static void main(String[] args)
    {
        launch(args);
    }


}

