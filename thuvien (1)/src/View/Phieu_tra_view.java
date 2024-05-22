/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import controller.Btl_thu_vien;
import controller.Phieu_Tra;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.WindowConstants;
import controller.Btl_thu_vien;
import java.util.List;
import Model.phieu_muon;
import View.Phieu_muon_view;
import Model.PhieuTraPhieuMuonJoin;
import controller.Phieu_Tra;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import XuatExcel.ExcelExporter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;



public class Phieu_tra_view extends JFrame implements ActionListener {
    private JButton TraBtn, XoaBtn, XuatBtn,BackBtn, Search_phieu_traBtn, TangBtn,GiamBtn;
    private JScrollPane jscrollPaneTable;
    private JTable Table1;
   
    private JTextField Search_phieu_tratf, id_phieu_tratf;
    private DefaultTableModel tblModel1;
    private final String[] columnNames = new String[]{"ID Phiếu Trả","ID Phiếu Mượn","Mã Sách","Mã Độc Giả","Số Lượng","Phí Mượn","Ngày Trả","Ngày Mượn", "Tình Trạng"};
    private final Object data = new Object[][]{};
    private Phieu_Tra controller;
    private List<PhieuTraPhieuMuonJoin> listPhieuTra = new ArrayList<>();

    public Phieu_tra_view() throws SQLException {
    initComponents();
    controller = new Phieu_Tra();
    loadDataFromPhieuMuon(); 
    }

    private void initComponents() throws SQLException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Search_phieu_tratf = new JTextField(15);

        TraBtn = new JButton("Trả Sách");
        XoaBtn = new JButton("Xoá Phiếu");
        XuatBtn = new JButton("Xuất File");
        Search_phieu_traBtn = new JButton("Tìm Kiếm");
        TangBtn = new JButton("Tăng");
        GiamBtn = new JButton("Giảm");
        BackBtn=new JButton("Quay lại");

        jscrollPaneTable = new JScrollPane();
        Table1 = new JTable();
        Table1.getTableHeader().setReorderingAllowed(false);
        Table1.setDefaultEditor(Object.class, null);// Không cho phép chỉnh sửa trực tiếp trên các ô trong bảng
        tblModel1 = new DefaultTableModel((Object[][]) data, columnNames);
        Table1.setModel(tblModel1);
        jscrollPaneTable.setViewportView(Table1);
        jscrollPaneTable.setPreferredSize(new Dimension(1247, 300));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(jscrollPaneTable);
        panel.add(Search_phieu_tratf);
        panel.add(Search_phieu_traBtn);
        panel.add(TangBtn);
        panel.add(GiamBtn);

        layout.putConstraint(SpringLayout.WEST, jscrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jscrollPaneTable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, jscrollPaneTable, -20, SpringLayout.EAST, panel);

        layout.putConstraint(SpringLayout.WEST, TangBtn, 50, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TangBtn, 20, SpringLayout.NORTH, panel);
        
         layout.putConstraint(SpringLayout.WEST, GiamBtn, 80, SpringLayout.WEST, TangBtn);
        layout.putConstraint(SpringLayout.NORTH, GiamBtn, 20, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, Search_phieu_tratf, 250, SpringLayout.WEST, GiamBtn);
        layout.putConstraint(SpringLayout.NORTH, Search_phieu_tratf, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, Search_phieu_traBtn, 150, SpringLayout.WEST, Search_phieu_tratf);
        layout.putConstraint(SpringLayout.NORTH, Search_phieu_traBtn, 20, SpringLayout.NORTH, panel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(TraBtn);
        buttonPanel.add(XoaBtn);
        buttonPanel.add(XuatBtn);
        buttonPanel.add(BackBtn);
        panel.add(buttonPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttonPanel, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, buttonPanel, 20, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, TraBtn, 10, SpringLayout.WEST, buttonPanel);
        layout.putConstraint(SpringLayout.WEST, XoaBtn, 10, SpringLayout.EAST, TraBtn);
        layout.putConstraint(SpringLayout.WEST, XuatBtn, 10, SpringLayout.EAST, XoaBtn);
        layout.putConstraint(SpringLayout.EAST, BackBtn, -20, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH,BackBtn, 150, SpringLayout.SOUTH, jscrollPaneTable);


        setTitle("Phieu Tra View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        
        DefaultTableModel model = (DefaultTableModel) ((JTable) jscrollPaneTable.getViewport().getView()).getModel();
        Phieu_Tra controller = new Phieu_Tra();
        listPhieuTra = controller.getPhieu_tra_List();
        this.Phieu_tra_List(listPhieuTra);
        
       

        TraBtn.addActionListener(this);
        TangBtn.addActionListener(this);
        GiamBtn.addActionListener(this);
        XoaBtn.addActionListener(this);
        XuatBtn.addActionListener(this);
        Search_phieu_traBtn.addActionListener(this);
        
        
        
        controller.setButtonListener1(XuatBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int columnCount = tblModel1.getColumnCount();
                DefaultTableModel filteredModel = new DefaultTableModel();
                for (int i = 0; i < columnCount; i++) {
                    filteredModel.addColumn(tblModel1.getColumnName(i));
                }
                int rowCount = tblModel1.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    if (Table1.convertRowIndexToModel(i) != -1) {
                        Object[] rowData = new Object[columnCount];
                        for (int j = 0; j < columnCount; j++) {
                            rowData[j] = Table1.getValueAt(i, j);
                        }
                        filteredModel.addRow(rowData);
                    }
                }
                ExcelExporter.exportToExcel(filteredModel, "C:\\Xuat_Excel\\phieu_tra.xlsx");
            }
        });

        // Bắt sự kiện cho nút Trả Sách
        controller.setButtonListener1(TraBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Table1.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu trả để xác nhận đã trả", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id_phieu_tra = (int) Table1.getValueAt(selectedRow, 0);
                int choice = JOptionPane.showConfirmDialog(null, "Xác nhận đã trả sách?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    controller.updateTinhTrangDaTra(id_phieu_tra);
                    listPhieuTra = controller.getPhieu_muon_List();
                    Phieu_tra_List(listPhieuTra);
                    JOptionPane.showMessageDialog(null, "Đã xác nhận sách đã được trả", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Bắt sự kiện cho nút Xóa Phiếu
        controller.setButtonListener1(XoaBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Table1.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu trả để xóa", "Lỗi Xóa", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id_phieu_tra = (int) Table1.getValueAt(selectedRow, 0);
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu trả này không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    controller.deleteBook(id_phieu_tra);
                    listPhieuTra = controller.getPhieu_muon_List();
                    Phieu_tra_List(listPhieuTra);
                    Table1.getSelectionModel().clearSelection();
                    if (listPhieuTra.isEmpty()) {
                        return;
                    }
                }
            }
        });
        
        // Xử lý sự kiện cho nút Tăng
