package com.cyb.amqp.bean;

/**
 * @author cyb
 * @date 2018/12/15 - 16:54
 */
public class Book {

    private String bookName;
    private String author;

    public Book () {
    }

    public Book (String bookName, String author) {

        this.bookName = bookName;
        this.author = author;
    }

    public String getBookName () {
        return bookName;
    }

    public void setBookName (String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    @Override
    public String toString () {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
