/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnDangNhap, btnThoat;
    
    public LoginView() {
        setTitle("Đăng nhập tài khoản");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel header = new JLabel("Đăng nhập tài khoản");
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel userLabel = new JLabel("Tài khoản:");
        txtUser = new JTextField();
        JLabel passLabel = new JLabel("Mật khẩu:");
        txtPass = new JPasswordField();
        btnDangNhap = new JButton("Đăng nhập");
        btnThoat = new JButton("Thoát");
        
        inputPanel.add(userLabel); inputPanel.add(txtUser);
        inputPanel.add(passLabel); inputPanel.add(txtPass);
        
        buttonPanel.add(btnDangNhap);
        buttonPanel.add(btnThoat);
        
        panel.add(header, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(panel);
        setVisible(true);
    }

    public String getUser() {
        return txtUser.getText();
    }
    
    public String getPass() {
        return new String (txtPass.getPassword());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public void addDangNhapListioner(ActionListener listener) {
        btnDangNhap.addActionListener(listener);
    }
    
    public void addThoatListioner(ActionListener listener) {
        btnThoat.addActionListener(listener);
    }
    
    public void displaySai(String errorMess) {
        JOptionPane.showMessageDialog(this, errorMess, "Đăng nhập lỗi!", JOptionPane.ERROR_MESSAGE);
    }
}
