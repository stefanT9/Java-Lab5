package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;


public class ConfigurationPanel extends FlowPane {

    private Shape selectedShape;
    public ConfigurationPanel()
    {
        selectedShape=Shape.Circle;
    }

    public void updateFor(Shape shape) { ;
        while(this.getChildren().size()>0)
        {
            this.getChildren().remove(0);
        }

        if(shape==Shape.Circle)
        {
            selectedShape=Shape.Circle;
            Label label=new Label("radius:");
            TextField radiusTextField=new TextField();
            this.getChildren().addAll(label,radiusTextField);
        }
        else if(shape==Shape.Square)
        {
            selectedShape=Shape.Square;
            Label label=new Label("length:");
            TextField radiusTextField=new TextField();
            this.getChildren().addAll(label,radiusTextField);
        }
        else if(shape==Shape.Rectangle)
        {
            selectedShape=Shape.Rectangle;
            Label label1=new Label("width:");
            TextField heightTextField=new TextField();
            Label label2=new Label("height:");
            TextField widthTextField=new TextField();
            this.getChildren().addAll(label1,heightTextField,label2,widthTextField);
        }
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
    }

    public List<Double> getSizeAtribute() {
        List<Double> sizeAtributes=new LinkedList<>();
        TextField field= (TextField) this.getChildren().get(1);
        sizeAtributes.add(Double.parseDouble(field.getText()));

        if(selectedShape==Shape.Rectangle)
        {
            field= (TextField) this.getChildren().get(3);
            sizeAtributes.add(Double.parseDouble(field.getText()));
        }

        return sizeAtributes;

    }
}
