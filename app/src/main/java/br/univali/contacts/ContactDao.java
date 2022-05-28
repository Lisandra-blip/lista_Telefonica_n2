package br.univali.contacts;

import androidx.room.Query;

import java.util.List;

public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> findAll();
}
