package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class Controller {

    public Canvas canvas;
    public ConfigurationPanel configurationPanel;
    public ControlPannel controlPanel;
    public ShapesPanel shapesPanel;
    public Button saveButton;
    public Button loadButton;
    public Button clearButton;
    public Button exitButton;
    public TextField thicknessTextField;
    public Button selectCircleButton;
    public Button selectSquareButton;
    public BorderPane mainLayoutBorderPane;
    public Shape chosenShape= Shape.Circle;
    public Button selectRectangleButton;

    public Controller() {
    }

    private void drawShape(double x,double y)
    {
        Shape selectedShape=configurationPanel.getSelectedShape();

        ;
        System.out.println(x);
        System.out.println(y);
        System.out.println(selectedShape);
        System.out.println(configurationPanel.getSizeAtribute());


        if(selectedShape==Shape.Square)
        {
            canvas.getGraphicsContext2D().setFill(Color.VIOLET);

            double h,w;
            h=configurationPanel.getSizeAtribute().get(0);
            w=configurationPanel.getSizeAtribute().get(0);
            canvas.getGraphicsContext2D().setFill(Color.VIOLET);
            canvas.getGraphicsContext2D().fillRect(x-w/2,y-h/2,w,h);
        }
        else if (selectedShape==Shape.Circle)
        {
            canvas.getGraphicsContext2D().setFill(Color.VIOLET);
            double h,w;
            h=configurationPanel.getSizeAtribute().get(0);
            w=configurationPanel.getSizeAtribute().get(0);
            canvas.getGraphicsContext2D().fillOval(x-w/2,y-h/2,w,h);
        }
        else if(selectedShape==Shape.Rectangle)
        {
            canvas.getGraphicsContext2D().setFill(Color.VIOLET);

            double h,w;
            h=configurationPanel.getSizeAtribute().get(0);
            w=configurationPanel.getSizeAtribute().get(1);
            canvas.getGraphicsContext2D().setFill(Color.VIOLET);
            canvas.getGraphicsContext2D().fillRect(x-w/2,y-h/2,w,h);
        }
    }

    @FXML
    private void initialize() {
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.getGraphicsContext2D().setFill(Color.VIOLET);


        canvas.setOnMouseClicked(click -> drawShape(click.getX(),click.getY()));
        canvas.setOnMouseDragged(mouseEvent -> {
            drawShape(mouseEvent.getX(),mouseEvent.getY());
        });

        saveButton.setOnMouseClicked(click -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image","*.png","*.jpg"));
            File file = fileChooser.showSaveDialog(mainLayoutBorderPane.getScene().getWindow());

            GraphicsContext gc=canvas.getGraphicsContext2D();
            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);

            WritableImage wi = new WritableImage((int)canvas.getWidth(),(int)canvas.getHeight());

            if(file!= null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(sp, wi), null), "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        loadButton.setOnMouseClicked(click -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Image");
            ///fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image","*.png","*.jpg"));
            File file = fileChooser.showOpenDialog(mainLayoutBorderPane.getScene().getWindow());

            if(file!= null) {
                try {
                    InputStream inputStream=new FileInputStream(file.getAbsolutePath());
                    Image image=new Image(inputStream);
                    canvas.getGraphicsContext2D().drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        clearButton.setOnMouseClicked(click-> {
            canvas.getGraphicsContext2D().setFill(Color.WHITE);
            canvas.getGraphicsContext2D().fillRect(0,0,canvas.getWidth(),canvas.getHeight());
            canvas.getGraphicsContext2D().setFill(Color.VIOLET);
        });
        exitButton.setOnMouseClicked(click -> {
            exit(0);
        });


        selectCircleButton.setOnMouseClicked(click->{
            configurationPanel.updateFor(Shape.Circle);
        });
        selectSquareButton.setOnMouseClicked(click->{
            configurationPanel.updateFor(Shape.Square);
        });
        selectRectangleButton.setOnMouseClicked(click->{
            configurationPanel.updateFor(Shape.Rectangle); });
    }

    @FXML
    private void printOutput() {
    }

}
