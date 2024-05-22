/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa
 */
package View;

import Controller.CardController;
import Controller.ReaderController;
import Model.ReaderModel;
import Model.CardModel;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.ModuleLayer.Controller;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Model.ReaderModel;

public class CardView extends JFrame implements ActionListener {

    private JButton BtnThem, BackBtn, BtnSua, BtnXoa, BtnLuu, BtnTimKiem, BtnExcel;
    private JScrollPane JScrollPaneTable;
    private JTable Table;
    private JTextField MaThe, TimKiemThe;
    private JComboBox MaDGcbb;
    private JSpinner spNgayDangKy, spNgayHetHan;
    private SpinnerDateModel dateModelDangKy, dateModelHetHan;
    private JLabel lblMaThe, lblMaDocGia, lblNgayDangKy, lblNgayHetHan;
    private DefaultTableModel tblModel;
    private final String[] columnNames = new String[]{"Mã thẻ", "Mã độc giả", "Ngày đăng ký", "Ngày hết hạn", "Trạng thái thẻ"};

    private final Object data = new Object[][]{};
    private List<CardModel> listCard = new ArrayList<>();
    //int Id;

    public CardView() {
        initComponents();
        GETDATA();
    }
    ReaderController readerController = new ReaderController();

// Khai báo một danh sách chứa độc giả
    List<ReaderModel> readerList;

