package pack;

import Controller.Album.AlbumController;
import Controller.Album.IAlbumController;
import Controller.Artists.ArtistsController;
import Controller.Artists.IArtistController;
import Controller.Chart.ChartController;
import Controller.Chart.IChartController;
import Model.Album;
import Model.Artist;
import Model.Chart;
import com.github.javafaker.Faker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        String url="jdbc:mysql://localhost:3306/testDB";
        String username="user";
        String password="parola";

        Context context=Context.getDbContext();
        try {
            context.openConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        populate(context);

        printTopArtists(context);
    }

    private static void printTopArtists(Context context) {
        String sql="select a.name, count(a.name) " +
                "from charts v_charts " +
                "join lists v_list on v_charts.list_id = v_list.id " +
                "join albums v_album on v_album.id=v_list.album_id " +
                "join artists a on v_album.artist_id = a.id " +
                "group by a.id " +
                "order by 2 desc;";

        try {
            Statement statement=context.connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            res.next();
            int head=10;
            while (!res.isAfterLast() && head>=0)
            {
                head--;
                String name=res.getString(1);
                Integer appearances = res.getInt(2);
                System.out.println(String.format("%s apare in %d charturi",name,appearances));
                res.next();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    static public void populate(Context context)
    {
        IArtistController artistController=new ArtistsController(context);
        IAlbumController albumController=new AlbumController(context);
        IChartController chartController=new ChartController(context);

        Faker faker=Faker.instance();
        for (int i=0;i<1000;i++)
        {
            Artist artist=new Artist(faker.country().name(),faker.name().firstName());
            try {
                artistController.create(artist);
            } catch (SQLException throwables) {
                System.out.println(throwables);
            }
        }
        System.out.println("artisti creat");
        List<Integer> artistsId=artistController.getAllIds();

        for(int i=0;i<1000;i++)
        {
            Integer artistId=artistsId.get(faker.number().numberBetween(0,artistsId.size()-1));
            Album album=new Album(faker.name().title(),artistId,faker.number().numberBetween(1990,2020));
            try {
                albumController.create(album);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        List<Integer> albumsId=albumController.getAllIds();
        System.out.println("albums created");

        for(int i=0;i<100;i++)
        {
            List<Integer> albumsIdList=new LinkedList<>();
            for(int j=0;j<10;j++)
            {
                int albumIdx=faker.number().numberBetween(0,albumsId.size()-1);;
                albumsIdList.add(albumsId.get(albumIdx));
            }
            Chart chart=new Chart(albumsIdList);
            try {
                chartController.create(chart);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        System.out.println("Charts created");
    }
}
