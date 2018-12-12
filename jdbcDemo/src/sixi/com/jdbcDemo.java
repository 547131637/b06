package sixi.com;

import java.sql.*;

/**
 * Created by luozicheng on 2018/12/10.
 */
public class jdbcDemo {

    public static void main(String[] ages) throws SQLException, ClassNotFoundException {
        //select("user");
        //insert("9","libin","123");
        //delete("libin");
        updateData("6","zts","123");
    }

    private static void insert(String  id ,String name ,String password){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtils.getConnection();
            String sql="insert into user values(?,?,?)";
            statement =connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setString(2,name);
            statement.setString(3,password);
            int i = statement.executeUpdate();
            if(i>0){
                System.out.println("insert success");
            }else{
                System.out.println("insert fail");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
             e.printStackTrace();
        }finally {
            jdbcUtils.clean(connection, statement, resultSet);
        }
    }

    private static void select(String table) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtils.getConnection();
            String sql = "select * from " + table;
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String id = resultSet.getString("id");
                System.out.println("username ==" + username + "password ==" + password + "id===" + id);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.clean(connection, statement, resultSet);
        }
    }

    private static void delete(String  name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtils.getConnection();
            String sql="delete  from  user where username=?";
            statement =connection.prepareStatement(sql);
            statement.setString(1,name);
            int i = statement.executeUpdate();
            if(i > 0) {
                System.out.println("success");
            } else {
                System.out.println("fail");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.clean(connection, statement, resultSet);
        }
    }

    private static void updateData(String id ,String username,String password){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtils.getConnection();
            String sql="update user set username=?, password=? where id =?";
            statement =connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setString(3,id);
            int i = statement.executeUpdate();
            if(i > 0) {
                System.out.println("success");
            } else {
                System.out.println("fail");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.clean(connection, statement, resultSet);
        }
    }
}
