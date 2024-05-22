/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;


import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.TypeCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import Model.Book;
import controller.BTLNOP;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


  import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Windows
 */
public class BookView extends JFrame implements ActionListener {

    private JButton AddBtn, EditBtn, DeleteBtn, SaveBtn, ClearBtn, BackBtn, SearchBtn, ExcelBtn;
    private JScrollPane jscrollPaneTable;
    private JTable Table;
    private JComboBox Sortcbb, Categorycbb;
    private JTextField SearchBooktf, BookNametf, Quantitytf, Pricetf, BookIDtf, Publishertf, Languagetf, Yeartf;
    private JLabel BookNamelbl, Pricelbl, Quantitylbl, Yearlbl, Categorylbl, Publisherlbl, Languagelbl;
    private DefaultTableModel tblModel;
    private final String[] columnNames = new String[]{"Mã sách", "Tên sách", "Ngôn ngữ", "Giá tiền", "Số lượng", "Thể loại", "Nhà xuất bản", "Năm xuất bản"};
    private final Object data = new Object[][]{};
    private List<Book> listBook = new ArrayList<>();

    int bookId;

    public BookView() {
        initComponents();
    }

    private void initComponents() {
        jscrollPaneTable = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BookNamelbl = new JLabel("Tên sách");
        Languagelbl = new JLabel("Ngôn ngữ");
        Pricelbl = new JLabel("Giá");
        Quantitylbl = new JLabel("Số lượng");
        Yearlbl = new JLabel("Năm xuất bản");
        Categorylbl = new JLabel("Thể loại");
        Publisherlbl = new JLabel("Nhà xuất bản");

        BookNametf = new JTextField(10);
        SearchBooktf = new JTextField(15);
        Quantitytf = new JTextField(10);
        Pricetf = new JTextField(10);
        Pricetf.setText("0");
        BookIDtf = new JTextField(10);
        Publishertf = new JTextField(10);
        Languagetf = new JTextField(10);
        Yeartf = new JTextField(10);

        Categorycbb = new JComboBox<>(new String[]{"Giáo trình", "Tài liệu tham khảo", "Luận án", "Luận văn", "Sách bài tập"});
        Sortcbb = new JComboBox<>(new String[]{"Sắp xếp", "Giảm dần theo giá", "Tăng dần theo giá"});
        AddBtn = new JButton("Thêm");
        EditBtn = new JButton("Sửa");
        DeleteBtn = new JButton("Xoá");
        SaveBtn = new JButton("Lưu");
        SearchBtn = new JButton("Tìm kiếm");
        ClearBtn = new JButton("Nhập lại");
        BackBtn = new JButton("Quay lại");
        ExcelBtn = new JButton("Xuất excel");
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

//        panel.add(BookNamelbl);
//        panel.add(Pricelbl);
//        panel.add(Quantitylbl);
//        panel.add(Yearlbl);
//        panel.add(Categorylbl);
////        panel.add(Authorlbl);
//        panel.add(Publisherlbl);
//        panel.add(Languagelbl);
        panel.add(BookNamelbl);
        panel.add(Pricelbl);
        panel.add(Quantitylbl);
        panel.add(Yearlbl);
        panel.add(Categorylbl);
        panel.add(Publisherlbl);
        panel.add(Languagelbl);
        panel.add(BookNametf);
        panel.add(Pricetf);
        panel.add(Quantitytf);
        panel.add(Yeartf);
        panel.add(Categorycbb);
//        panel.add(Authortf);
        panel.add(Publishertf);
        panel.add(Languagetf);

        panel.add(AddBtn);
        panel.add(EditBtn);
        panel.add(DeleteBtn);
        panel.add(SaveBtn);
        panel.add(SearchBooktf);
        panel.add(SearchBtn);
        panel.add(Sortcbb);
        panel.add(ClearBtn);
        panel.add(BackBtn);
        panel.add(ExcelBtn);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Sử dụng BoxLayout để các nút được sắp xếp theo chiều ngang

        // Thêm các nút vào panel
        buttonPanel.add(AddBtn);
        buttonPanel.add(Box.createHorizontalStrut(30)); // Tạo một khoảng cách ngang 30 pixel giữa các nút
        buttonPanel.add(EditBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(SaveBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(DeleteBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(ClearBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(BackBtn);    
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(ExcelBtn);

        // Thêm panel chứa các nút vào phần dưới cùng của panel chính
        panel.add(buttonPanel);
        //Bang sách

        layout.putConstraint(SpringLayout.WEST, Sortcbb, 315, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Sortcbb, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SearchBooktf, 180, SpringLayout.WEST, Sortcbb);
        layout.putConstraint(SpringLayout.NORTH, SearchBooktf, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SearchBtn, 180, SpringLayout.WEST, SearchBooktf);
        layout.putConstraint(SpringLayout.NORTH, SearchBtn, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jscrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jscrollPaneTable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, jscrollPaneTable, -19, SpringLayout.EAST, panel);
        //cot 1
        layout.putConstraint(SpringLayout.WEST, BookNamelbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, BookNamelbl, 20, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, BookNametf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, BookNametf, 20, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, Languagelbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Languagelbl, 60, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Languagetf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Languagetf, 60, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, Pricelbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Pricelbl, 100, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Pricetf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Pricetf, 100, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, Quantitylbl, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Quantitylbl, 140, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Quantitytf, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Quantitytf, 140, SpringLayout.SOUTH, jscrollPaneTable);
        //cot2
        layout.putConstraint(SpringLayout.WEST, Categorylbl, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Categorylbl, 20, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Categorycbb, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Categorycbb, 20, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, Publisherlbl, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Publisherlbl, 60, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Publishertf, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Publishertf, 60, SpringLayout.SOUTH, jscrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, Yearlbl, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Yearlbl, 100, SpringLayout.SOUTH, jscrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Yeartf, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Yeartf, 100, SpringLayout.SOUTH, jscrollPaneTable);
        //nut
        layout.putConstraint(SpringLayout.WEST, buttonPanel, 50, SpringLayout.WEST, panel); // Đặt panel ở phía bên trái của panel chính
        layout.putConstraint(SpringLayout.SOUTH, buttonPanel, -10, SpringLayout.SOUTH, panel); // Đặt panel ở phía dưới cùng của panel chính

        DefaultTableModel model = (DefaultTableModel) ((JTable) jscrollPaneTable.getViewport().getView()).getModel();
       BTLNOP controller = new BTLNOP();
        listBook = controller.getBookList();
        this.BookList(listBook);

        AddBtn.addActionListener(this);
        EditBtn.addActionListener(this);
        SaveBtn.addActionListener(this);
        DeleteBtn.addActionListener(this);
        ClearBtn.addActionListener(this);
        BackBtn.addActionListener(this);
        controller.setButtonListener(AddBtn, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Vào được");
                String bookName = BookNametf.getText();
                String language = Languagetf.getText();
                int price = Integer.parseInt(Pricetf.getText());
                int quantity = Integer.parseInt(Quantitytf.getText());
                String category = (String) Categorycbb.getSelectedItem().toString(); // Lấy mục đã chọn từ combobox
                String publisher = Publishertf.getText();
                String year = Yeartf.getText();
//                                String category = (String)getCategorycbb().getSelectedItem().toString(); // Lấy mục đã chọn từ combobox
//                                String publisher = getPublishertf().getText();
                //
                //            // Tạo một đối tượng Book
                Book book = new Book(0, bookName, language, price, quantity, category, publisher, year);
                controller.addBook(book);
                JOptionPane.showMessageDialog(null, "Sách đã được thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                listBook = controller.getBookList();
                BookList(listBook);
                clearFields();
            }
        });

        controller.setButtonListener(DeleteBtn, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Table.getSelectedRow();
                if (selectedRow == -1) {
                    // Nếu không có hàng nào được chọn, hiển thị thông báo lỗi
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một cuốn sách để xóa", "Lỗi Xóa", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int bookId = (int) Table.getValueAt(selectedRow, 0);
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa cuốn sách này không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        controller.deleteBook(bookId);
                    } catch (SQLException ex) {
                        Logger.getLogger(BookView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    listBook = controller.getBookList();
                    BookList(listBook);

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

            // Lấy dữ liệu từ bảng và đặt vào các JLabel
            String Name = (String) Table.getValueAt(selectedRow, 1);
            String language = (String) Table.getValueAt(selectedRow, 2);
            int priceStr = (int) Table.getValueAt(selectedRow, 3);
            int quantityStr = (int) Table.getValueAt(selectedRow, 4);
            String category = (String) Table.getValueAt(selectedRow, 5);
            String publisher = (String) Table.getValueAt(selectedRow, 6);
            String year = (String) Table.getValueAt(selectedRow, 7);
            BookNametf.setText(Name);
            Languagetf.setText(language);
            Pricetf.setText(String.valueOf(priceStr)); // Chuyển đổi số nguyên thành chuỗi
            Quantitytf.setText(String.valueOf(quantityStr));
            Categorycbb.setSelectedItem(category);
            Publishertf.setText(publisher);
            Yeartf.setText(year);
            bookId = (int) Table.getValueAt(selectedRow, 0);

            // Ẩn nút "add"
            AddBtn.setEnabled(false);
        }
    }
});

        controller.setButtonListener(SaveBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra xem người dùng đã nhập đầy đủ thông tin hay chưa
                String bookName = BookNametf.getText();
                String language = Languagetf.getText();
                String priceStr = Pricetf.getText();
                String quantityStr = Quantitytf.getText();
                String category = (String) Categorycbb.getSelectedItem();
                String publisher = Publishertf.getText();
                String year = Yeartf.getText();

                if (bookName.isEmpty() || language.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()
                        || publisher.isEmpty() || year.isEmpty()) {
                    // Nếu có một trường nào đó chưa được điền, hiển thị thông báo và không thực hiện lưu
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin cho cuốn sách.");
                    return;
                }

                try {
                    // Chuyển đổi dữ liệu về số nguyên
                    int price = Integer.parseInt(priceStr);
                    int quantity = Integer.parseInt(quantityStr);

                    // Kiểm tra xem có cuốn sách nào được chọn không
                    int selectedRow = Table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một cuốn sách để chỉnh sửa.");
                        return;
                    }

                    // Lấy ID của cuốn sách được chọn
                    // Gọi phương thức trong controller để cập nhật thông tin của cuốn sách
                    Book book = new Book(bookId, bookName, language, price, quantity, category, publisher, year);
                    controller.updateBook(book);

                    // Cập nhật lại dữ liệu trong bảng sau khi lưu thành công
                    listBook = controller.getBookList();
                    BookList(listBook);
                } catch (NumberFormatException ex) {
                    // Xử lý nếu có lỗi khi chuyển đổi dữ liệu về số nguyên
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số cho giá và số lượng.");
                } catch (SQLException ex) {
                    Logger.getLogger(BookView.class.getName()).log(Level.SEVERE, null, ex);
                }
                AddBtn.setEnabled(true);
                clearFields();
            }
        });
        Sortcbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSortOption = (String) Sortcbb.getSelectedItem();
                switch (selectedSortOption) {
                    case "Tăng dần theo giá":
                        Collections.sort(listBook, Comparator.comparingInt(Book::getPrice));
                        break;
                    case "Giảm dần theo giá":
                        Collections.sort(listBook, Comparator.comparingInt(Book::getPrice).reversed());
                        break;
                    case "Sắp xếp":
                        listBook = controller.getBookList();
                        BookList(listBook);
                }
                // Cập nhật hiển thị của bảng
                BookList(listBook);
            }
        });
        SearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = SearchBooktf.getText().trim(); // Lấy từ khóa tìm kiếm từ JTextField và loại bỏ dấu cách thừa

                // Tạo một danh sách tạm thời để lưu kết quả tìm kiếm
                List<Book> searchResult = new ArrayList<>();

                // Duyệt qua danh sách sách hiện tại và kiểm tra xem từ khóa tìm kiếm có trong sách hay không
                for (Book book : listBook) {
                    if (book.getBookName().toLowerCase().contains(keyword)
                            || book.getLanguage().toLowerCase().contains(keyword)
                            || String.valueOf(book.getPrice()).contains(keyword)
                            || String.valueOf(book.getQuantity()).contains(keyword)
                            || book.getCategory().toLowerCase().contains(keyword)
                            || book.getPublisher().toLowerCase().contains(keyword)
                            || String.valueOf(book.getBookId()).contains(keyword)
                            || book.getYear().contains(keyword)) {
                        // Nếu tìm thấy từ khóa trong tên sách, thêm sách vào danh sách kết quả
                        searchResult.add(book);
                    }
                }

                // Hiển thị kết quả tìm kiếm trên bảng
                BookList(searchResult);
                clearFields();
            }
        });

        ClearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBtn.setEnabled(true);
                clearFields();
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
                cell.setCellValue("Tên sách");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Loại");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Ngôn ngữ");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Giá");
 
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Số lượng");
                
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Nhà xuất bản");
                
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Năm xuất bản");

               List<Book> list1 = controller.getBookList();

                if (list1 != null) {
                    FileOutputStream fos = null;
                    try {
                        int s = list1.size();
                        for (int i = 0; i < s; i++) {
                            Book b = list1.get(i);
                            row = sheet.createRow(1 + i);

                            cell = row.createCell(0, CellType.STRING);
                            cell.setCellValue(b.getBookId());

                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(b.getBookName());

                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(b.getCategory());

                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(b.getLanguage());

                            cell = row.createCell(4, CellType.STRING);
                            cell.setCellValue(b.getPrice());

                            cell = row.createCell(5, CellType.STRING);
                            cell.setCellValue(b.getQuantity());
                            
                            cell = row.createCell(6, CellType.STRING);
                            cell.setCellValue(b.getPublisher());
                            
                            cell = row.createCell(7, CellType.STRING);
                            cell.setCellValue(b.getYear());
                        }
                        File f = new File("D:\\booklist.xlsx");
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
        setTitle("Quản lý sách");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void BookList(List<Book> BookList) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) jscrollPaneTable.getViewport().getView()).getModel();
        model.setRowCount(0);
        for (Book book : BookList) {
            model.addRow(new Object[]{
                book.getBookId(),
                book.getBookName(),
                book.getLanguage(),
                book.getPrice(), book.getQuantity(), book.getCategory(), book.getPublisher(), book.getYear()

            });
        }
    }

    public void clearFields() {
        BookNametf.setText("");
        SearchBooktf.setText("");
        Quantitytf.setText("");
        Pricetf.setText("");
        Pricetf.setText("0");
        BookIDtf.setText("");
        Publishertf.setText("");
        Languagetf.setText("");
        Yeartf.setText("");
    }

    public void addAddBtnListener(ActionListener listener) {
        AddBtn.addActionListener(listener);
    }

    public BookView(JTextField SearchBooktf, JTextField BookNametf, JTextField Quantitytf, JTextField Pricetf, JTextField BookIDtf, JTextField Publishertf, JTextField Languagetf, JTextField Yeartf) {
        this.SearchBooktf = SearchBooktf;
        this.BookNametf = BookNametf;
        this.Quantitytf = Quantitytf;
        this.Pricetf = Pricetf;
        this.BookIDtf = BookIDtf;
        this.Publishertf = Publishertf;
        this.Languagetf = Languagetf;
        this.Yeartf = Yeartf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
