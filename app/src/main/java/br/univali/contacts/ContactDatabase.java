package br.univali.contacts;

import androidx.room.Database;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase {
    public abstract ContactDao contactDao();
}
