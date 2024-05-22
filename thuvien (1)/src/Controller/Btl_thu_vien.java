/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;
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
import javax.swing.table.DefaultTableModel;
import Model.phieu_muon;
import View.Phieu_muon_view;
import java.util.Date;
import javax.swing.JTable;
//import XuatExcel.ExcelExporter;
import Controller.ConnectDB;
import Controller.DAO;
import Controller.ExcelExporter;


/**
 *
 * @author Windows
 */
public class Btl_thu_vien {
private Connection conn;


    public Btl_thu_vien() {
    }

    public void setButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);
        
    }
   
   public List<phieu_muon> getPhieu_muon_List() {
	List<phieu_muon> Phieu_muon_List = new ArrayList<>();
        try (   
                Connection conn = ConnectDB.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT *FROM phieu_muon");//thực hiện truyvan
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                
                phieu_muon book=new phieu_muon(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),
                      resultSet.getDate(6),resultSet.getDate(7));
              Phieu_muon_List.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  Phieu_muon_List;
	}
   
  public void addBook(phieu_muon book) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = ConnectDB.getConnection();
        String sql = "INSERT INTO phieu_muon (bookId,readerID,so_luong,phi_muon,ngay_muon,ngay_tra) VALUES (?, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, book.getBookId());
        stmt.setInt(2, book.getReaderID());
        stmt.setInt(3, book.getSo_luong());
        stmt.setString(4, book.getPhi_muon());
        stmt.setDate(5, new java.sql.Date(book.getNgay_muon().getTime()));
        stmt.setDate(6, new java.sql.Date(book.getNgay_tra().getTime()));

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Thêm sách thành công!");
        } else {
            System.out.println("Thêm sách thất bại!");
        }
    } catch (SQLException e) {
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

   public void updatePhieuTraWithNull(int id_phieu_muon) throws SQLException {
    conn = ConnectDB.getConnection();
    String sql =  "UPDATE phieu_tra SET bookId=NULL, readerID=NULL, so_luong=NULL, phi_muon=NULL, ngay_muon=NULL, ngay_tra=NULL WHERE id_phieu_muon=?";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id_phieu_muon);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

 public void deleteBook(int id_phieu_muon) throws SQLException {
    conn = ConnectDB.getConnection();
    try {
        // Tạm thời tắt ràng buộc khóa ngoại
        conn.createStatement().execute("SET FOREIGN_KEY_CHECKS=0");
        
        String deletePhieuMuonSQL = "DELETE FROM phieu_muon WHERE id_phieu_muon = ?";
        PreparedStatement deletePhieuMuonStmt = conn.prepareStatement(deletePhieuMuonSQL);
        deletePhieuMuonStmt.setInt(1, id_phieu_muon);
        deletePhieuMuonStmt.execute();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            // Bật lại ràng buộc khóa ngoại
            conn.createStatement().execute("SET FOREIGN_KEY_CHECKS=1");
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}




   
   
  public void updateBook(phieu_muon book) throws SQLException {
    conn = ConnectDB.getConnection();
    String sql = "UPDATE phieu_muon SET bookId=?, readerID=?, so_luong=?, phi_muon=?, ngay_muon=?, ngay_tra=? WHERE id_phieu_muon=?";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, book.getBookId());
        stmt.setInt(2, book.getReaderID());
        stmt.setInt(3, book.getSo_luong());
        stmt.setString(4, book.getPhi_muon());
        
        // Chuyển đổi từ java.util.Date sang java.sql.Date
        java.util.Date utilDateStart = book.getNgay_muon();
        java.sql.Date sqlDateStart = new java.sql.Date(utilDateStart.getTime());
        stmt.setDate(5, sqlDateStart);
        
        java.util.Date utilDateLate = book.getNgay_tra();
        java.sql.Date sqlDateLate = new java.sql.Date(utilDateLate.getTime());
        stmt.setDate(6, sqlDateLate);

        stmt.setInt(7, book.getId_phieu_muon()); // Set the id_phieu_muon for WHERE clause

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Update thành công!");

            // Cập nhật bảng phieu_tra tương ứng
            updatePhieuTra(book.getId_phieu_muon(),book.getBookId(),book.getReaderID(),book.getSo_luong(),book.getPhi_muon(), book.getNgay_muon(), book.getNgay_tra());

        } else {
            System.out.println("Bạn chưa sửa gì!");
        }
    } catch (SQLException e) {
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

public void updatePhieuTra(int id_phieu_muon, int bookId, int readerID, int so_luong, String phi_muon, Date ngay_muon, Date ngay_tra) throws SQLException {
    conn = ConnectDB.getConnection();
    String sql = "UPDATE phieu_tra SET bookId=?, readerID=?, so_luong=?, phi_muon=?, ngay_muon=?, ngay_tra=? WHERE id_phieu_muon=?";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id_phieu_muon);
        stmt.setInt(2, bookId);
        stmt.setInt(3, readerID);
        stmt.setInt(4, so_luong);
        stmt.setString(5, phi_muon);
        java.sql.Date sqlDateStart = new java.sql.Date(ngay_muon.getTime());
        stmt.setDate(6, sqlDateStart);
        java.sql.Date sqlDateLate = new java.sql.Date(ngay_tra.getTime());
        stmt.setDate(7, sqlDateLate);
        

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Cập nhật phieu_tra thành công!");
        } else {
            System.out.println("Không thể cập nhật phieu_tra!");
        }
    } catch (SQLException e) {
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



   public List<phieu_muon> findBookBy(String sql, String a) throws SQLException
	{
		List<phieu_muon> Phieu_muon_List = new ArrayList<>();
		conn = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+a+"%");
			stmt.execute();
			rs = stmt.executeQuery();
			while(rs.next())
			{
//				
				phieu_muon book = new phieu_muon(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),
                      rs.getDate(6),rs.getDate(7));
				Phieu_muon_List.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
    return Phieu_muon_List;
        }
 

//   public static void main(String[] args) {
//        // Tạo đối tượng BookView
//    Phieu_muon_view bookView = new Phieu_muon_view();
//    
//    // Để sử dụng countRows(), bạn cần có một danh sách phù hợp hoặc DefaultTableModel
//        List<phieu_muon> listPhieumuon = new ArrayList<>();
//        
        
       
//    }

//    public void updateBook(String bookName, String language, int price, int quantity, String category, String publisher, String year) {
//        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    
    
}

