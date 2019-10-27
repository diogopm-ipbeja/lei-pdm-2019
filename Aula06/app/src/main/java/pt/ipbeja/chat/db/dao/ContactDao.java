package pt.ipbeja.chat.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import pt.ipbeja.chat.db.entity.Contact;

@Dao
public abstract class ContactDao implements BaseDao<Contact> { // Ver BaseDao

    @Query("select * from contact")
    public abstract List<Contact> getAll();

    @Query("select * from contact where id = :contactId")
    public abstract Contact get(long contactId);



}
