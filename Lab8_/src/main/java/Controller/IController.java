package Controller;

import Model.Chart;

import java.util.List;

public interface IController {
    public List<Integer> getAllIds();
    public List<Chart> getAllElements();

}
