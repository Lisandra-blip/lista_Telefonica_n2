package br.univali.contacts;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String phone;

    @ColumnInfo
    private String type;

    public Contact() {

    }

    public Contact(int id, String name, String phone, String type) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Comparator implements java.util.Comparator<Contact> {
        @Override
        public int compare(Contact a, Contact b) {
            return a.name.compareTo(b.name);
        }
    }
}

