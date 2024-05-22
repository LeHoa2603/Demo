/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.framemain;
import Model.LoginModel;
import View.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hoa
 */
public class LoginController {

    private LoginView view;
    private LoginModel model;

    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
        this.view.addDangNhapListioner(new dangNhapListioner());
        this.view.addThoatListioner(new thoatsListener());
    }

    class dangNhapListioner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String user = view.getUser();
            String pass = view.getPass();

            if (model.kiemTra(user, pass)) {
                JOptionPane.showMessageDialog(view, "Đăng nhập thành công!");
                 framemain framemain = new framemain();
                view.setVisible(false);
            } else {
                view.displaySai("Sai tài khoản hoặc mật khẩu!");
            }
        }

    }

    class thoatsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // SanCauFrm arf = new SanCauFrm();
        // arf.setVisible(true);
        LoginView view = new LoginView();
        LoginModel model = new LoginModel();
        LoginController controller = new LoginController(view, model);
        view.setVisible(true);
    }
}
