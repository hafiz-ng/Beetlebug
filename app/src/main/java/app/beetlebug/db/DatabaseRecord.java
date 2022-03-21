package app.beetlebug.db;

import app.beetlebug.utils.Configuration;

public class DatabaseRecord {
    private int id;
    private String title;
    private String author;
    private Configuration config = new Configuration();

    public DatabaseRecord() {

    }

    public DatabaseRecord(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (new String("").equals(title)) {
            title = config.default_title_database_item;
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (new String("").equals(author)) {
            author = config.default_author_database_item;
        }
        this.author = author;
    }

    @Override
    public String toString() {
        return "Record [id=" + id + ", title=" + title + ", author=" + author
                + "]";
    }
}
