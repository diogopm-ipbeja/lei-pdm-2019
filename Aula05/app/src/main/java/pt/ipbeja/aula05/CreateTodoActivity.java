package pt.ipbeja.aula05;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import pt.ipbeja.aula05.db.AppDatabase;
import pt.ipbeja.aula05.db.Todo;

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



        FirebaseFirestore instance = FirebaseFirestore.getInstance();
        instance.collection("notes").add(todo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                finish();
            }
        });
    }
}
