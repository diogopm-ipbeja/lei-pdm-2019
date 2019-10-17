package pt.ipbeja.aula05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import pt.ipbeja.aula05.db.AppDatabase;
import pt.ipbeja.aula05.db.Todo;
import pt.ipbeja.aula05.db.TodoDao;

public class CreateTodoActivity extends AppCompatActivity {

    private TextInputEditText todoTitle;
    private TextInputEditText todoDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        this.todoTitle = findViewById(R.id.todo_title);
        this.todoDescription = findViewById(R.id.todo_description);

    }

    public void onSaveTodoClicked(View view) {
        // Quando o utilizador carregar no botão de gravar, vamos construir o modelo da nota
        Todo todo = new Todo(todoTitle.getText().toString(), todoDescription.getText().toString(), false);
        // Depois só precisamos de passar esse modelo como parâmetro do método insert
        AppDatabase.getInstance(this).todoDao().insert(todo);
        // No final, podemos terminar esta Activity e voltar à MainActivity
        finish();
    }
}
