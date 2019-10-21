package pt.ipbeja.chat.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

// TODO: Anotar esta class como Base de Dados (não esquecer os atributos obrigatórios 'entities' e
//  'version')
public abstract class ChatDatabase extends RoomDatabase {

    private static ChatDatabase INSTANCE = null;

    public static ChatDatabase getInstance(Context context) {
        context = context.getApplicationContext();

        // TODO: Completar o padrão Singleton para obter a instância da Base de Dados (garantir que
        //  só há uma instância desta class)
        Room.databaseBuilder(context, ChatDatabase.class, "chat-db")
                .allowMainThreadQueries()
                .build();

        return INSTANCE;
    }

    // TODO: Adicionar os métodos abstratos para obter as instâncias dos DAO (ContactDao e
    //  MessageDao)
}
