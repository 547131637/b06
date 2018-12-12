package sixi.com;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by luozicheng on 2018/12/10.
 */
public class jdbcUtils {
    public static String driver;
    public static String url;
    public static String username;
    public  static String password;

/*    static {
        driver = ResourceBundle.getBundle("db.properties").getString("driver");
        url = ResourceBundle.getBundle("db.properties").getString("url");
        username = ResourceBundle.getBundle("db.properties").getString("username");
        password = ResourceBundle.getBundle("db.properties").getString("password");
    }*/

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        driver="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost:3306/iot";
        username="root";
        password="luozicheng";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    public static void clean(Connection con,Statement statement, ResultSet resultSet ){
        if (con !=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con =null;
        }

        if(statement !=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement=null;
        }

        if(resultSet !=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet=null;
        }
    }
}
