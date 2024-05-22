/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ReaderController;
import Model.ReaderModel;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Windows
 */
public class View extends JFrame implements ActionListener {

    private JButton AddBtn, BackBtn, EditBtn, DeleteBtn, SaveBtn, SearchBtn, ExcelBtn;
    private JScrollPane jscrollPaneTable;
    private JTable Table;
    private JComboBox Gendercbb;

    private JTextField SearchBooktf, ReaderNametf, Phonetf, Emailtf, ReaderIDtf, Classtf;
    private JLabel ReaderNamelbl, Phonelbl, Genderlbl, Emaillbl, ReaderIDlbl, Classlbl;
    private DefaultTableModel tblModel;
    private final String[] columnNames = new String[]{
        "Mã độc giả", "Tên độc giả", "Lớp", "Giới tính", "Email", "Số điện thoại"};
    private final Object data = new Object[][]{};
    private List<ReaderModel> listReader = new ArrayList<>();

    int readerId;

    public View() {
        initComponents();
    }

    private void initComponents() {
        jscrollPaneTable = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ReaderIDlbl = new JLabel("Mã độc giả");
        ReaderNamelbl = new JLabel("Tên độc giả");
        Classlbl = new JLabel("Lớp");
        Genderlbl = new JLabel("Giới tính");
        Emaillbl = new JLabel("Email");
        Phonelbl = new JLabel("Số đt");

        ReaderNametf = new JTextField(20);
        SearchBooktf = new JTextField(15);
        Phonetf = new JTextField(10);
        Phonetf.setText("0");
        Emailtf = new JTextField(15);
        ReaderIDtf = new JTextField(10);
        Classtf = new JTextField(10);
        Gendercbb = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});

        //Sortcbb = new JComboBox<>(new String[]{"Sắp xếp", "Giảm dần theo giá", "Tăng dần theo giá"});
        AddBtn = new JButton("Thêm");
        EditBtn = new JButton("Sửa");
        DeleteBtn = new JButton("Xoá");
        SaveBtn = new JButton("Lưu");
        SearchBtn = new JButton("Tìm kiếm");
        ExcelBtn = new JButton("Xuất excel");
        BackBtn = new JButton("Quay lại");

        jscrollPaneTable = new JScrollPane();
        Table = new JTable();
        tblModel = new DefaultTableModel((Object[][]) data, columnNames);
        Table.setModel(tblModel);
        Table.getTableHeader().setReorderingAllowed(false);
        Table.setDefaultEditor(Object.class, null);// Không cho phép chỉnh sửa trực tiếp trên các ô trong bảng
        jscrollPaneTable.setViewportView(Table);
        jscrollPaneTable.setPreferredSize(new Dimension(1247, 300));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(jscrollPaneTable);

        panel.add(ReaderNamelbl);
        panel.add(Phonelbl);
        panel.add(Genderlbl);
        panel.add(Emaillbl);
        panel.add(ReaderIDlbl);
        panel.add(Classlbl);

        panel.add(ReaderNametf);
        panel.add(Phonetf);
        panel.add(Classtf);

        panel.add(Emailtf);
        panel.add(ReaderIDtf);
        panel.add(Gendercbb);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Sử dụng BoxLayout để các nút được sắp xếp theo chiều ngang

        buttonPanel.add(AddBtn);
        buttonPanel.add(Box.createHorizontalStrut(30)); // Tạo một khoảng cách ngang 30 pixel giữa các nút
        buttonPanel.add(EditBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(DeleteBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(SaveBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(BackBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(ExcelBtn);

        // Thêm panel chứa các nút vào phần dưới cùng của panel chính
        panel.add(buttonPanel);

        panel.add(SearchBooktf);
        panel.add(SearchBtn);
//        panel.add(Sortcbb);

        //Bang doc gia
//        layout.putConstraint(SpringLayout.WEST, Sortcbb, 315, SpringLayout.WEST, panel);
//        layout.putConstraint(SpringLayout.NORTH, Sortcbb, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, SearchBooktf, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, SearchBooktf, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SearchBtn, 180, SpringLayout.WEST, SearchBooktf);
        layout.putConstraint(SpringLayout.NORTH, SearchBtn, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jscrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jscrollPaneTable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, jscrollPaneTable, -19, SpringLayout.EAST, panel);
        //cot 1
        layout.putConstraint(SpringLayout.WEST, ReaderNamelbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ReaderNamelbl, 20, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ReaderNametf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ReaderNametf, 20, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, ReaderIDlbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ReaderIDlbl, 60, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ReaderIDtf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ReaderIDtf, 60, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, Classlbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Classlbl, 100, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Classtf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Classtf, 100, SpringLayout.SOUTH, jscrollPaneTable);

        //cot2
        layout.putConstraint(SpringLayout.WEST, Genderlbl, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Genderlbl, 20, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Gendercbb, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Gendercbb, 20, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, Emaillbl, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Emaillbl, 60, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Emailtf, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Emailtf, 60, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, Phonelbl, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Phonelbl, 100, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Phonetf, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Phonetf, 100, SpringLayout.SOUTH, jscrollPaneTable);
        //nut
//        layout.putConstraint(SpringLayout.EAST, AddBtn, -32, SpringLayout.WEST, EditBtn);
//        layout.putConstraint(SpringLayout.NORTH, AddBtn, 25, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, buttonPanel, 20, SpringLayout.WEST, panel); // Đặt panel ở phía bên trái của panel chính
        layout.putConstraint(SpringLayout.SOUTH, buttonPanel, -20, SpringLayout.SOUTH, panel); // Đặt panel ở phía dưới cùng của panel chính
        DefaultTableModel model = (DefaultTableModel) ((JTable) jscrollPaneTable.getViewport().getView()).getModel();
        ReaderController controller = new ReaderController();
        listReader = controller.getReaderList();
        this.ReaderList(listReader);

        AddBtn.addActionListener(this);
        EditBtn.addActionListener(this);
        SaveBtn.addActionListener(this);
        DeleteBtn.addActionListener(this);
        ExcelBtn.addActionListener(this);
        BackBtn.addActionListener(this);
        // Lắng nghe sự kiện khi người dùng nhập số điện thoại
        getPhonetf().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                if (text.length() >= 10) { // Nếu độ dài của số điện thoại đã đạt đến 10 ký tự
                    e.consume(); // Không cho phép nhập thêm ký tự
                }
            }
        });

        controller.setButtonListener(AddBtn, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy thông tin từ các trường nhập liệu
        int readerId = Integer.parseInt(getReaderIDtf().getText());
        String readernamestr = getReaderNametf().getText();
        String lopstr = getClasstf().getText();
        String genderstr = (String) Gendercbb.getSelectedItem();
        String emailstr = getEmailtf().getText();
        String phonestr = getPhonetf().getText(); // Sử dụng kiểu String cho số điện thoại

        try {
            // Kiểm tra xem readerId đã tồn tại trong cơ sở dữ liệu chưa
            if (controller.isReaderIdExists(readerId)) {
                JOptionPane.showMessageDialog(null, "Mã độc giả đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Không thực hiện thêm độc giả nếu readerId đã tồn tại
            }

            // Kiểm tra độ dài của số điện thoại
            if (phonestr.length() != 10) {
                JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Không thực hiện thêm độc giả nếu số điện thoại không hợp lệ
            }

            // Kiểm tra số điện thoại chỉ chứa các ký tự số
            if (!phonestr.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại chỉ được chứa các ký tự số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Không thực hiện thêm độc giả nếu số điện thoại không hợp lệ
            }

            // Kiểm tra tính hợp lệ của email
            if (emailstr.isEmpty() || !emailstr.contains("@")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ email hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Không thực hiện thêm độc giả nếu email không hợp lệ
            }

            // Chuyển chuỗi số điện thoại thành kiểu Integer
            int phone = Integer.parseInt(phonestr);

            // Tạo một đối tượng ReaderModel
            ReaderModel readerModel = new ReaderModel(readerId, readernamestr, lopstr, genderstr, emailstr, phone);
            controller.addReader(readerModel);
            JOptionPane.showMessageDialog(null, "Độc giả đã được thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            listReader = controller.getReaderList();
            ReaderList(listReader);
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Số điện thoại chỉ được chứa các ký tự số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
});


        controller.setButtonListener(DeleteBtn, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Table.getSelectedRow();
                if (selectedRow == -1) {
                    // Nếu không có hàng nào được chọn, hiển thị thông báo lỗi
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một độc giả để xóa", "Lỗi Xóa", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int readerId = (int) Table.getValueAt(selectedRow, 0);
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa độc giả này không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        controller.deleteReader(readerId);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    listReader = controller.getReaderList();
                    ReaderList(listReader);

                }
            }
        });
        controller.setButtonListener(EditBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra xem có hàng nào được chọn không
                if (Table.getSelectedRow() != -1) {
                    // Lấy hàng đã chọn
                    int selectedRow = Table.getSelectedRow();

                    // Lấy dữ liệu từ bảng và đặt vào các JTextField và JComboBox
                    Object readerIdObj = Table.getValueAt(selectedRow, 0);
                    String readerId = "";
                    if (readerIdObj != null) {
                        readerId = String.valueOf(readerIdObj);
                    }
                    String Name = (String) Table.getValueAt(selectedRow, 1);
                    String lop = (String) Table.getValueAt(selectedRow, 2);
                    String gender = (String) Table.getValueAt(selectedRow, 3);
                    String emailstr = (String) Table.getValueAt(selectedRow, 4);
                    int phone = (int) Table.getValueAt(selectedRow, 5);
                    ReaderIDtf.setText(readerId);
                    ReaderNametf.setText(Name);
                    Classtf.setText(lop);
                    // Đặt giá trị được chọn cho JComboBox Gendercbb
                    Gendercbb.setSelectedItem(gender);
                    Emailtf.setText(emailstr);
                    Phonetf.setText(String.valueOf(phone));
                    //phone = (int) Table.getValueAt(selectedRow, 0);
                    
                }
            }
        });
        controller.setButtonListener(SaveBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Kiểm tra xem người dùng đã nhập đầy đủ thông tin hay chưa
                    String readeridstr = ReaderIDtf.getText();
                    String readernamestr = ReaderNametf.getText();
                    String lopstr = Classtf.getText();
                    String genderstr = (String) Gendercbb.getSelectedItem();  // Sử dụng phương thức getSelectedItem() để lấy giá trị được chọn trong JComboBox
                    String emailstr = Emailtf.getText();
                    String phonestr = Phonetf.getText();

                    if (readeridstr.isEmpty() || readernamestr.isEmpty() || lopstr.isEmpty() || emailstr.isEmpty() || phonestr.isEmpty()) {
                        // Nếu có một trường nào đó chưa được điền, hiển thị thông báo và không thực hiện lưu
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin cho độc giả.");
                        return;
                    }

                    // Kiểm tra định dạng dữ liệu và chuyển đổi dữ liệu sang kiểu phù hợp
                    int readerId;
                    int phone;
                    try {
                        readerId = Integer.parseInt(readeridstr);
                        phone = Integer.parseInt(phonestr);
                    } catch (NumberFormatException ex) {
                        // Xử lý nếu có lỗi khi chuyển đổi dữ liệu về số nguyên
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số cho mã độc giả.");
                        return;
                    }

                    // Kiểm tra xem có cuốn sách nào được chọn không
                    int selectedRow = Table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một độc giả để chỉnh sửa.");
                        return;
                    }

                    // Gọi phương thức trong controller để cập nhật thông tin của độc giả
                    ReaderModel readerModel = new ReaderModel(readerId, readernamestr, lopstr, genderstr, emailstr, phone);
                    controller.updateReader(readerModel); // Thay đổi tên phương thức tương ứng với phương thức trong controller

                    // Cập nhật lại dữ liệu trong bảng sau khi lưu thành công
                    listReader = controller.getReaderList();
                    ReaderList(listReader);
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công", "Lưu", JOptionPane.YES_OPTION);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin độc giả.");
                }
                clearFields();
            }
        });

