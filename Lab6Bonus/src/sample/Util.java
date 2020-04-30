package sample;

import javafx.util.Pair;

import java.util.List;

public class Util {
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static Pair<Double, Double> closestPoint(List<Pair<Double,Double>> points, double x, double y, double maxDistance)
    {
        if(points.isEmpty()) return null;
        Pair<Double, Double> best = points.get(0);

        for(var el:points)
        {
            if(distance(el.getKey(),el.getValue(),x,y)<distance(best.getKey(),best.getValue(),x,y))
            {
                best=el;
            }
        }

        if(distance(best.getKey(),best.getValue(),x,y)>maxDistance)
        {
            return null;
        }
        else
        {
            return best;
        }
    }
}
