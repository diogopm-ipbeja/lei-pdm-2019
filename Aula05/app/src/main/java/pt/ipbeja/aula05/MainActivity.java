package pt.ipbeja.aula05;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.ipbeja.aula05.db.AppDatabase;
import pt.ipbeja.aula05.db.Todo;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private TodoAdapter adapter;

    private TextView notesHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.notesHint = findViewById(R.id.notes_hint);

        this.list = findViewById(R.id.list);
        // O LayoutManager desta RecyclerView foi definido no XML (ver atributo layoutManager na RecyclerView em res.layout.activity_main)
        this.adapter = new TodoAdapter();
        this.list.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Este método é invocado cada vez que esta Activity volta a aparecer
        // É uma boa altura para ir buscar todas a notas à BD e actualizar a lista
        List<Todo> todoList = AppDatabase.getInstance(this).todoDao().getAll();
        adapter.setData(todoList); // ver o método setData

        notesHint.setVisibility(todoList.isEmpty() ? View.VISIBLE : View.INVISIBLE);


        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        Task<QuerySnapshot> notes = firestore.collection("notes").get();
        notes.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot result = task.getResult();
                for (DocumentSnapshot document : result.getDocuments()) {

                    Todo todo = document.toObject(Todo.class);
                    System.out.println(todo);

                }

            }
        });

        /*
        Map<String, Object> map = new HashMap<>();
        map.put("title", "teste 1");
        map.put("date", System.currentTimeMillis());
        Task<DocumentReference> add = firestore.collection("notes").add(map);
        */


    }


    public void onCreateTodoClicked(View view) {
        Intent starter = new Intent(this, CreateTodoActivity.class);
        startActivity(starter);
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        private Todo todo;

        private TextView title;
        private TextView description;
        private CheckBox done;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.todo_title);
            description = itemView.findViewById(R.id.todo_description);
            done = itemView.findViewById(R.id.todo_checkbox);

            // Podemos adicionar um listener à CheckBox
            done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    // Cada vez que a checkbox muda de valor, este método é invocado com o valor actual da checkbox (parâmetro 'b')

                    // Com isto, podemos actualizar o modelo de dados
                    todo.setDone(b);

                    // E actualizar o mesmo na base de dados
                    AppDatabase.getInstance(MainActivity.this).todoDao().update(todo);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Ao pressionar num dos itens da lista, queremos ver o detalhe desse item
                    // Ao contrário de exercícios anteriores, não vamos passar todos os dados com os métodos extra do Intent
                    // vamos apenas passar o identificador único e a outra Activity tratará de ir buscar o item em questão à BD
                    // (ver onCreate em TodoDetailActivity)

                    Intent starter = new Intent(MainActivity.this, TodoDetailActivity.class);
                    starter.putExtra(TodoDetailActivity.TODO_ID_EXTRA, todo.getId()); // Passamos o id da nota no Intent (ver TodoDetailActivity#onCreate)
                    MainActivity.this.startActivity(starter);
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Delete note")
                            .setMessage("Are you sure you want to delete this note?\nYou can't undo this action.")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() { // Um ClickListener para a acção positiva
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // Só se o utilizar pressionar "Delete" é que apagamos o registo da BD
                                    AppDatabase.getInstance(MainActivity.this).todoDao().delete(todo);
                                    // TODO Execute a aplicação e tente eliminar uma das Notas da lista
                                    //  Verá que a nota continua na lista apesar de ter confirmado a sua eliminação
                                    //  Verifique que ao tentar aceder ao detalhe dessa nota a aplicação crasha (porquê?)
                                    //  Corrija o código para que a lista reflita esta alteração
                                }
                            })
                            .setNegativeButton("Cancel", null) // Para a acção negativa, podemos passar null pois não queremos fazer nada com o registo
                            .show(); // No final mostramos o AlertDialog


                    return true; // Nos OnLongClickListeners temos de devolver true se tratámos o evento
                }
            });

        }

        void bind(Todo todo) {
            this.todo = todo;
            this.title.setText(todo.getTitle());
            this.description.setText(todo.getDescription());
            this.done.setChecked(todo.isDone());
        }
    }


    class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {

        private List<Todo> data = new ArrayList<>();

        public void setData(List<Todo> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new TodoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
            Todo todo = data.get(position);
            holder.bind(todo);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }


}













