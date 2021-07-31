/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannh.dao;

import toannh.dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HuuToan
 */
public class UserDAO {
    public UserDTO checkLogin(String username, String password) throws SQLException {
         Connection con = null;
         PreparedStatement stm = null;
         ResultSet rs = null;
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT UserID, UserName, RoleID "
                        + "FROM tblUsers "
                        + "WHERE UserName = ? AND PassWord = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    UserDTO u = new UserDTO(rs.getInt("UserID"), rs.getString("UserName"), "****", rs.getInt("RoleID"));
                    return u;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
