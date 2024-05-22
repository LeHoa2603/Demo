/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package  controller;
import java.awt.event.ActionListener;
  import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import Model.Book;
import View.BookView;
import Controller.ConnectDB;


/**
 *
 * @author Windows
 */
public class BTLNOP {
private Connection conn;

    public BTLNOP() {
    }

    public void setButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);
        
    }
   
   public List<Book> getBookList() {
	List<Book> bookList = new ArrayList<>();
        try (   
                Connection conn = ConnectDB.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT *FROM themsach");//thực hiện truyvan
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Book book=new Book(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),
                      resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8));
              bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  bookList;
	}
   
   public void addBook(Book book) {
    Connection conn = null;
    PreparedStatement stmt = null;//khởi tạo dối tượng để thực thi truy vấn
    
    try {
        conn = ConnectDB.getConnection();
        String sql = "INSERT INTO themsach(bookName,language,price,quantity,category,publisher,year)"
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, book.getBookName());
        stmt.setString(2, book.getLanguage());
        stmt.setInt(3, book.getPrice());
        stmt.setInt(4, book.getQuantity());
        stmt.setString(5, book.getCategory());
        stmt.setString(6, book.getPublisher());
        stmt.setString(7, book.getYear());
        
        // Thực thi câu lệnh SQL để thêm dữ liệu vào cơ sở dữ liệu
        int rowsAffected = stmt.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Thêm sách thành công!");
        } else {
            System.out.println("Thêm sách thất bại!");
        }
    } catch(Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
   public void deleteBook(int bookId) throws SQLException{
       conn=ConnectDB.getConnection();
      String sql="DELETE FROM themsach WHERE bookId = ?";
      PreparedStatement stmt=null;
      try{
          stmt=conn.prepareStatement(sql);
          stmt.setInt(1,bookId);
          stmt.execute();
      }catch(Exception e){
          e.printStackTrace();
      }
   }
   public void updateBook(Book book) throws SQLException{
      conn = ConnectDB.getConnection();
     String sql = "UPDATE themsach SET bookName=?, language=?, price=?, quantity=?, category=?, publisher=?, `year`=? WHERE bookId=?";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, book.getBookName());
        stmt.setString(2, book.getLanguage());
        stmt.setInt(3, book.getPrice());
        stmt.setInt(4, book.getQuantity());
        stmt.setString(5, book.getCategory());
        stmt.setString(6, book.getPublisher());
        stmt.setString(7, book.getYear());
        stmt.setInt(8, book.getBookId()); // Chú ý thứ tự của các tham số
        stmt.executeUpdate();
       int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Update thành công!");
        } else {
            System.out.println("Update thất bại!");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close the connection
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       }
   }
  
//  
   
//   public static void main(String[] args) {
//        // Tạo đối tượng BookView
//    BookView bookView = new BookView();
//    
//    
//        
//       
//    }

//    public void updateBook(String bookName, String language, int price, int quantity, String category, String publisher, String year) {
//        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
}
