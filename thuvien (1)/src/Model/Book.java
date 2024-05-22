/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Windows
 */
public class Book {
    private int bookId;
    private String bookName;
    private String language;
    private int price;
    private int quantity;
    private String year;
    private String category;
    
    private String publisher;
    public Book(){
        
    }

    public Book(int bookId, String bookName, String language, int price, int quantity, String category, String publisher, String year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.language = language;
        this.price = price;
        this.quantity = quantity;
        this.year = year;
        this.category = category;
//        this.author = author;
        this.publisher = publisher;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

//    public String getAuthor() {
//        return author;
//    }

//    public void setAuthor(String author) {
//        this.author = author;
//    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    
}
