package pt.ipbeja.chat.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pt.ipbeja.chat.db.dao.ContactDao;
import pt.ipbeja.chat.db.dao.MessageDao;
import pt.ipbeja.chat.db.entity.ChatMessage;
import pt.ipbeja.chat.db.entity.Contact;

@Database(entities = {Contact.class, ChatMessage.class}, version =4)
public abstract class ChatDatabase extends RoomDatabase {

    private static ChatDatabase INSTANCE = null;

    public static ChatDatabase getInstance(final Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ChatDatabase.class, "chat-db")
                    .fallbackToDestructiveMigration() // Recria a BD (destruindo os dados) quando a versão muda
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract ContactDao contactDao();

    public abstract MessageDao messageDao();
}
