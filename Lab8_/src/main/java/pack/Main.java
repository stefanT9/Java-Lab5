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

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        String url="jdbc:mysql://localhost:3306/testDB";
        String username="stefan";
        String password="sintaxaparola";

        Context context=Context.getDbContext();
        try {
            context.openConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        populate(context);


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
        System.out.println(artistsId);
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
        System.out.println(albumsId);
        for(int i=0;i<100;i++)
        {
            List<Integer> albumsIdList=new LinkedList<>();
            for(int j=0;i<10;i++)
            {
                int albumIdx=faker.number().numberBetween(0,albumsId.size()-1);
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
        System.out.println(chartController.getAllIds());
    }
}
