package Controller.Album;

import Model.Album;
import Model.Chart;
import pack.Context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AlbumController implements IAlbumController {

    Connection connection= null;
    public AlbumController(Context context) {
        connection=context.getConnection();
    }

    @Override
    public void create(Album album) throws SQLException {
        String name=album.getName();
        int artistId=album.getArtist_id();
        int releaseYear=album.getRelease_year();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select max(id) from  albums");
        resultSet.next();
        int idx=resultSet.getInt(1);
        String sql=String.format("insert into albums values('%d','%s','%d','%s')",idx+1,name,artistId,releaseYear);
        statement.executeUpdate(sql);
        statement.close();
    }

    @Override
    public Album findByArtist(int artistId) {
        try {
            Statement statement = connection.createStatement();
            String sql=String.format("select * from albums" +
                    "where artist_id='%d'",artistId);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

            Album album = new Album(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),resultSet.getInt(4));
            return album;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored);
            return null;
        }    }

    @Override
    public List<Integer> getAllIds() {
        try {
            Statement statement = connection.createStatement();
            String sql=String.format("select id from albums;");
            ResultSet resultSet = statement.executeQuery(sql);
            List<Integer> idList=new LinkedList<>();
            resultSet.next();

            while (!resultSet.isAfterLast())
            {
                idList.add(resultSet.getInt(1));
                resultSet.next();
            }

            return idList;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored);
            return null;
        }
    }

    @Override
    public List<Chart> getAllElements() {
        return null;
    }
}
