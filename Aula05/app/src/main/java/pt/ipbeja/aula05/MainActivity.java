package pt.ipbeja.aula05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.list = findViewById(R.id.list);

        this.adapter = new TodoAdapter();

        this.list.setAdapter(adapter);


    }


    class TodoViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private CheckBox done;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.todo_title);
            description = itemView.findViewById(R.id.todo_description);
            done = itemView.findViewById(R.id.todo_checkbox);

        }
    }


    class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {


        @NonNull
        @Override
        public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_item,
                    parent,
                    false);

            TodoViewHolder viewHolder = new TodoViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


}













