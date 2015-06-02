import com.sun.istack.internal.Nullable;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Student on 02.06.2015.
 */
public class JDBConnector
{
    private static final String IP = "localhost";
    private static final String PORT = "3306";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="";
    private Connection connection;

    public void connect () throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        connection =DriverManager.getConnection("jdbc:mysql://"+IP+":"+PORT+"/test",USERNAME,PASSWORD);

    }

    public  void disconnect() throws SQLException
    {
        connection.close();
    }

    public void insert(String name, String phone, @Nullable String email) throws SQLException
    {
        String query = "INSERT INTO test_table" +"(name, phone, email) VALUES ('"+ name + "','" + phone + "','" + email + "')";
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.close();
    }

    public void selectByphone (String phone) throws SQLException {
        String query = "SELECT * FROM test_table WHERE phone ='" + phone + "'";
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(query);
        result.next();
        System.out.println(result.getInt("id") + " " + result.getString("name") + " " + result.getString("phone") + " " + result.getString("email"));
        statement.close();
    }

    public void selectByName (String name) throws SQLException {
        String query = "SELECT * FROM test_table WHERE name ='" + name + "'";
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(query);
        while (result.next())
        {
            System.out.println(result.getInt("id") + " " + result.getString("name") + " " + result.getString("phone") + " " + result.getString("email"));
        }
        statement.close();
    }

    public ArrayList<String> getAllemails() throws SQLException {
        ArrayList<String>emails = new ArrayList<String>();
        String query = "SELECT email FROM test_table";

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(query);

        while (result.next())
        {
            String email = result.getString("email");
            emails.add (email);
        }

        statement.close();
        return emails;
    }


}