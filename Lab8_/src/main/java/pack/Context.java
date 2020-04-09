package pack;

import java.sql.*;

public class Context{

    static Context context=null;
    Connection connection=null;

    public Connection getConnection() {
        return connection;
    }

    private Context() {}

    public static Context getDbContext()
    {
        if(context==null)
        {
            context=new Context();
        }
        return new Context();
    }
    public void openConnection(String url, String user, String pass) throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(
                    url,user,pass);
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from artists");
    }
    public void closeConection() throws SQLException {
        context.connection.close();
    }

}