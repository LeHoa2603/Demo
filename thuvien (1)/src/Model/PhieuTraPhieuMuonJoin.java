/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

public class PhieuTraPhieuMuonJoin {
    private int id_phieu_tra;
    private int id_phieu_muon;
    private int bookId;
    private int readerID;
    private int so_luong;
    private String phi_muon;
    private Date ngay_muon;
    private Date ngay_tra;
    private String tinh_trang;

    public PhieuTraPhieuMuonJoin(int id_phieu_muon, int bookId, int readerID, int so_luong, String phi_muon, Date ngay_muon, Date ngay_tra, String tinh_trang) {
        this.id_phieu_muon = id_phieu_muon;
        this.bookId = bookId;
        this.readerID = readerID;
        this.so_luong = so_luong;
        this.phi_muon = phi_muon;
        this.ngay_muon = ngay_muon;
        this.ngay_tra = ngay_tra;
        this.tinh_trang = tinh_trang;
    }

    public PhieuTraPhieuMuonJoin(int id_phieu_tra, int id_phieu_muon, int bookId, int readerID, int so_luong, String phi_muon, Date ngay_muon, Date ngay_tra, String tinh_trang) {
        this.id_phieu_tra = id_phieu_tra;
        this.id_phieu_muon = id_phieu_muon;
        this.bookId = bookId;
        this.readerID = readerID;
        this.so_luong = so_luong;
        this.phi_muon = phi_muon;
        this.ngay_muon = ngay_muon;
        this.ngay_tra = ngay_tra;
        this.tinh_trang = tinh_trang;
    }

   

    // Getters and setters

    public int getId_phieu_tra() {
        return id_phieu_tra;
    }

    public void setId_phieu_tra(int id_phieu_tra) {
        this.id_phieu_tra = id_phieu_tra;
    }

    public int getId_phieu_muon() {
        return id_phieu_muon;
    }

    public void setId_phieu_muon(int id_phieu_muon) {
        this.id_phieu_muon = id_phieu_muon;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public String getPhi_muon() {
        return phi_muon;
    }

    public void setPhi_muon(String phi_muon) {
        this.phi_muon = phi_muon;
    }

    public Date getNgay_muon() {
        return ngay_muon;
    }

    public void setNgay_muon(Date ngay_muon) {
        this.ngay_muon = ngay_muon;
    }

    public Date getNgay_tra() {
        return ngay_tra;
    }

    public void setNgay_tra(Date ngay_tra) {
        this.ngay_tra = ngay_tra;
    }

    public String getTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(String tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

   
    
}
