package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Controller {
    public Canvas canvas;
    public Button prettifyButton;
    public Button addNodeButton;
    public Button removeNodeButton;
    public Button addEdgeButton;
    public Button removeEdgeButton;

    public List<Pair<Double,Double>> nodes;
    public List<List<Integer>> graph;
    public Double radius=10D;
    public Action selectedAction;

    @FXML
    private void initialize() {
        selectedAction = Action.addNode;

        prettifyButton.setOnMouseClicked(__   -> prettify());
        addEdgeButton.setOnMouseClicked( __   -> selectedAction=Action.addEdge );
        addNodeButton.setOnMouseClicked( __   -> selectedAction=Action.addNode );
        removeEdgeButton.setOnMouseClicked( __-> selectedAction=Action.removeEdge );
        removeNodeButton.setOnMouseClicked( __-> selectedAction=Action.removeNode );

        nodes = new LinkedList<>();
        graph = new LinkedList<>();

        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getWidth());

        canvas.getGraphicsContext2D().setFill(Color.VIOLET);

        AtomicReference<Double> x1 = new AtomicReference<>((double) 0);
        AtomicReference<Double> y1 = new AtomicReference<>((double) 0);
        AtomicBoolean flag = new AtomicBoolean(false);

        canvas.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            switch (selectedAction) {
                case addEdge: {
                    if (!flag.get()) {
                        x1.set(x);
                        y1.set(y);
                        flag.set(true);
                    } else {
                        addEdge(x1.get(), y1.get(), x, y);
                        flag.set(false);
                    }
                    break;
                }
                case addNode: {
                    addNode(x, y);
                    break;
                }
                case removeEdge: {
                    removeEdge(x, y);
                    break;
                }
                case removeNode: {
                    removeNode(x, y);
                    break;
                }
            }
            System.out.println(graph);

        });
    }
    private void prettify()
    {

        System.out.println("not implemented");
    }
    private void addEdge(double x1,double y1,double x2,double y2) {
        var node1 = Util.closestPoint(nodes, x1, y1, radius);
        var node2 = Util.closestPoint(nodes, x2, y2, radius);

        if(node1==null || node2==null)
            return;

        Integer idx1 = nodes.indexOf(node1);
        Integer idx2 = nodes.indexOf(node2);
        if (!graph.get(idx1).contains(idx2)) {
            graph.get(idx1).add(idx2);
            graph.get(idx2).add(idx1);
            canvas.getGraphicsContext2D().setStroke(Color.VIOLET);
            canvas.getGraphicsContext2D().strokeLine(node1.getKey(),node1.getValue(),node2.getKey(),node2.getValue());
        }
    }
    private void removeNode(double x, double y) {
        var node=Util.closestPoint(nodes,x,y,radius);
        if(node!=null)
        {
            Integer idx=nodes.indexOf(node);

            for (var idx2:graph.get(idx))///stergem muchiile
            {
                graph.get(idx2).remove(idx);
                var node2=nodes.get(idx2);
                canvas.getGraphicsContext2D().setStroke(Color.WHITE);
                canvas.getGraphicsContext2D().strokeLine(node.getKey(),node.getValue(),node2.getKey(),node2.getValue());
                canvas.getGraphicsContext2D().setStroke(Color.VIOLET);

                canvas.getGraphicsContext2D().fillOval(node2.getKey()-radius,node2.getValue()-radius,radius*2,radius*2);
            }


            nodes.remove(node);
            graph.remove((int)idx);
            System.out.println("removed node "+idx);

            canvas.getGraphicsContext2D().setFill(Color.WHITE);
            canvas.getGraphicsContext2D().fillOval(node.getKey()-radius,node.getValue()-radius,radius*2,radius*2);
            canvas.getGraphicsContext2D().setFill(Color.VIOLET);

            for(var i=0;i<graph.size();i++)
            {
                for(var j = 0; j<graph.get(i).size(); j++)
                {
                    if(graph.get(i).get(j)>idx)
                    {
                        var oldVal=graph.get(i).get(j);
                        graph.get(i).set(j,oldVal-1);
                    }
                }
            }
        }
    }

    private void removeEdge(double x, double y) {
        for(Integer idx1=0;idx1<graph.size();idx1++)
        {
            for(Integer idx2=0;idx2<graph.get(idx1).size();idx2++) {
                var node1 = nodes.get(idx1);
                var node2 = nodes.get(idx2);

                var n = (node1.getValue() - node2.getValue()) / (node1.getKey() - node2.getKey());
                var m = node1.getValue() - n * node1.getKey();
                var epsilon = 4;

                if (Math.abs(n * x + m - y) < epsilon)
                {

                    canvas.getGraphicsContext2D().setStroke(Color.WHITE);
                    canvas.getGraphicsContext2D().strokeLine(node1.getKey(),node1.getValue(),node2.getKey(),node2.getValue());
                    canvas.getGraphicsContext2D().setStroke(Color.VIOLET);

                    canvas.getGraphicsContext2D().setFill(Color.VIOLET);
                    canvas.getGraphicsContext2D().fillOval(node1.getKey()-radius,node1.getValue()-radius,radius*2,radius*2);
                    canvas.getGraphicsContext2D().fillOval(node2.getKey()-radius,node2.getValue()-radius,radius*2,radius*2);


                    graph.get(idx1).remove(idx2);
                    graph.get(idx2).remove(idx1);

                    return;
                }
            }
        }
    }

    void addNode(double x,double y) {
        if(Util.closestPoint(nodes,x,y,radius*2)==null)
        {
            nodes.add(new Pair<Double, Double>(x,y));
            canvas.getGraphicsContext2D().fillOval(x-radius,y-radius,radius*2,radius*2);
            graph.add(new LinkedList<>());
        }
        else
        {
            System.out.println("too close");
        }
    }
}
