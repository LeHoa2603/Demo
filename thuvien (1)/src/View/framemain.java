package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import View.BookView;
import View.CardView;
import View.Phieu_muon_view;
import View.Phieu_tra_view;
import View.View;
//import controller.Btl_thu_vien;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class framemain {
    private static JLabel danhmuc = new JLabel("DANH MỤC");
    private static View readerview;
    private static BookView bookView;
    private static CardView cardView;
    private static Phieu_muon_view phieumuon;
    private static Phieu_tra_view phieutra;

    public framemain() {
        // Tạo frame chính
        JFrame mainFrame = new JFrame("Main Frame");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);
        
        // Tạo panel chứa các nút
        JPanel buttonPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        buttonPanel.setLayout(layout);

        // Thêm nút mở frame phụ 1 (Manager Book)
        JButton openBookViewButton = new JButton(" Manager Book");
        openBookViewButton.setPreferredSize(new Dimension(300, 50));
        openBookViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bookView == null) {
                    bookView = new BookView(); // Tạo mới bookView nếu chưa được tạo
                }
                // Hiển thị hoặc ẩn BookView tùy thuộc vào trạng thái của nó
                if (!bookView.isVisible()) {
                    bookView.setVisible(true);
                } else {
                    bookView.setVisible(false);
                }
            }
        });
        buttonPanel.add(openBookViewButton);

        // Thêm nút mở frame phụ 2 (Manager Reader)
        JButton openAnotherFrameButton = new JButton("Manager Reader");
        openAnotherFrameButton.setPreferredSize(new Dimension(300, 50));
        openAnotherFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (readerview == null) {
                    readerview = new View(); // Tạo mới readerview nếu chưa được tạo
                }
                // Hiển thị hoặc ẩn readerview tùy thuộc vào trạng thái của nó
                if (!readerview.isVisible()) {
                    readerview.setVisible(true);
                } else {
                    readerview.setVisible(false);
                }
            }
        });
        buttonPanel.add(openAnotherFrameButton);

        // Thêm nút mở frame phụ 3 (Open Third Frame)
        JButton openThirdFrameButton = new JButton("Manager Card");
        openThirdFrameButton.setPreferredSize(new Dimension(300, 50));
        openThirdFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cardView == null) {
                    cardView = new CardView(); // Tạo mới readerview nếu chưa được tạo
                }
                // Hiển thị hoặc ẩn readerview tùy thuộc vào trạng thái của nó
                if (!cardView.isVisible()) {
                    cardView.setVisible(true);
                } else {
                    cardView.setVisible(false);
                }
            }
        });
        buttonPanel.add(openThirdFrameButton);

        // Thêm nút mở frame phụ 4 (Loan Slip)
        JButton openFourthFrameButton = new JButton("Loan Slip");
        openFourthFrameButton.setPreferredSize(new Dimension(300, 50));
        openFourthFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phieumuon == null) {
                    phieumuon = new Phieu_muon_view(); // Tạo mới readerview nếu chưa được tạo
                }
                // Hiển thị hoặc ẩn readerview tùy thuộc vào trạng thái của nó
                if (!phieumuon.isVisible()) {
                    phieumuon.setVisible(true);
                } else {
                    phieumuon.setVisible(false);
                }
            }
        });
        buttonPanel.add(openFourthFrameButton);

        // Thêm nút mở frame phụ 5 (Payment slip)
        JButton openFifthFrameButton = new JButton("Payment slip");
        openFifthFrameButton.setPreferredSize(new Dimension(300, 50));
        openFifthFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phieutra == null) {
                    try {
                        phieutra = new Phieu_tra_view(); // Tạo mới readerview nếu chưa được tạo
                    } catch (SQLException ex) {
                        Logger.getLogger(framemain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Hiển thị hoặc ẩn readerview tùy thuộc vào trạng thái của nó
                if (!phieutra.isVisible()) {
                    phieutra.setVisible(true);
                } else {
                    phieutra.setVisible(false);
                }
            }
        });
        buttonPanel.add(openFifthFrameButton);
        buttonPanel.add(danhmuc);
        
        // Đặt ràng buộc cho các thành phần trong panel
        layout.putConstraint(SpringLayout.WEST, danhmuc, 220, SpringLayout.WEST, mainFrame);
        layout.putConstraint(SpringLayout.NORTH, danhmuc, 5, SpringLayout.NORTH, mainFrame); 

        layout.putConstraint(SpringLayout.WEST, openBookViewButton, 100, SpringLayout.WEST, mainFrame);
        layout.putConstraint(SpringLayout.NORTH, openBookViewButton, 50, SpringLayout.SOUTH, danhmuc); 

        layout.putConstraint(SpringLayout.WEST, openAnotherFrameButton, 100, SpringLayout.WEST, mainFrame);
        layout.putConstraint(SpringLayout.NORTH, openAnotherFrameButton, 30, SpringLayout.SOUTH, openBookViewButton); 

        layout.putConstraint(SpringLayout.WEST, openThirdFrameButton, 100, SpringLayout.WEST, mainFrame);
        layout.putConstraint(SpringLayout.NORTH, openThirdFrameButton, 30, SpringLayout.SOUTH, openAnotherFrameButton); 

        layout.putConstraint(SpringLayout.WEST, openFourthFrameButton, 100, SpringLayout.WEST, mainFrame);
        layout.putConstraint(SpringLayout.NORTH, openFourthFrameButton, 30, SpringLayout.SOUTH, openThirdFrameButton); 
        
        layout.putConstraint(SpringLayout.WEST, openFifthFrameButton, 100, SpringLayout.WEST, mainFrame);
        layout.putConstraint(SpringLayout.NORTH, openFifthFrameButton, 30, SpringLayout.SOUTH, openFourthFrameButton); 

        mainFrame.add(buttonPanel); // Thêm panel chứa các nút vào frame chính
        mainFrame.setVisible(true);
    }
}
