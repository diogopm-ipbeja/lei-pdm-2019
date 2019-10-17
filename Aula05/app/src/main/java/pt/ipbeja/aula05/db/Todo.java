package pt.ipbeja.aula05.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "todo")
// Temos de anotar cada um dos modelos que servirá de template para uma tabela da BD. O atributo tableName é opcional.
public class Todo {

    @PrimaryKey(autoGenerate = true)
    // Devemos anotar o atributo que servirá de chave e - tipicamente necessário - que este id seja autogerado
    private long id;

    // Por defeito, os nomes das colunas serão os mesmos dos atributos
    private String title;
    // Mas podemos definir nomes diferentes, por ex:
    // @ColumnInfo(name = "todo_description")
    private String description;
    private boolean done;

    // Deve existir um construtor com as entradas para cada uma das colunas (será usado internamente pelo Room)
    public Todo(long id, String title, String description, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
    }

    // Podemos no entanto ter outros construtores desde que estejam anotados com @Ignore (para o Room ignorar este construtor)
    @Ignore
    // Este tipo de construtores são convenientes para evitar estar sempre a passar o id = 0 sempre que queremos instanciar um novo objecto (ver abaixo)
    public Todo(String title, String description, boolean done) {
        this.id = 0; // Quando queremos que um id seja gerado automaticamente, podemos passar o valor 0 e o Room saberá que deve gerar um por nós
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