//            Sortcbb.addActionListener(new ActionListener() {
//                 @Override
//    public void actionPerformed(ActionEvent e) {
//          String selectedSortOption = (String) Sortcbb.getSelectedItem();
//        switch (selectedSortOption) {
//            case "Tăng dần theo giá":
//                Collections.sort(listBook, Comparator.comparingInt(Book::getPrice));
//                break;
//            case "Giảm dần theo giá":
//                Collections.sort(listBook, Comparator.comparingInt(Book::getPrice).reversed());
//                break;
//            
//        }
//        // Cập nhật hiển thị của bảng
//        BookList(listBook);
//    }
//            });
        SearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = SearchBooktf.getText().trim(); // Lấy từ khóa tìm kiếm từ JTextField và loại bỏ dấu cách thừa

                // Tạo một danh sách tạm thời để lưu kết quả tìm kiếm
                List<ReaderModel> searchResult = new ArrayList<>();

                // Duyệt qua danh sách độc giả hiện tại và kiểm tra xem từ khóa tìm kiếm có trong độc giả hay không
                for (ReaderModel reader : listReader) {
                    if (reader.getReadername().toLowerCase().contains(keyword)
                            || reader.getLop().toLowerCase().contains(keyword)
                            || reader.getGender().toLowerCase().contains(keyword)
                            || reader.getEmail().toLowerCase().contains(keyword)
                            || String.valueOf(reader.getPhone()).contains(keyword)
                            || String.valueOf(reader.getReaderid()).contains(keyword)) {
                        // Nếu tìm thấy từ khóa trong thông tin độc giả, thêm độc giả vào danh sách kết quả
                        searchResult.add(reader);
                    }
                }

                // Hiển thị kết quả tìm kiếm trên bảng
                ReaderList(searchResult);
            }
        });
        BackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ExcelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Danh sach sách");
                XSSFRow row = null;
                Cell cell = null;

                row = sheet.createRow(0);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("id");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Tên độc giả");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Lớp");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Giới tính");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Email");

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Số điện thoại");

                List<ReaderModel> list1 = controller.getReaderList();

                if (list1 != null) {
                    FileOutputStream fos = null;
                    try {
                        int s = list1.size();
                        for (int i = 0; i < s; i++) {
                            ReaderModel dg = list1.get(i);
                            row = sheet.createRow(1 + i);

                            cell = row.createCell(0, CellType.STRING);
                            cell.setCellValue(dg.getReaderid());

                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(dg.getReadername());

                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(dg.getLop());

                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(dg.getGender());

                            cell = row.createCell(4, CellType.STRING);
                            cell.setCellValue(dg.getEmail());

                            cell = row.createCell(5, CellType.STRING);
                            cell.setCellValue(dg.getPhone());
                        }
                        File f = new File("D:\\readerlist.xlsx");
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
        setTitle("QUẢN LÝ ĐỘC GIẢ");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void ReaderList(List<ReaderModel> ReaderList) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) jscrollPaneTable.getViewport().getView()).getModel();
        model.setRowCount(0);
        for (ReaderModel readerList : ReaderList) {
            model.addRow(new Object[]{
                readerList.getReaderid(),
                readerList.getReadername(),
                readerList.getLop(),
                readerList.getGender(), readerList.getEmail(), readerList.getPhone()

            });
        }
    }

    public void clearFields() {
        SearchBooktf.setText("");
        ReaderIDtf.setText("");
        ReaderNametf.setText("");
        Classtf.setText("");
        //Gendertf.setText("");
        Emailtf.setText("");
        Phonetf.setText("0");
    }

    public View(JTextField ReaderIDtf, JTextField SearchBooktf, JTextField ReaderNametf, JTextField Phonetf, JTextField Emailtf, JTextField Classtf, JComboBox<String> Gendercbb) {
        this.SearchBooktf = SearchBooktf;
        this.ReaderNametf = ReaderNametf;
        this.Phonetf = Phonetf;
        this.Emailtf = Emailtf;
        this.Classtf = Classtf;
        this.Gendercbb = Gendercbb;
        this.ReaderIDtf = ReaderIDtf;
    }

    public JTextField getSearchBooktf() {
        return SearchBooktf;
    }

    public JTextField getReaderNametf() {
        return ReaderNametf;
    }

    public JTextField getPhonetf() {
        return Phonetf;
    }

//    public JTextField getGendertf() {
//        return Gendertf;
//    }
    public JComboBox<String> getGendercbb() {
        return Gendercbb;
    }

    public JTextField getEmailtf() {
        return Emailtf;
    }

    public void setReaderIDtf(JTextField ReaderIDtf) {
        this.ReaderIDtf = ReaderIDtf;
    }

    public JTextField getReaderIDtf() {
        return ReaderIDtf;
    }

    public JTextField getClasstf() {
        return Classtf;
    }

    public void addAddBtnListener(ActionListener listener) {
        AddBtn.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void ReadList(List<ReaderModel> readerList) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getReaderid() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
