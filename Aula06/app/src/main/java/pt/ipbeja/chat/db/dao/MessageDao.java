package pt.ipbeja.chat.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import pt.ipbeja.chat.db.entity.ChatMessage;

@Dao
public abstract class MessageDao implements BaseDao<ChatMessage> {

    @Query("select * from message where contactId = :contactId")
    public abstract List<ChatMessage> getAll(long contactId);

    // TODO devolver a última mensagem enviada/recebida do contacto (ver ContactDetailActivity)
    //public abstract String getLastMessage(long contactId);

    // TODO devolver o número de mensagens trocadas com o contacto (ver ContactDetailActivity)
    //public abstract int getMessageCount(long contactId);

}
