/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.CardModel;
import java.awt.event.ActionListener;
  import java.util.List;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.Vector;
import javax.swing.JButton;
//import javax.swing.table.DefaultTableModel;
import Model.ReaderModel;
import View.View;

/**
 *
 * @author admin
 */
public class ReaderController {
//    private ConnectDB conn;

   /* public ReaderController() {
    }
*/
    public void setButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);
        
    }
   
   public List<ReaderModel> getReaderList() {
    List<ReaderModel> readerList = new ArrayList<>();
    try (
        Connection connection = ConnectDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM docgia2");
        ResultSet resultSet = preparedStatement.executeQuery()
    ) {
        while (resultSet.next()) {
            ReaderModel readerModel = new ReaderModel(
                resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                      resultSet.getString(5),resultSet.getInt(6));
            readerList.add(readerModel);
        }
    } catch (SQLException e) {
        // Handle exception gracefully, log it, or rethrow a custom exception
        e.printStackTrace();
    }
    return readerList;
}

   
  public void addReader(ReaderModel readerModel) {
    // Kiểm tra trước khi thêm vào cơ sở dữ liệu
    if (isReaderIdExists(readerModel.getReaderid())) {
        System.out.println("Mã độc giả đã tồn tại!");
        return;
    }

    Connection conn = null;
    PreparedStatement stmt = null;
    
    try {
        conn = ConnectDB.getConnection();
        String sql = "INSERT INTO docgia2(readerId,readerName,lop,gender,email,phone)"
                   + "VALUES (?,?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, readerModel.getReaderid());
        stmt.setString(2, readerModel.getReadername());
        stmt.setString(3, readerModel.getLop());
        stmt.setString(4, readerModel.getGender());
        stmt.setString(5, readerModel.getEmail());
        stmt.setInt(6, readerModel.getPhone());
        
        // Thực thi câu lệnh SQL để thêm dữ liệu vào cơ sở dữ liệu
        int rowsAffected = stmt.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Thêm độc giả thành công!");
        } else {
            System.out.println("Thêm độc giả thất bại!");
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

// Phương thức để kiểm tra xem readerId đã tồn tại hay chưa
public boolean isReaderIdExists(int readerId) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    boolean exists = false;

    try {
        conn = ConnectDB.getConnection();
        String sql = "SELECT COUNT(*) FROM docgia2 WHERE readerId = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, readerId);
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

   public void deleteReader(int readerId) throws SQLException{
       Connection conn =null;
       conn = ConnectDB.getConnection();
      String sql="DELETE FROM docgia2 WHERE readerId = ?";
      PreparedStatement stmt=null;
      try{
          stmt=conn.prepareStatement(sql);
          stmt.setInt(1,readerId);
          stmt.execute();
      }catch(Exception e){
          e.printStackTrace();
      }
   }
   public void updateReader(ReaderModel readerModel) throws SQLException {
    Connection conn = null;
    try {
        conn = ConnectDB.getConnection();
        String sql = "UPDATE docgia2 SET  readerName=?, lop=?, gender=?, email=?, phone=? WHERE readerId=?";
        System.out.println("sssss: " + sql);
        PreparedStatement stmt = conn.prepareStatement(sql);
//        stmt.setInt(1, readerModel.getReaderid());
        stmt.setString(1, readerModel.getReadername());
        stmt.setString(2, readerModel.getLop());
        stmt.setString(3, readerModel.getGender());
        stmt.setString(4, readerModel.getEmail());
        stmt.setInt(    5, readerModel.getPhone());
        stmt.setInt(6, readerModel.getReaderid()); // Thiết lập tham số readerId cho điều kiện WHERE

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Update thành công!");
        } else {
            System.out.println("Không có bản ghi nào được cập nhật!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng kết nối
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}



   public List<ReaderModel> findReaderBy(String sql, String a) throws SQLException
	{
                Connection conn=null;
		List<ReaderModel> readerList = new ArrayList<>();
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
                            ReaderModel readerModel = new ReaderModel(rs.getInt(1),rs.getString(2),rs.getString(3),
                                        rs.getString(4),rs.getString(5),rs.getInt(6));
                            readerList.add(readerModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
    return readerList;
        }
   
        public List<ReaderModel> sortAZPageNo(String sql, boolean ascending) throws SQLException {
            Connection conn =null;
    List<ReaderModel> readerList = new ArrayList<>();
    conn = ConnectDB.getConnection();
    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            ReaderModel readerModel = new ReaderModel(rs.getInt(1),rs.getString(2),rs.getString(3),
                                        rs.getString(4),rs.getString(5),rs.getInt(6));
            readerList.add(readerModel);
        }
        
        // Sắp xếp danh sách độc giả theo tên
//        if (ascending) {
//            readerList.sort(Comparator.comparing(ReaderModel::getReaderName()));
//        } else {
//            readerList.sort(Comparator.comparing(ReaderModel::getReaderName).reversed());
//        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return readerList;
}
   
//   public static void main(String[] args) {
//    View readerView = new View();  
//    }
//        public List<Integer> getReaderIds() {
//        List<Integer> readerIds = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            conn = ConnectDB.getConnection();
//            String sql = "SELECT readerId FROM docgia2";
//            stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                readerIds.add(rs.getInt("readerId"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return readerIds;
//    }

    // Phương thức để đồng bộ dữ liệu từ bảng quản lý độc giả sang bảng quản lý thẻ
//    public void syncCardData() {
//        List<Integer> readerIds = getReaderIds();
//        CardController cardController = new CardController();
//        for (int readerId : readerIds) {
//            // Kiểm tra nếu mã độc giả đã tồn tại trong bảng quản lý thẻ thì bỏ qua
//            if (!cardController.isCardExists(readerId)) {
//                // Tạo một bản ghi thẻ mới với mã độc giả từ bảng quản lý độc giả
//                CardModel cardModel = new CardModel();
//                cardModel.setID(readerId); // Mã độc giả
//                // Cài đặt các giá trị khác của thẻ nếu cần
//                // Ví dụ: cardModel.setNgayDangKy(...);
//                // Thêm thẻ mới vào bảng quản lý thẻ
//                cardController.AddCard(cardModel);
//            }
//        }
//    }

    public List<View> getView() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
