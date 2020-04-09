package Controller.Artists;

import Model.Artist;
import Model.Chart;
import pack.Context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ArtistsController implements IArtistController {
    Connection connection= null;
    public ArtistsController(Context context) {
        connection=context.getConnection();
    }

    @Override
    public void create(Artist artist) throws SQLException {
        String name=artist.getName();
        String country=artist.getCountry();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select max(id) from  artists");
        resultSet.next();
        int idx=resultSet.getInt(1);
        String sql=String.format("insert into artists values('%d','%s','%s')",idx+1,name,country);
        statement.executeUpdate(sql);
        statement.close();
    }

    @Override
    public Artist findByName(String name) {
        try {
            Statement statement = connection.createStatement();
            String sql=String.format("select * from artists where name='%s'",name);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

            Artist artist = new Artist(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            return artist;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored);
            return null;
        }
    }

    @Override
    public List<Integer> getAllIds() {
        try {
            Statement statement = connection.createStatement();
            String sql=String.format("select id from artists;");
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
