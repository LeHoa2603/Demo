/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import View.Phieu_tra_view;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTable;
import Model.PhieuTraPhieuMuonJoin;
import View.Phieu_tra_view;
import Controller.ConnectDB;
import Controller.ExcelExporter;

public class Phieu_Tra {

    private Connection conn;

    public Phieu_Tra() throws SQLException {
        conn = ConnectDB.getConnection();
        createUpdateTrigger();
    }

    public void setButtonListener1(JButton button, ActionListener listener) {
        button.addActionListener(listener);
    }

   public List<PhieuTraPhieuMuonJoin> getPhieu_tra_List() {
    List<PhieuTraPhieuMuonJoin> Phieu_tra_List = new ArrayList<>();
    String query = "SELECT id_phieu_muon, bookId, readerID, so_luong, phi_muon, ngay_muon, ngay_tra FROM phieu_muon";
    try (PreparedStatement preparedStatement = conn.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
            PhieuTraPhieuMuonJoin data = new PhieuTraPhieuMuonJoin(
                    resultSet.getInt("id_phieu_tra"),
                resultSet.getInt("id_phieu_muon"),
                resultSet.getInt("bookId"),
                resultSet.getInt("readerID"),
                resultSet.getInt("so_luong"),
                resultSet.getString("phi_muon"),
                resultSet.getDate("ngay_muon"),
                resultSet.getDate("ngay_tra"),
                resultSet.getString("tinh_trang")
            );
            Phieu_tra_List.add(data);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return Phieu_tra_List;
}



   public void createUpdateTrigger() {
    try {
        // Kiểm tra xem trigger đã tồn tại hay chưa
        PreparedStatement checkStmt = conn.prepareStatement("SHOW TRIGGERS LIKE 'update_phieu_tra1'");
        ResultSet rs = checkStmt.executeQuery();
        if (!rs.next()) {
            // Trigger chưa tồn tại, tạo mới
            String sql = "DELIMITER $$ " +
                    "CREATE TRIGGER update_phieu_tra1 " +
                    "AFTER INSERT ON phieu_muon " +
                    "FOR EACH ROW " +
                    "BEGIN " +
                    "INSERT INTO phieu_tra (id_phieu_muon, bookId, readerID, so_luong, phi_muon, ngay_muon, ngay_tra, tinh_trang) " +
                    "VALUES (NEW.id_phieu_muon, NEW.bookId, NEW.readerID, NEW.so_luong, NEW.phi_muon, NEW.ngay_muon, NEW.ngay_tra, 'Đang Mượn'); " +
                    "END $$ " +
                    "DELIMITER ;";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.execute();
                System.out.println("Trigger created successfully");
            }
        } else {
            System.out.println("Trigger already exists");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}




    public List<PhieuTraPhieuMuonJoin> getPhieu_muon_List() {
        List<PhieuTraPhieuMuonJoin> Phieu_muon_List = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT pt.id_phieu_tra,pm.id_phieu_muon, pm.bookId, pm.readerID, pm.so_luong, pm.phi_muon, pm.ngay_muon, pm.ngay_tra, pt.tinh_trang "
                + "FROM phieu_muon pm INNER JOIN phieu_tra pt ON pm.id_phieu_muon = pt.id_phieu_muon"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PhieuTraPhieuMuonJoin joinedData = new PhieuTraPhieuMuonJoin(resultSet.getInt("id_phieu_tra"),
                        resultSet.getInt("id_phieu_muon"), resultSet.getInt("bookId"), resultSet.getInt("readerID"),
                        resultSet.getInt("so_luong"), resultSet.getString("phi_muon"), resultSet.getDate("ngay_muon"),
                        resultSet.getDate("ngay_tra"), resultSet.getString("tinh_trang"));
                Phieu_muon_List.add(joinedData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Phieu_muon_List;
    }
    


    public void updateTinhTrangDaTra(int id_phieu_tra) {
        String sql = "UPDATE phieu_tra SET tinh_trang = 'Đã Trả' WHERE id_phieu_tra = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_phieu_tra);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cập nhật tình trạng của phiếu trả thành Đã Trả thành công.");
            } else {
                System.out.println("Không tìm thấy phiếu trả có id_phieu_tra = " + id_phieu_tra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id_phieu_tra) {
        PreparedStatement deletePhieuTraStmt = null;
        try {
            if (conn != null) {
                // Xóa phiếu trả từ bảng phieu_tra
                String deletePhieuTraSQL = "DELETE FROM phieu_tra WHERE id_phieu_tra = ?";
                deletePhieuTraStmt = conn.prepareStatement(deletePhieuTraSQL);
                deletePhieuTraStmt.setInt(1, id_phieu_tra);
                deletePhieuTraStmt.executeUpdate();

                System.out.println("Xóa phiếu trả thành công từ bảng phieu_tra.");
            } else {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (deletePhieuTraStmt != null) {
                    deletePhieuTraStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<PhieuTraPhieuMuonJoin> findBookBy(String sql, String a) throws SQLException {
        List<PhieuTraPhieuMuonJoin> Phieu_tra_List = new ArrayList<>();
        conn = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            // Thiết lập giá trị của tham số
            stmt.setString(1, "%" + a + "%");
            // Thực thi câu lệnh SQL
            rs = stmt.executeQuery();
            // Lặp qua các kết quả trả về
            while (rs.next()) {
                // Tạo đối tượng PhieuTraPhieuMuonJoin từ kết quả của truy vấn
                PhieuTraPhieuMuonJoin data = new PhieuTraPhieuMuonJoin(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(6), rs.getDate(7), rs.getString(8));
                // Thêm đối tượng vào danh sách kết quả
                Phieu_tra_List.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Đóng các tài nguyên
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // Trả về danh sách kết quả
        return Phieu_tra_List;
    }

    
//
    // Phương thức xử lý sự kiện cho nút trả
    public void handleTraButton(int id_phieu_tra) {
        // Gọi phương thức updateTinhTrangDaTra() để cập nhật trạng thái của phiếu trả
        updateTinhTrangDaTra(id_phieu_tra);
    }
//
//    public static void main(String[] args) {
//        // Tạo đối tượng BookView
//        Phieu_tra_view phieuTraView = new Phieu_tra_view();
//        phieuTraView.setVisible(true);
//    }
}
