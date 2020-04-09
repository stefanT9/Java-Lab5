package Controller.Chart;

import Model.Chart;
import pack.Context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ChartController implements IChartController{
    Connection connection= null;

    public ChartController(Context context) {
        connection=context.getConnection();
    }

    @Override
    public void create(Chart chart) throws SQLException {
        List<Integer> albumIds=chart.getAlbum_ids();

        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select max(id) from  charts");
        resultSet.next();
        int idx1=resultSet.getInt(1);

        resultSet=statement.executeQuery("select max(id) from  lists");
        resultSet.next();
        int idx2=resultSet.getInt(1);
        String sql =String.format("insert into charts values ('%d','%d')",idx1+1,idx2);

        statement.executeUpdate(sql);
        for(Integer id:albumIds)
        {
            sql =String.format("insert into lists values ('%d','%d')",idx2,id);
            statement.executeUpdate(sql);
        }
        statement.close();
    }

    @Override
    public Chart findById(int id) {
        try {
            Statement statement = connection.createStatement();
            List<Integer> albums_id = new LinkedList<>();

            String sql = String.format("select * from lists where 'id'=%d", id);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                albums_id.add(resultSet.getInt(2));
            }
            return new Chart(id, albums_id);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Integer> getAllIds() {
        try {
            Statement statement = connection.createStatement();
            String sql=String.format("select id from charts");
            ResultSet resultSet = statement.executeQuery(sql);
            List<Integer> idList=new LinkedList<>();
            resultSet.next();

            while (resultSet.isAfterLast());
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
