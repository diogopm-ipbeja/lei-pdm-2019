package pt.ipbeja.aula05.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// O Dao é uma camada de abstração de acesso aos dados. A biblioteca Room irá gerar código com base nos métodos e queries que aqui definirmos.
// Tipicamente haverá um DAO por tabela, mas não necessariamente. Um DAO pode lidar com mais que uma tabela se tal for necessário
@Dao
public interface TodoDao { // Pode ser uma interface ou uma class abstracta

    // Temos de anotar cada um dos métodos com a acção pretendida

    @Insert
    long insert(Todo todo); // Devolve long (o identificador atribuido ao registo)

    @Delete
    int delete(Todo todo); // int é o número de registos afectados

    @Update
    int update(Todo todo); // int é o número de registos afectados

    // Para queries select ou, por exemplo um Delete que não se baseie só no ID do registo, temos a anotação @Query
    @Query("select * from todo")
    List<Todo> getAll();

    // Para queries que dependem de parâmetros, definimos esses parâmetros no método e referimo-nos a esses parâmetros colocando ':' atrás do nome
    @Query("select * from todo where id = :todoId")
    Todo get(long todoId);

}
