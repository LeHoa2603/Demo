/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import controller.Btl_thu_vien;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import Model.phieu_muon;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import XuatExcel.ExcelExporter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows
 */
public class Phieu_muon_view extends JFrame implements ActionListener {

    private JButton AddBtn, EditBtn, DeleteBtn,BackBtn, SaveBtn, SearchBtn, Xuat_excel,TangBtn,GiamBtn;
    private JScrollPane jscrollPaneTable;
    private JTable Table;
    private JComboBox Phi_muoncbb;
    private JTextField SearchBooktf,bookIdtf,readerIDtf, So_luongtf, ngay_muontf, ngay_tratf ;
    private JLabel bookIdlbl,readerIDlbl, So_luonglbl, Phi_muonlbl, ngay_muonlbl, ngay_tralbl ;
    private DefaultTableModel tblModel;
    private final String[] columnNames = new String[]{"ID Phiếu Mượn", "Mã Sách", "Mã Độc Giả", "Số Lượng", "Phí Mượn/1 quyến sách", "Ngày Trả", "Ngày Mượn"};
    private final Object data = new Object[][]{};
    private List<phieu_muon> listPhieumuon = new ArrayList<>();

    int id_phieu_muon;

    public Phieu_muon_view() {
        initComponents();
        addInputRestrictions();
        
    }
    private void addInputRestrictions() {
    // Bắt sự kiện khi người dùng nhập vào ô bookId
    getBookIdtf().addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c)) { // Kiểm tra xem ký tự nhập vào có phải là số không
                e.consume(); // Nếu không phải số, hủy sự kiện nhập
            }
        }
    });

    // Bắt sự kiện khi người dùng nhập vào ô readerID
    getReaderIDtf().addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c)) { // Kiểm tra xem ký tự nhập vào có phải là số không
                e.consume(); // Nếu không phải số, hủy sự kiện nhập
            }
        }
    });

    // Bắt sự kiện khi người dùng nhập vào ô So_luong
    getSo_luongtf().addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c)) { // Kiểm tra xem ký tự nhập vào có phải là số không
                e.consume(); // Nếu không phải số, hủy sự kiện nhập
            }
        }
    });

    // Bắt sự kiện khi người dùng nhập vào ô ngay_muon và ngay_tra
    getNgay_muontf().addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!(Character.isDigit(c) || c == '-')) { // Kiểm tra xem ký tự nhập vào có phải là số hoặc dấu /
                e.consume(); // Nếu không phải số hoặc dấu /, hủy sự kiện nhập
            }
        }
    });

    getNgay_tratf().addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!(Character.isDigit(c) || c == '-')) { // Kiểm tra xem ký tự nhập vào có phải là số hoặc dấu /
                e.consume(); // Nếu không phải số hoặc dấu /, hủy sự kiện nhập
            }
        }
    });
}

    private void initComponents() {
        jscrollPaneTable = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        bookIdlbl = new JLabel("Mã Sách");
        readerIDlbl = new JLabel("Mã độc giả");
        So_luonglbl = new JLabel("Số lượng");
        Phi_muonlbl = new JLabel("Phí mượn");
        ngay_muonlbl = new JLabel("Ngày mượn");
        ngay_tralbl = new JLabel("Ngày trả");
        

        bookIdtf = new JTextField(10);
        SearchBooktf = new JTextField(15);
        readerIDtf = new JTextField(10);
        So_luongtf = new JTextField(10);
        ngay_muontf = new JTextField(20);
        ngay_tratf = new JTextField(20);
        
        Phi_muoncbb = new JComboBox<>(new String[]{"5000", "10000", "20000"});
        
        AddBtn = new JButton("Thêm");
        EditBtn = new JButton("Sửa");
        DeleteBtn = new JButton("Xoá");
        SaveBtn = new JButton("Lưu");
        SearchBtn = new JButton("Tìm kiếm");
        Xuat_excel = new JButton("Xuất File");
        TangBtn = new JButton("Tăng");
        GiamBtn = new JButton("Giảm");
        BackBtn=new JButton("Quay lại");
        
        jscrollPaneTable = new JScrollPane();
        Table = new JTable();
        Table.getTableHeader().setReorderingAllowed(false);
        Table.setDefaultEditor(Object.class, null);// Không cho phép chỉnh sửa trực tiếp trên các ô trong bảng
        tblModel = new DefaultTableModel((Object[][]) data, columnNames);
        Table.setModel(tblModel);
        jscrollPaneTable.setViewportView(Table);
        jscrollPaneTable.setPreferredSize(new Dimension(1247, 300));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(jscrollPaneTable);

        panel.add(bookIdlbl);
        panel.add(readerIDlbl);
        panel.add(So_luonglbl);
        panel.add(Phi_muonlbl);
        panel.add(ngay_muonlbl);
        panel.add(ngay_tralbl);
        

        panel.add(bookIdtf);
        panel.add(SearchBooktf);
        panel.add(readerIDtf);
        panel.add(So_luongtf);
        panel.add(ngay_muontf);
        panel.add(ngay_tratf);
        panel.add(Phi_muoncbb);
       

        panel.add(AddBtn);
        panel.add(EditBtn);
        panel.add(DeleteBtn);
        panel.add(SaveBtn);
        panel.add(SearchBooktf);
        panel.add(SearchBtn);
        panel.add(TangBtn);
       panel.add(GiamBtn);
        panel.add(Phi_muoncbb);
        panel.add(Xuat_excel);
        panel.add(BackBtn);
        
         layout.putConstraint(SpringLayout.WEST, TangBtn, 50, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TangBtn, 20, SpringLayout.NORTH, panel);
        
         layout.putConstraint(SpringLayout.WEST, GiamBtn, 80, SpringLayout.WEST, TangBtn);
        layout.putConstraint(SpringLayout.NORTH, GiamBtn, 20, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, SearchBooktf, 250, SpringLayout.WEST, GiamBtn);
        layout.putConstraint(SpringLayout.NORTH, SearchBooktf, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SearchBtn, 150, SpringLayout.WEST, SearchBooktf);
        layout.putConstraint(SpringLayout.NORTH, SearchBtn, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jscrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jscrollPaneTable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, jscrollPaneTable, -19, SpringLayout.EAST, panel);
        //cot1

        layout.putConstraint(SpringLayout.WEST, bookIdlbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, bookIdlbl, 20, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, bookIdtf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, bookIdtf, 20, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, readerIDlbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, readerIDlbl, 60, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, readerIDtf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, readerIDtf, 60, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, So_luonglbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, So_luonglbl, 100, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, So_luongtf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, So_luongtf, 100, SpringLayout.SOUTH, jscrollPaneTable);
        //cot2

        layout.putConstraint(SpringLayout.WEST, Phi_muonlbl, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Phi_muonlbl, 20, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Phi_muoncbb, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Phi_muoncbb, 20, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, ngay_muonlbl, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngay_muonlbl, 60, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ngay_muontf, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngay_muontf, 60, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, ngay_tralbl, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngay_tralbl, 100, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ngay_tratf, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngay_tratf, 100, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.EAST, AddBtn, -32, SpringLayout.WEST, EditBtn);
        layout.putConstraint(SpringLayout.NORTH, AddBtn, 25, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.EAST, EditBtn, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, EditBtn, 25, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.EAST, SaveBtn, -32, SpringLayout.WEST, DeleteBtn);
        layout.putConstraint(SpringLayout.NORTH, SaveBtn, 85, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.EAST, DeleteBtn, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, DeleteBtn, 85, SpringLayout.SOUTH, jscrollPaneTable);
        
        layout.putConstraint(SpringLayout.EAST, Xuat_excel,-90, SpringLayout.EAST, EditBtn);
        layout.putConstraint(SpringLayout.NORTH, Xuat_excel, 150, SpringLayout.SOUTH, jscrollPaneTable);
            
        layout.putConstraint(SpringLayout.EAST, BackBtn, -20, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH,BackBtn, 150, SpringLayout.SOUTH, jscrollPaneTable);

        DefaultTableModel model = (DefaultTableModel) ((JTable) jscrollPaneTable.getViewport().getView()).getModel();
        Btl_thu_vien controller = new Btl_thu_vien();
        listPhieumuon = controller.getPhieu_muon_List();
        this.Phieu_muon_List(listPhieumuon);

        AddBtn.addActionListener(this);
        EditBtn.addActionListener(this);
        SaveBtn.addActionListener(this);
        DeleteBtn.addActionListener(this);
        Xuat_excel.addActionListener(this);
        TangBtn.addActionListener(this);
        GiamBtn.addActionListener(this);
        
        
       getBookIdtf().addKeyListener(new KeyAdapter() {
    
});
       
       
       controller.setButtonListener(AddBtn, new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
        // Kiểm tra xem người dùng đã nhập đầy đủ thông tin hay chưa
        if (getBookIdtf().getText().isEmpty() || getReaderIDtf().getText().isEmpty() ||
            getSo_luongtf().getText().isEmpty() || getNgay_muontf().getText().isEmpty() ||
            getNgay_tratf().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; // Dừng việc thực hiện hành động nếu có trường không được nhập
        }
        
        // Kiểm tra định dạng ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strngay_muon = getNgay_muontf().getText();
        String strngay_tra = getNgay_tratf().getText();
        
        try {
            // Kiểm tra định dạng ngày tháng mượn
            sdf.setLenient(false); // Set strict parsing mode
            java.util.Date dateStart = sdf.parse(strngay_muon);
            
            // Kiểm tra định dạng ngày tháng trả
            sdf.setLenient(false); // Set strict parsing mode
            java.util.Date dateLate = sdf.parse(strngay_tra);
            
            // Tiếp tục thực hiện hành động khi đã nhập đúng định dạng ngày tháng
            int bookId = Integer.parseInt(getBookIdtf().getText());
            int readerID = Integer.parseInt(getReaderIDtf().getText());
            int So_luong = Integer.parseInt(getSo_luongtf().getText());
            String Phi_muon = (String) getPhi_muoncbb().getSelectedItem().toString();
            
            // Chuyển đổi java.util.Date thành java.sql.Date
            java.sql.Date sqlDateStart = new java.sql.Date(dateStart.getTime());
            java.sql.Date sqlDateLate = new java.sql.Date(dateLate.getTime());
           
            // Tạo một đối tượng Book
            phieu_muon book = new phieu_muon(0, bookId, readerID, So_luong,Phi_muon, sqlDateLate, sqlDateStart);
            controller.addBook(book);
            JOptionPane.showMessageDialog(null, "Phiếu mượn đã được thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            
            // Xóa các trường nhập liệu sau khi thêm thành công
            getBookIdtf().setText("");
            getReaderIDtf().setText("");
            getSo_luongtf().setText("0");
            getNgay_muontf().setText("");
            getNgay_tratf().setText("");
            
            // Cập nhật danh sách phiếu mượn sau khi thêm
            listPhieumuon = controller.getPhieu_muon_List();
            Phieu_muon_List(listPhieumuon);
        } catch (ParseException ex) {
            // Xử lý khi người dùng nhập sai định dạng ngày tháng
            JOptionPane.showMessageDialog(null, "Bạn đã nhập sai định dạng ngày tháng. Hãy nhập lại theo định dạng yyyy-MM-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            // Xử lý khi người dùng nhập sai định dạng số
            JOptionPane.showMessageDialog(null, "Bạn đã nhập sai định dạng số. Hãy nhập lại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Xử lý các lỗi khác có thể xảy ra
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm phiếu mượn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        
    }
});
       BackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

       controller.setButtonListener(DeleteBtn, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = Table.getSelectedRow();
        if (selectedRow == -1) {
            // Nếu không có hàng nào được chọn, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu mượn để xóa", "Lỗi Xóa", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id_phieu_muon = (int) Table.getValueAt(selectedRow, 0);
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu mượn này không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                // Cập nhật các giá trị tham chiếu trong bảng phieu_tra thành NULL
                controller.updatePhieuTraWithNull(id_phieu_muon);
            } catch (SQLException ex) {
                Logger.getLogger(Phieu_muon_view.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                // Tiến hành xóa phiếu mượn từ bảng phieu_muon
                controller.deleteBook(id_phieu_muon);
            } catch (SQLException ex) {
                Logger.getLogger(Phieu_muon_view.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Cập nhật lại danh sách phiếu mượn sau khi xóa
            listPhieumuon = controller.getPhieu_muon_List();
            
            // Hiển thị lại danh sách phiếu mượn trên view
            Phieu_muon_List(listPhieumuon);
        }
    }
});


        
        
        controller.setButtonListener(Xuat_excel, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 // Lấy số lượng cột từ bảng gốc
        int columnCount = tblModel.getColumnCount();
        // Tạo bảng mới để chứa dữ liệu đã lọc
        DefaultTableModel filteredModel = new DefaultTableModel();
        // Thêm các cột vào bảng mới
        for (int i = 0; i < columnCount; i++) {
            filteredModel.addColumn(tblModel.getColumnName(i));
        }
        // Lấy số lượng dòng đã lọc
        int rowCount = tblModel.getRowCount();
        // Lặp qua các dòng đã lọc
        for (int i = 0; i < rowCount; i++) {
            // Nếu dòng đó được hiển thị (không bị ẩn do bộ lọc)
            if (Table.convertRowIndexToModel(i) != -1) {
                // Lấy dòng dữ liệu từ bảng gốc
                Object[] rowData = new Object[columnCount];
                for (int j = 0; j < columnCount; j++) {
                    rowData[j] = Table.getValueAt(i, j);
                }
                // Thêm dòng dữ liệu vào bảng mới
                filteredModel.addRow(rowData);
            }
        }
        // Xuất Excel với dữ liệu đã lọc
        ExcelExporter.exportToExcel(filteredModel, "C:\\Xuat_Excel\\phieu_muon.xlsx");
            }
        });
        

       // Bắt sự kiện khi nhấn nút Sửa
   // Trong phần EditBtn:
// Trong phần EditBtn:
controller.setButtonListener(EditBtn, new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        // Kiểm tra xem có hàng nào được chọn không
        int selectedRow = Table.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy dữ liệu từ hàng được chọn
            int id_phieu_muon = (int) Table.getValueAt(selectedRow, 0);
            int bookId = (int) Table.getValueAt(selectedRow, 1);
            int readerID = (int) Table.getValueAt(selectedRow, 2);
            int So_luong = (int) Table.getValueAt(selectedRow, 3);
            String Phi_muon = (String) Table.getValueAt(selectedRow, 4);
            String ngay_traStr = (String) Table.getValueAt(selectedRow, 5);
            String ngay_muonStr = (String) Table.getValueAt(selectedRow, 6);
            
            // Chuyển đổi chuỗi ngày thành kiểu java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date ngay_tra_util = sdf.parse(ngay_traStr);
                java.util.Date ngay_muon_util = sdf.parse(ngay_muonStr);
                Date ngay_tra = new Date(ngay_tra_util.getTime());
                Date ngay_muon = new Date(ngay_muon_util.getTime());
                
                // Hiển thị dữ liệu lên các ô
                bookIdtf.setText(String.valueOf(bookId));
                readerIDtf.setText(String.valueOf(readerID));
                So_luongtf.setText(String.valueOf(So_luong));
                Phi_muoncbb.setSelectedItem(Phi_muon);
                ngay_muontf.setText(ngay_muon.toString());
                ngay_tratf.setText(ngay_tra.toString());

                // Lưu lại ID của phiếu mượn để sử dụng khi lưu thay đổi
                id_phieu_muon = id_phieu_muon;
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi chuyển đổi ngày", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu mượn để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
});


