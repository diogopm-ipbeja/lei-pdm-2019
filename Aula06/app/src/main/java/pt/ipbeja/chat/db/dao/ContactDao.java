package pt.ipbeja.chat.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import pt.ipbeja.chat.db.entity.Contact;
import pt.ipbeja.chat.db.entity.ContactWithMessages;

@Dao
public abstract class ContactDao implements BaseDao<Contact> {

    @Query("select * from contact")
    public abstract List<Contact> getAll();

    /*
    @Query("select * from contact order by name desc")
    public abstract List<Contact> getAllByName(String sort);
    */

    @Query("select * from contact where id = :contactId")
    public abstract Contact get(long contactId);


    @Query("select * from contact")
    public abstract LiveData<List<Contact>> getAllLiveData();

    @Query("select * from contact where id = :contactId")
    public abstract ContactWithMessages getWithMessages(long contactId);

}
