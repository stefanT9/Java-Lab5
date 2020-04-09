package Controller.Album;

import Controller.IController;
import Model.Album;

import java.sql.SQLException;

public interface IAlbumController extends IController {
    void create(Album album) throws SQLException;
    Album findByArtist(int artistId);
}
