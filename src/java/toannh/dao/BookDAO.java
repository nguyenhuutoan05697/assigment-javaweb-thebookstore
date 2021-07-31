/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannh.dao;

import toannh.dto.BookDTO;
import toannh.dto.CategoryDTO;
import toannh.dto.DetailDTO;
import toannh.dto.OrderDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HuuToan
 */
public class BookDAO {

    public ArrayList<BookDTO> getListBook() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<BookDTO> list = new ArrayList<>();
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT BookID, BookName, image, Price, quantity, Author, CurrentDate, Status, CategoryID "
                        + "FROM tblBook ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new BookDTO(rs.getInt("BookID"),
                            rs.getString("BookName"),
                            rs.getString("image"),
                            rs.getFloat("Price"),
                            rs.getInt("quantity"),
                            rs.getString("Author"),
                            rs.getString("CurrentDate"),
                            rs.getBoolean("Status"),
                            rs.getInt("CategoryID")));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return list;
    }

    public ArrayList<CategoryDTO> getListCategory() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<CategoryDTO> list = new ArrayList<>();
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT CategoryID, CategoryName "
                        + "FROM tblCategory ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new CategoryDTO(rs.getInt("CategoryID"), rs.getString("CategoryName")));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return list;
    }

    public BookDTO searchBookbyID(int ID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BookDTO b = new BookDTO();
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT BookID, BookName, image, Price, quantity, Author, CurrentDate, Status, CategoryID "
                        + "FROM tblBook "
                        + "WHERE BookID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    b = new BookDTO(rs.getInt("BookID"),
                            rs.getString("BookName"),
                            rs.getString("image"),
                            rs.getInt("Price"),
                            rs.getInt("quantity"),
                            rs.getString("Author"),
                            rs.getString("CurrentDate"),
                            rs.getBoolean("Status"),
                            rs.getInt("CategoryID"));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return b;
    }

    public int updateBook(BookDTO b) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE tblBook "
                        + "SET BookName=?, image=?, Price=?, quantity=?, Author=?, CurrentDate=?, Status=?, CategoryID=? "
                        + "WHERE BookID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, b.getBookName());
                stm.setString(2, b.getImage());
                stm.setFloat(3, b.getPrice());
                stm.setInt(4, b.getQuantity());
                stm.setString(5, b.getAuthor());
                stm.setString(6, b.getCurrentDate());
                stm.setBoolean(7, b.isStatus());
                stm.setInt(8, b.getCategoryID());
                stm.setInt(9, b.getBookID());
                return stm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public int insertBook(BookDTO b) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO tblBook values(?,?,?,?,?,?,'1',?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, b.getBookName());
                stm.setString(2, b.getImage());
                stm.setFloat(3, b.getPrice());
                stm.setInt(4, b.getQuantity());
                stm.setString(5, b.getAuthor());
                stm.setString(6, b.getCurrentDate());
                stm.setInt(7, b.getCategoryID());
                int result = stm.executeUpdate();
                return result;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public int deleteBook(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE tblBook "
                        + "SET Status = 0 "
                        + "WHERE BookID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                return stm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public ArrayList<BookDTO> getListBookByCategoryID(int ID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<BookDTO> list = new ArrayList<>();
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = null;
                if (ID == 0) {
                    sql = "SELECT BookID, BookName, image, Price, quantity, Author, CurrentDate, Status, CategoryID "
                            + "FROM tblBook "
                            + "WHERE Status = '1' ";
                } else {
                    sql = "SELECT BookID, BookName, image, Price, quantity, Author, CurrentDate, Status, CategoryID "
                            + "FROM tblBook "
                            + "WHERE Status = '1' AND CategoryID = " + ID;
                }
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new BookDTO(rs.getInt("BookID"),
                            rs.getString("BookName"),
                            rs.getString("image"),
                            rs.getFloat("Price"),
                            rs.getInt("quantity"),
                            rs.getString("Author"),
                            rs.getString("CurrentDate"),
                            rs.getBoolean("Status"),
                            rs.getInt("CategoryID")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
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
        return list;
    }

    public ArrayList<BookDTO> getListBookByName(String search) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<BookDTO> list = new ArrayList<>();
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT BookID, BookName, image, Price, quantity, Author, CurrentDate, Status, CategoryID "
                        + "FROM tblBook "
                        + "WHERE Status = '1' AND BookName like '%" + search + "%'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new BookDTO(rs.getInt("BookID"),
                            rs.getString("BookName"),
                            rs.getString("image"),
                            rs.getFloat("Price"),
                            rs.getInt("quantity"),
                            rs.getString("Author"),
                            rs.getString("CurrentDate"),
                            rs.getBoolean("Status"),
                            rs.getInt("CategoryID")));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return list;
    }

    public BookDTO getBookByBookID(int ID) throws SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BookDTO b = new BookDTO();
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT BookID, BookName, image, Price, quantity, Author, CurrentDate, Status, CategoryID "
                        + "FROM tblBook "
                        + "WHERE BookID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    b = new BookDTO(rs.getInt("BookID"),
                            rs.getString("BookName"),
                            rs.getString("image"),
                            rs.getFloat("Price"),
                            rs.getInt("quantity"),
                            rs.getString("Author"),
                            rs.getString("CurrentDate"),
                            rs.getBoolean("Status"),
                            rs.getInt("CategoryID"));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return b;
    }

    public int getQuantityOfBook(int ID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int quantity = 0;
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT quantity "
                        + "FROM tblBook "
                        + "WHERE BookID = " + ID;
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return quantity;
    }

    public int insertDetail(DetailDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO tblDetail values(?,?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setFloat(1, dto.getPrice());
                stm.setInt(2, dto.getQuantity());
                stm.setInt(3, dto.getOrderID());
                stm.setInt(4, dto.getBook().getBookID());
                return stm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public int insertOrder(OrderDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO tblOrder values(?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getDateOrder());
                stm.setFloat(2, dto.getTotal());
                stm.setInt(3, dto.getUserID());
                return stm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public int getOrderID() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 OrderID "
                        + "FROM tblOrder "
                        + "ORDER BY OrderID DESC ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("OrderID");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return -1;
    }

    public int updateQuantityOfBook(int BookID, int Bookquantity) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE tblBook "
                        + "SET quantity = ? "
                        + "WHERE BookID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, Bookquantity);
                stm.setInt(2, BookID);
                return stm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public ArrayList<OrderDTO> listOrderID(int UserID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<OrderDTO> list = new ArrayList<>();
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT OrderID, DateOrder, total "
                        + "FROM tblOrder "
                        + "WHERE UserID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, UserID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new OrderDTO(rs.getInt("OrderID"),
                            rs.getString("DateOrder"),
                            rs.getFloat("total"),
                            UserID));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return list;
    }

    public ArrayList<DetailDTO> getListDetailByOrderID(int ID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<DetailDTO> list = new ArrayList<>();
        BookDAO dao = new BookDAO();
        try {
            con = toannh.utils.DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT Price, quantity, OrderID, BookID "
                        + "FROM tblDetail "
                        + "WHERE OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    BookDTO dto = dao.getBookByBookID(rs.getInt("BookID"));
                    list.add(new DetailDTO(0, rs.getFloat("Price"), rs.getInt("quantity"), rs.getInt("OrderID"), dto));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return list;
    }
}