controller.setButtonListener(SaveBtn, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Kiểm tra xem người dùng đã nhập đầy đủ thông tin hay chưa
        String bookIdstr = bookIdtf.getText();
        String date_lateStr = ngay_muontf.getText();
        String date_startStr = ngay_tratf.getText();
        String readerIDstr = readerIDtf.getText();
        String So_luongstr = So_luongtf.getText();
        String Phi_muonstr = (String) getPhi_muoncbb().getSelectedItem();

        if (bookIdstr.isEmpty() || date_lateStr.isEmpty() || date_startStr.isEmpty() || readerIDstr.isEmpty()
            || So_luongstr.isEmpty() || Phi_muonstr.isEmpty()) {
            // Nếu có một trường nào đó chưa được điền, hiển thị thông báo và không thực hiện lưu
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin cho phiếu mượn.");
            return;
        }

        try {
            // Chuyển đổi dữ liệu về số nguyên
            int bookId = Integer.parseInt(bookIdstr);
            int readerID = Integer.parseInt(readerIDstr);
            int So_luong = Integer.parseInt(So_luongstr);
            // Chuyển đổi chuỗi ngày thành kiểu java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date_late = new Date(sdf.parse(date_lateStr).getTime());
            Date date_start = new Date(sdf.parse(date_startStr).getTime());

            // Kiểm tra xem có cuốn sách nào được chọn không
            int selectedRow = Table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu mượn để chỉnh sửa.");
                return;
            }

            // Lấy ID của phiếu mượn được chọn
            int id_phieu_muon = (int) Table.getValueAt(selectedRow, 0);

            // Tạo đối tượng phiếu mượn mới với thông tin đã chỉnh sửa
            phieu_muon book = new phieu_muon(id_phieu_muon, bookId, readerID, So_luong, Phi_muonstr, date_start, date_late);

            // Gọi phương thức trong controller để cập nhật thông tin của phiếu mượn
            controller.updateBook(book);

            // Xóa dữ liệu trong các trường nhập liệu
            SearchBooktf.setText("");
            bookIdtf.setText("");
            readerIDtf.setText("");
            So_luongtf.setText("0");
            ngay_muontf.setText("");
            ngay_tratf.setText("");

            // Cập nhật lại dữ liệu trong bảng sau khi lưu thành công
            listPhieumuon = controller.getPhieu_muon_List();
            Phieu_muon_List(listPhieumuon);
        } catch (NumberFormatException ex) {
            // Xử lý nếu có lỗi khi chuyển đổi dữ liệu về số nguyên
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số cho giá và số lượng.");
        } catch (Exception ex) {
            // Xử lý nếu có lỗi khi chuyển đổi chuỗi ngày thành kiểu Date
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày theo định dạng yyyy-MM-dd.");
        }
    }
});

      controller.setButtonListener(TangBtn, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Sắp xếp danh sách listPhieumuon theo ngày mượn (ngay_muon) tăng dần
        Collections.sort(listPhieumuon, Comparator.comparing(phieu_muon::getNgay_muon));
        
        // Cập nhật hiển thị của bảng
        Phieu_muon_List(listPhieumuon);
    }
});
      controller.setButtonListener(GiamBtn, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Sắp xếp danh sách listPhieumuon theo ngày mượn (ngay_muon) giảm dần
        Collections.sort(listPhieumuon, Comparator.comparing(phieu_muon::getNgay_muon).reversed());
        
        // Cập nhật hiển thị của bảng
        Phieu_muon_List(listPhieumuon);
    }
});




            SearchBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String keyword = SearchBooktf.getText().trim(); // Lấy từ khóa tìm kiếm từ JTextField và loại bỏ dấu cách thừa

        // Tạo một danh sách tạm thời để lưu kết quả tìm kiếm
        List<phieu_muon> searchResult = new ArrayList<>();

        // Duyệt qua danh sách sách hiện tại và kiểm tra xem từ khóa tìm kiếm có trong sách hay không
        for (phieu_muon book : listPhieumuon) {
            if (book.getPhi_muon().toLowerCase().contains(keyword) ||
               
                String.valueOf(book.getBookId()).contains(keyword) ||
                String.valueOf(book.getReaderID()).contains(keyword) ||
                String.valueOf(book.getSo_luong()).contains(keyword) ||
                String.valueOf(book.getNgay_muon()).contains(keyword) ||
                String.valueOf(book.getNgay_tra()).contains(keyword) ||
                String.valueOf(book.getId_phieu_muon()).contains(keyword) 
                ) {
                // Nếu tìm thấy từ khóa trong tên sách, thêm sách vào danh sách kết quả
                searchResult.add(book);
            }
        }

        // Hiển thị kết quả tìm kiếm trên bảng
        Phieu_muon_List(searchResult);
    }
});

        add(panel);
        setTitle("PHIẾU MƯỢN");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    
    
    
    public void Phieu_muon_List(List<phieu_muon> Phieu_muon_List) {
         DefaultTableModel model = (DefaultTableModel) Table.getModel();
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (phieu_muon book : Phieu_muon_List) {
            // Định dạng ngày tháng thành chuỗi
            String formattedNgayMuon = dateFormat.format(book.getNgay_muon());
            String formattedNgayTra = dateFormat.format(book.getNgay_tra());
            // Thêm dữ liệu vào bảng
            model.addRow(new Object[]{
                    book.getId_phieu_muon(),
                    book.getBookId(),
                    book.getReaderID(),
                    book.getSo_luong(),
                    book.getPhi_muon(),
                    formattedNgayMuon,
                    formattedNgayTra
            });
        }
    }
   


    public void clearFields() {
        
        SearchBooktf.setText("");
        bookIdtf.setText("");
        readerIDtf.setText("");
        So_luongtf.setText("0");
        ngay_muontf.setText("");
        ngay_tratf.setText("");
        
       
    }

//    public void addAddBtnListener(ActionListener listener) {
//        AddBtn.addActionListener(listener);
//    }

    public Phieu_muon_view(JTextField SearchBooktf, JTextField BookIdtf, JTextField ReaderIDtf, JTextField So_luongtf, JTextField Ngay_muon, JTextField Ngay_tra) {
        this.SearchBooktf = SearchBooktf;
        this.bookIdtf = BookIdtf;
        this.readerIDtf = ReaderIDtf;
        this.So_luongtf = So_luongtf;
        this.ngay_muontf = Ngay_muon;
        this.ngay_tratf = Ngay_tra;
       
    }

     public JComboBox<String>getPhi_muoncbb() {
        return Phi_muoncbb;
    }

    public JTextField getSearchBooktf() {
        return SearchBooktf;
    }
    public JTextField getBookIdtf() {
        return bookIdtf;
    }
    public JTextField getReaderIDtf() {
        return readerIDtf;
    }

    public JTextField getSo_luongtf() {
        return So_luongtf;
    }

    public JTextField getNgay_muontf() {
        return ngay_muontf;
    }

    public JTextField getNgay_tratf() {
        return ngay_tratf;
    }

   

    

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