    private void GETDATA() {
        try {
            // Gọi phương thức để lấy danh sách độc giả từ ReaderController
            readerList = readerController.getReaderList();

            // Duyệt qua danh sách và thêm các mã độc giả vào MaDGcbb
            for (ReaderModel reader : readerList) {
                MaDGcbb.addItem(reader.getReaderid());
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
    }

    private void initComponents() {
        JScrollPaneTable = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lblMaDocGia = new JLabel("Mã độc giả: ");
        lblNgayDangKy = new JLabel("Ngày đăng ký: ");
        lblNgayHetHan = new JLabel("Ngày hết hạn: ");
        lblMaThe = new JLabel("Mã thẻ: ");

        TimKiemThe = new JTextField(15);
        // TenDocGia = new JTextField(10);
        // MaDocGia = new JTextField(13);
        MaDGcbb = new JComboBox();
        MaThe = new JTextField(13);

        dateModelDangKy = new SpinnerDateModel();
        dateModelHetHan = new SpinnerDateModel();
        spNgayDangKy = new JSpinner(dateModelDangKy);
        spNgayHetHan = new JSpinner(dateModelHetHan);
        spNgayDangKy.setEditor(new JSpinner.DateEditor(spNgayDangKy, "dd/MM/yyyy"));
        spNgayHetHan.setEditor(new JSpinner.DateEditor(spNgayHetHan, "dd/MM/yyyy"));

        BtnThem = new JButton("Thêm");
        BtnSua = new JButton("Sửa");
        BtnXoa = new JButton("Xóa");
        BtnLuu = new JButton("Lưu");
        BtnExcel = new JButton("Xuất Excel");
        BackBtn = new JButton("Quay lại");
        BtnTimKiem = new JButton("Tìm Kiếm");

        JScrollPaneTable = new JScrollPane();
        Table = new JTable();
        tblModel = new DefaultTableModel((Object[][]) data, columnNames);
        Table.setModel(tblModel);
        Table.getTableHeader().setReorderingAllowed(false);
        Table.setDefaultEditor(Object.class, null);// Không cho phép chỉnh sửa trực tiếp trên các ô trong bảng
        JScrollPaneTable.setViewportView(Table);
        JScrollPaneTable.setPreferredSize(new Dimension(1247, 300));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(JScrollPaneTable);

        panel.add(lblMaDocGia);
        panel.add(lblNgayDangKy);
        panel.add(lblNgayHetHan);
        panel.add(lblMaThe);

        panel.add(lblMaDocGia);
        panel.add(lblNgayDangKy);
        panel.add(lblNgayHetHan);
        panel.add(lblMaThe);
        // panel.add(MaDocGia);
        panel.add(MaDGcbb);
        panel.add(spNgayDangKy);
        panel.add(spNgayHetHan);
        panel.add(MaThe);

        panel.add(TimKiemThe);
        panel.add(BtnTimKiem);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Sử dụng BoxLayout để các nút được sắp xếp theo chiều ngang

        // Thêm các nút vào panel
        buttonPanel.add(BtnThem);
        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(BtnSua);
        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(BtnXoa);
        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(BtnLuu);
        buttonPanel.add(Box.createHorizontalStrut(40));

        buttonPanel.add(BackBtn);
        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(BtnExcel);
        buttonPanel.add(Box.createHorizontalStrut(40));

        // Thêm panel chứa các nút vào phần dưới ùng của panel chính
        panel.add(buttonPanel);

        // bang the
        layout.putConstraint(SpringLayout.WEST, TimKiemThe, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TimKiemThe, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, BtnTimKiem, 160, SpringLayout.WEST, TimKiemThe);
        layout.putConstraint(SpringLayout.NORTH, BtnTimKiem, 17, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, JScrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, JScrollPaneTable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, JScrollPaneTable, -19, SpringLayout.EAST, panel);
        //cot 1
        layout.putConstraint(SpringLayout.WEST, lblMaThe, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblMaThe, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, MaThe, 110, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaThe, 60, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, lblMaDocGia, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblMaDocGia, 100, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, MaDGcbb, 110, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaDGcbb, 100, SpringLayout.SOUTH, JScrollPaneTable);

        //cot 2
        layout.putConstraint(SpringLayout.WEST, lblNgayDangKy, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblNgayDangKy, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, spNgayDangKy, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, spNgayDangKy, 60, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, lblNgayHetHan, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblNgayHetHan, 100, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, spNgayHetHan, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, spNgayHetHan, 100, SpringLayout.SOUTH, JScrollPaneTable);

        //nut
        layout.putConstraint(SpringLayout.WEST, buttonPanel, 30, SpringLayout.WEST, panel); // Đặt panel ở phía bên trái của panel chính
        layout.putConstraint(SpringLayout.SOUTH, buttonPanel, -20, SpringLayout.SOUTH, panel); // Đặt panel ở phía dưới cùng của panel chính   

        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        CardController controller = new CardController();
        listCard = controller.getCardList();
        this.CardList(listCard);

        BtnThem.addActionListener((ActionListener) this);
        BtnSua.addActionListener((ActionListener) this);
        BtnXoa.addActionListener((ActionListener) this);
        BtnLuu.addActionListener(this);
        BackBtn.addActionListener(this);
        controller.setButtonListener(BtnThem, new ActionListener() {
            private int IDDocGia;

            @Override
            public void actionPerformed(ActionEvent e) {
                int ID = Integer.parseInt(MaThe.getText());
                int selectedReaderID = Integer.parseInt(MaDGcbb.getSelectedItem().toString());

                // Lấy ngày đăng ký và ngày hết hạn từ Spinner
                Date NgayDangKy = (Date) spNgayDangKy.getValue();
                Date NgayHetHan = (Date) spNgayHetHan.getValue();

                // Chuyển đổi java.util.Date sang java.sql.Date
                java.sql.Date sqlNgayDangKy = new java.sql.Date(NgayDangKy.getTime());
                java.sql.Date sqlNgayHetHan = new java.sql.Date(NgayHetHan.getTime());

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Ngày đăng ký: " + dateFormat1.format(NgayDangKy));
                System.out.println("Ngày hết hạn: " + dateFormat1.format(NgayHetHan));

                try {
//
//                    int ID = Integer.parseInt(MaThe.getText());

                    // Kiểm tra xem đã chọn độc giả từ combobox chưa
                    if (MaDGcbb.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một độc giả.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // Thêm thẻ vào danh sách hoặc cơ sở dữ liệu
                    //controller.AddCard(card);
                    if (controller.isCardExists(ID)) {
                        JOptionPane.showMessageDialog(null, "Mã thẻ  đã tồn tại....", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return; // Không thực hiện thêm độc giả nếu readerId đã tồn tại
                    }
                    if (controller.isReaderExists(selectedReaderID)) {
                        JOptionPane.showMessageDialog(null, "Mã độc giả đã tồn tại....", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return; // Không thực hiện thêm độc giả nếu readerId đã tồn tại
                    }
//                   else {
//    // Nếu thẻ chưa tồn tại, thực hiện thêm thẻ vào cơ sở dữ liệu
//    controller.AddCard(card);
//    JOptionPane.showMessageDialog(null, "Thêm thẻ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//}
                    CardModel card = new CardModel(ID, selectedReaderID, sqlNgayDangKy, sqlNgayHetHan);
                    controller.AddCard(card);
                    JOptionPane.showMessageDialog(null, "Thêm thẻ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    // Lấy danh sách thẻ mới và cập nhật giao diện
                    listCard = controller.getCardList();
                    CardList(listCard);
                    clearFields();
                } catch (NumberFormatException ex) {
                    // Xử lý nếu có lỗi khi chuyển đổi dữ liệu về số nguyên
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số cho mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                //System.out.println("Thêm thành công");
            }
        });

        controller.setButtonListener(BtnSua, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Table.getSelectedRow();
                if (selectedRow != -1) {
                    MaThe.setText(String.valueOf(Table.getValueAt(selectedRow, 0)));

                    // Khởi tạo một đối tượng ReaderController
                    ReaderController readerController = new ReaderController();

                    // Lấy danh sách độc giả từ ReaderController
                    List<ReaderModel> readerList = readerController.getReaderList();

                    // Xóa tất cả các mục trong MaDGcbb trước khi thêm mới
                    MaDGcbb.removeAllItems();

                    // Duyệt qua danh sách độc giả và thêm vào MaDGcbb
                    for (ReaderModel reader : readerList) {
                        MaDGcbb.addItem(reader.getReaderid());
                    }

                    // Set giá trị của MaDGcbb tương ứng với thẻ được chọn
                    MaDGcbb.setSelectedItem(Table.getValueAt(selectedRow, 1));

                    spNgayDangKy.setValue(Table.getValueAt(selectedRow, 2));
                    spNgayHetHan.setValue(Table.getValueAt(selectedRow, 3));
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một thẻ để sửa", "Lỗi Sửa", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        controller.setButtonListener(BtnXoa, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Table.getSelectedRow();
                if (selectedRow == -1) {
                    // Nếu không có hàng nào được chọn, hiển thị thông báo lỗi
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một thẻ để xóa", "Lỗi Xóa", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int ID = (int) Table.getValueAt(selectedRow, 0);
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thẻ này không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        controller.DeleteCard(ID);
                    } catch (SQLException ex) {
                        Logger.getLogger(CardView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    listCard = controller.getCardList();
                    CardList(listCard);

                }
            }
        });
        controller.setButtonListener(BtnLuu, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String IDSt = MaThe.getText();

                // Kiểm tra xem đã chọn độc giả từ combobox chưa
                if (MaDGcbb.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một độc giả.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Lấy ID của độc giả từ combobox được chọn
                int selectedReaderIndex = MaDGcbb.getSelectedIndex();
                int selectedReaderID = Integer.parseInt(MaDGcbb.getSelectedItem().toString());

                // Lấy ngày đăng ký và ngày hết hạn từ Spinner
                Date NgayDangKy = (Date) spNgayDangKy.getValue();
                Date NgayHetHan = (Date) spNgayHetHan.getValue();

                // Chuyển đổi java.util.Date sang java.sql.Date
                java.sql.Date sqlNgayDangKy = new java.sql.Date(NgayDangKy.getTime());
                java.sql.Date sqlNgayHetHan = new java.sql.Date(NgayHetHan.getTime());

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Ngày đăng ký: " + dateFormat1.format(NgayDangKy));
                System.out.println("Ngày hết hạn: " + dateFormat1.format(NgayHetHan));

                if (IDSt.isEmpty()) {
                    // Nếu có một trường nào đó chưa được điền, hiển thị thông báo và không thực hiện lưu
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
                    return;
                }
                

                try {
                    // Chuyển đổi dữ liệu về số nguyên
                    int ID = Integer.parseInt(IDSt);

                    // Kiểm tra xem có thẻ nào được chọn không
                    int selectedRow = Table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một thẻ để chỉnh sửa.");
                        return;
                    }
                    
                    if (controller.isReaderExists(selectedReaderID)) {
                        JOptionPane.showMessageDialog(null, "Mã độc giả đã tồn tại....", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return; // Không thực hiện thêm độc giả nếu readerId đã tồn tại
                    }

                    // Gọi phương thức trong controller để cập nhật thông tin của thẻ
                    CardModel card = new CardModel(ID, selectedReaderID, sqlNgayDangKy, sqlNgayHetHan);
                    controller.UpdateCard(card);
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    // Cập nhật lại dữ liệu trong bảng sau khi lưu thành công
                    listCard = controller.getCardList();
                    CardList(listCard);
                    clearFields();
                } catch (NumberFormatException ex) {
                    // Xử lý nếu có lỗi khi chuyển đổi dữ liệu về số nguyên
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số cho mã");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin thẻ.");
//                    Logger.getLogger(CardView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        BtnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = TimKiemThe.getText().trim().toLowerCase(); // Lấy từ khóa tìm kiếm từ JTextField và chuyển đổi sang chữ thường

                // Tạo một danh sách tạm thời để lưu kết quả tìm kiếm
                List<CardModel> searchResult = new ArrayList<>();

                // Nếu từ khóa tìm kiếm không rỗng, thực hiện tìm kiếm
                if (!keyword.isEmpty()) {
                    // Duyệt qua danh sách thẻ hiện tại và kiểm tra xem từ khóa tìm kiếm có trong thông tin của thẻ hay không
                    for (CardModel card : listCard) {
                        if (String.valueOf(card.getID()).equals(keyword)) //                                || String.valueOf(card.getIDDocGia()).equals(keyword)
                        //                                || card.getNgayDangKy().toString().equals(keyword)
                        //                                || card.getNgayHetHan().toString().equals(keyword)) 
                        {
                            // Nếu tìm thấy từ khóa trong thông tin thẻ, thêm thẻ vào danh sách kết quả
                            searchResult.add(card);
                        }
                    }
                } else {
                    // Nếu từ khóa tìm kiếm là rỗng, hiển thị lại bảng ban đầu
                    CardList(listCard);
                    return; // Dừng thực hiện phương thức
                }

                // Hiển thị kết quả tìm kiếm trên bảng
                CardList(searchResult);
            }
        });
        BackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        BtnExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Danh sách thẻ");
                XSSFRow row = null;
                Cell cell = null;

                row = sheet.createRow(0);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("ID");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("ID độc giả");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Ngày đăng ký");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Ngày hết hạn");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Trạng thái thẻ");
                List<CardModel> list1 = controller.getCardList();

                if (list1 != null) {
                    FileOutputStream fos = null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        int s = list1.size();
                        for (int i = 0; i < s; i++) {
                            CardModel c = list1.get(i);
                            row = sheet.createRow(1 + i);

                            cell = row.createCell(0, CellType.STRING);
                            cell.setCellValue(c.getID());

                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(c.getIDDocGia());

                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(dateFormat.format(c.getNgayDangKy())); // Chuyển định dạng NgayDangKy

                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(dateFormat.format(c.getNgayHetHan())); // Chuyển định dạng NgayHetHan
                            cell = row.createCell(4, CellType.STRING);
                            String cardStatus = calculateCardStatus(c.getNgayHetHan());
                            cell.setCellValue(cardStatus);
                        }
                        File f = new File("D:\\cardlist.xlsx");
                        fos = new FileOutputStream(f);
                        workbook.write(fos);
                        fos.close();
                        JOptionPane.showMessageDialog(rootPane, "Excel Success!.File path: " + f.getPath());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        add(panel);
        setTitle("Quản lý thẻ mượn sách");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private String calculateCardStatus(Date endDate) {
        Date currentDate = new Date(); // Lấy thời gian hiện tại
        if (endDate.after(currentDate)) {
            return "Đang hoạt động"; // Nếu ngày hết hạn sau ngày hiện tại, thẻ đang hoạt động
        } else {
            return "Không hoạt động"; // Ngược lại, thẻ không hoạt động
        }
    }

    public void clearFields() {
        MaThe.setText("");
        MaDGcbb.setSelectedIndex(0);

    }

    public void CardList(List<CardModel> CardList) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        model.setRowCount(0);
        for (CardModel card : CardList) {
            // Tính toán trạng thái của thẻ dựa trên ngày hết hạn
            String cardStatus = calculateCardStatus(card.getNgayHetHan());
            // Thêm hàng mới vào bảng với dữ liệu của thẻ và trạng thái của nó
            model.addRow(new Object[]{
                card.getID(), card.getIDDocGia(), card.getNgayDangKy(), card.getNgayHetHan(), cardStatus
            });
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        CardView v = new CardView();
    }
}
