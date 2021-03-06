package persistence;

import connection.Conexao;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Allan de souza melo
 */
public class UserDao {
    public void add(User user) throws Exception{
        Connection con = new Conexao().getConnection();
        String sql = "insert into tbl_users(login,password) values(?,md5(?))";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, user.getLogin());
        stmt.setString(2, user.getPassword());
        stmt.execute();
        stmt.close();
    }
    public User autenticate(User user)throws Exception{
        Connection con = new Conexao().getConnection();
        String sql = "select * from tbl_users where login=? and password=md5(?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, user.getLogin());
        stmt.setString(2, user.getPassword());
        ResultSet rs = stmt.executeQuery();
        User u = null;
        while(rs.next()){
           u = new User();
           u.setId(rs.getInt("id"));
           u.setLogin(rs.getString("login"));
           u.setPassword(rs.getString("password"));
        }
        return u;
    }
}
