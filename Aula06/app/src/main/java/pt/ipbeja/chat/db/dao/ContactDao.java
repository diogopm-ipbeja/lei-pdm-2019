package pt.ipbeja.chat.db.dao;

import java.util.List;

import pt.ipbeja.chat.db.entity.Contact;

// TODO: Anotar este DAO (class e métodos)
public abstract class ContactDao implements BaseDao<Contact> { // Ver BaseDao
    
    public abstract List<Contact> getAll();

    public abstract Contact get(long contactId);

}
