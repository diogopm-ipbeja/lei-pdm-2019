package pt.ipbeja.aula05.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1) // Os atributos entities (não esquecer adicionar aqui sempre que definimos uma nova Entity) e version são obrigatórios
public abstract class AppDatabase extends RoomDatabase { // A class é sempre abstracta e é subclass de RoomDatabase

    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(Context context) {
        // É importante que se aplique o padrão Singleton para evitar que exista mais que uma instância da BD durante a vida da nossa aplicação

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "todo_db") // Deve ser o contexto da app. O nome ("todo_db") é à nossa escolha.
                    .allowMainThreadQueries() // Por agora, vamos permitir que se realizem queries na thread principal (thread da interface gráfica)
                    .build();
        }

        return INSTANCE;
    }

    // Não esquecer de definir um método abstrato para cada um dos DAOs definidos
    public abstract TodoDao todoDao();
}
