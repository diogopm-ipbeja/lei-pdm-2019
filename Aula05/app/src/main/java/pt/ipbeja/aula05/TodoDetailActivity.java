package pt.ipbeja.aula05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import pt.ipbeja.aula05.db.AppDatabase;
import pt.ipbeja.aula05.db.Todo;

public class TodoDetailActivity extends AppCompatActivity {

    private TextView identifier;
    private TextView title;
    private TextView description;
    private CheckBox done;

    public static final String TODO_ID_EXTRA = "todo_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        this.identifier = findViewById(R.id.todo_id);
        this.title = findViewById(R.id.todo_title);
        this.description = findViewById(R.id.todo_description);
        this.done = findViewById(R.id.todo_done);


        // O identificador vem nos Extras
        long todoId = getIntent().getLongExtra(TODO_ID_EXTRA, 0);

        // Com esse identificador, vamos buscar o modelo à BD
        Todo todo = AppDatabase.getInstance(this).todoDao().get(todoId);

        // Depois preenchemos as TextViews com os dados do modelo
        this.identifier.setText(todo.getId() + "");
        this.title.setText(todo.getTitle());
        this.description.setText(todo.getDescription());
        this.done.setChecked(todo.isDone());

        // TODO Adicione um OnCheckedChangedListener à CheckBox e permita que o utilizador marque esta tarefa como feita a partir desta Activity (actualizar o modelo na BD)
    }
}