/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import javax.swing.JComboBox;

/**
 *
 * @author Hoa
 */
public class CardModel {
    private int ID,IDDocGia;
    private Date NgayDangKy, NgayHetHan;
    private String data;
    
    public CardModel(){
    }

    public CardModel(int ID, int IDDocGia, Date NgayDangKy, Date NgayHetHan) {
        this.ID = ID;
        this.IDDocGia = IDDocGia;
        this.NgayDangKy = NgayDangKy;
        this.NgayHetHan = NgayHetHan;
        
    }

  

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDDocGia() {
        return IDDocGia;
    }

    public void setIDDocGia(int IDDocGia) {
        this.IDDocGia = IDDocGia;
    }

    public Date getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(Date NgayDangKy) {
        this.NgayDangKy = NgayDangKy;
    }

    public Date getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(Date NgayHetHan) {
        this.NgayHetHan = NgayHetHan;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
    
}