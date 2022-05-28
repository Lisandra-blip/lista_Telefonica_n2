package br.univali.contacts;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> findAll();
}
