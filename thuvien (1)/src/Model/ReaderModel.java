/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class ReaderModel {
    private int readerid;
    private String readername;
    private String lop;
    private String gender;
    private String email;
    private int phone;
    
    public ReaderModel() {
    }

    public ReaderModel(int readerid,String readername,String lop, String gender,String email,int phone ) {
        this.readerid = readerid;
        this.readername = readername;
        
        this.lop = lop;
        
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    public String getReadername() {
        return readername;
    }

    public void setReadername(String readername) {
        this.readername = readername;
    }

    public int getReaderid() {
        return readerid;
    }

    public void setReaderid(int readerid) {
        this.readerid = readerid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    
}
