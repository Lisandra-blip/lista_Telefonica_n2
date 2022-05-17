package br.univali.contacts;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String phone;

    public Contact() {

    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
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

    public static class Comparator implements java.util.Comparator<Contact> {
        @Override
        public int compare(Contact a, Contact b) {
            return a.name.compareTo(b.name);
        }
    }
}