controller.setButtonListener1(TangBtn, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Sắp xếp danh sách listPhieumuon theo ngày trả (ngay_tra) tăng dần
        Collections.sort(listPhieuTra, Comparator.comparing(PhieuTraPhieuMuonJoin::getNgay_tra));
        
        // Cập nhật hiển thị của bảng
        Phieu_tra_List(listPhieuTra);
    }
});

     BackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
// Xử lý sự kiện cho nút Giảm
controller.setButtonListener1(GiamBtn, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Sắp xếp danh sách listPhieumuon theo ngày trả (ngay_tra) giảm dần
        Collections.sort(listPhieuTra, Comparator.comparing(PhieuTraPhieuMuonJoin::getNgay_tra).reversed());
        
        // Cập nhật hiển thị của bảng
        Phieu_tra_List(listPhieuTra);
    }
});

        // Bắt sự kiện cho nút Tìm Kiếm
        controller.setButtonListener1(Search_phieu_traBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = Search_phieu_tratf.getText().trim();
                List<PhieuTraPhieuMuonJoin> searchResult = new ArrayList<>();
                for (PhieuTraPhieuMuonJoin data : listPhieuTra) {
                    if (data.getPhi_muon().toLowerCase().contains(keyword) ||
                            data.getTinh_trang().toLowerCase().contains(keyword) ||
                            String.valueOf(data.getBookId()).contains(keyword) ||
                            String.valueOf(data.getReaderID()).contains(keyword) ||
                            String.valueOf(data.getSo_luong()).contains(keyword) ||
                            String.valueOf(data.getNgay_muon()).contains(keyword) ||
                            String.valueOf(data.getNgay_tra()).contains(keyword) ||
                            String.valueOf(data.getId_phieu_muon()).contains(keyword) ||
                            String.valueOf(data.getId_phieu_tra()).contains(keyword)) {
                        searchResult.add(data);
                    }
                }
                Phieu_tra_List(searchResult);
            }
        });
        
        Phieu_tra_List(listPhieuTra);
        
    }
    
  
   public void setButtonListener1(JButton button, ActionListener listener) {
        button.addActionListener(listener);
    }
//   
  private void loadDataFromPhieuMuon() throws SQLException {
    Phieu_Tra controller = new Phieu_Tra();
    List<PhieuTraPhieuMuonJoin> Phieu_tra_List = controller.getPhieu_muon_List();
    // Gọi phương thức Phieu_tra_List() để hiển thị dữ liệu lên bảng
    Phieu_tra_List(Phieu_tra_List);
}
  public void Phieu_muon_List(List<PhieuTraPhieuMuonJoin> Phieu_muon_List) {
    DefaultTableModel model = (DefaultTableModel) Table1.getModel();
    model.setRowCount(0);
    for (PhieuTraPhieuMuonJoin data : Phieu_muon_List) {
        model.addRow(new Object[]{
           // data.getId_phieu_tra(),
            data.getId_phieu_muon(),
            data.getBookId(),
            data.getReaderID(),
            data.getSo_luong(),
            data.getPhi_muon(),
            data.getNgay_muon(),
            data.getNgay_tra(),
            data.getTinh_trang()
        });
    }
}


public void Phieu_tra_List(List<PhieuTraPhieuMuonJoin> Phieu_tra_List) {
    DefaultTableModel model = (DefaultTableModel) Table1.getModel();
    model.setRowCount(0);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    for (PhieuTraPhieuMuonJoin data : Phieu_tra_List) {
        // Định dạng ngày tháng thành chuỗi
        String formattedNgayMuon = dateFormat.format(data.getNgay_muon());
        String formattedNgayTra = dateFormat.format(data.getNgay_tra());
        // Thêm dữ liệu vào bảng
        model.addRow(new Object[]{data.getId_phieu_tra(),
            data.getId_phieu_muon(), // ID Phiếu mượn
            data.getBookId(),
            data.getReaderID(),
            data.getSo_luong(),
            data.getPhi_muon(),
            formattedNgayMuon,
            formattedNgayTra,
            data.getTinh_trang()
        });
    }
}
public Phieu_tra_view(JTextField Search_phieu_tratf) {
        this.Search_phieu_tratf = Search_phieu_tratf;
        
       
    }
public JTextField Search_phieu_tratf() {
        return Search_phieu_tratf;
    }
@Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}





