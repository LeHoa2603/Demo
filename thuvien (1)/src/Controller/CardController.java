/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CardModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Hoa
 */
public class CardController {

    private Connection conn;
    private List<CardModel> cardList;

    public CardController() {
    }

    public void setButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);

    }

    public List<CardModel> getCardList() {
        List<CardModel> cardList = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement("SELECT *FROM qlythe"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                CardModel card = new CardModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getDate(4));
                cardList.add(card);

            }
        } catch (SQLException e) {
        }
        return cardList;
    }

    public void AddCard(CardModel card) {
        // Kiểm tra trước khi thêm vào cơ sở dữ liệu
        if (isCardExists(card.getID()))  
            {
            System.out.println("Mã thẻ đã tồn tại!");

            return;
        }
        if (isReaderExists(card.getIDDocGia()))
            {
            System.out.println("Mã độc giả đã tồn tại!");

            return;
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "INSERT INTO qlythe(ID,IDDocGia,NgayDangKy,NgayHetHan)" + "VALUES(?,?,?,?)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, card.getID());
            stmt.setInt(2, card.getIDDocGia());

            stmt.setDate(3, card.getNgayDangKy());
            stmt.setDate(4, card.getNgayHetHan());
            // Thực thi câu lệnh SQL để thêm dữ liệu vào cơ sở dữ liệu
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Thêm thẻ thành công!");
            } else {
                System.out.println("Thêm thẻ thất bại!");
            }
        } catch (Exception e) {
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

    //kiểm tra mã tồn tại hay chưa
//    public boolean isCardExists(int ID, int selectedReaderID) {
//        for (CardModel card : cardList) {
//            // Kiểm tra xem ID của thẻ và ID của độc giả có trùng khớp không
//            if (card.getID() == ID && card.getIDDocGia() == selectedReaderID) {
//                return true; // Thẻ đã tồn tại
//            }
//        }
//        return false; // Thẻ không tồn tại
//    }
    public boolean isCardExists(int ID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean exists = false;
        try {
            conn = ConnectDB.getConnection();
            String sql = "SELECT COUNT(*) FROM qlythe WHERE ID = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
//        stmt.setInt(2,  selectedReaderID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                exists = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return exists;
    }

    public boolean isReaderExists(int selectedReaderID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean exists = false;
        try {
            conn = ConnectDB.getConnection();
            String sql = "SELECT COUNT(*) FROM qlythe WHERE  IDDocGia = ?";
            stmt = conn.prepareStatement(sql);
//        stmt.setInt(1, ID);
            stmt.setInt(1, selectedReaderID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                exists = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return exists;
    }

    public void UpdateCard(CardModel card) throws SQLException {
        conn = ConnectDB.getConnection();
        String sql = "UPDATE qlythe SET IDDocGia = ?,NgayDangKy=?,NgayHetHan=? WHERE ID=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, card.getIDDocGia());
            stmt.setDate(2, card.getNgayDangKy());
            stmt.setDate(3, card.getNgayHetHan());
            stmt.setInt(4, card.getID());
            stmt.executeUpdate();
            int rowsUpdated = stmt.executeUpdate();
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

    public void DeleteCard(int ID) throws SQLException {
        conn = ConnectDB.getConnection();
        String sql = "DELETE FROM qlythe WHERE ID = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CardModel> FindCard(String sql, String a) throws SQLException {
        List<CardModel> cardList = new ArrayList<>();
        conn = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + a + "%");
            stmt.execute();
            rs = stmt.executeQuery();
            while (rs.next()) {
                CardModel card = new CardModel(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4));
                cardList.add(card);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardList;

    }

//    boolean isCardExists(int readerId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
