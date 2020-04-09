package Controller.Chart;

import Controller.IController;
import Model.Artist;
import Model.Chart;

import java.sql.SQLException;
import java.util.List;

public interface IChartController extends IController {
    void create(Chart chart) throws SQLException;
    Chart findById(int id);

}
