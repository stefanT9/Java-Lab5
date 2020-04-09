package Controller.Artists;

import Controller.IController;
import Model.Artist;

import java.sql.SQLException;

public interface IArtistController extends IController {
    void create(Artist artist) throws SQLException;
    Artist findByName(String name);
}
