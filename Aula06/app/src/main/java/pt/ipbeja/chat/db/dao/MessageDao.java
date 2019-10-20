package pt.ipbeja.chat.db.dao;

import java.util.List;

import pt.ipbeja.chat.db.entity.ChatMessage;

// TODO: Anotar este DAO (class e métodos)
public abstract class MessageDao implements BaseDao<ChatMessage> {

    public abstract List<ChatMessage> getAll(long contactId);

}
