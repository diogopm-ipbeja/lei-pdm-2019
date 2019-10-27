package pt.ipbeja.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pt.ipbeja.chat.db.ChatDatabase;
import pt.ipbeja.chat.db.entity.Contact;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private ContactAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.list = findViewById(R.id.contact_list);

        this.adapter = new ContactAdapter();
        this.list.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Contact> contacts = ChatDatabase.getInstance(this).contactDao().getAll();
        adapter.setContacts(contacts);
    }

    public void onAddContactClicked(View view) {
        CreateContactActivity.start(this);
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {

        private Contact contact;

        private TextView initials;
        private TextView name;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            initials = itemView.findViewById(R.id.contact_initials);

            itemView.setOnClickListener(view -> {
                ChatActivity.start(MainActivity.this, contact.getId());
            });

            itemView.setOnLongClickListener(v -> {

                // TODO: AlertDialog para eliminar o contacto
                //  Eliminar o contacto e as suas mensagens

                return true;
            });

            initials.setOnClickListener(view -> ContactDetailsActivity.start(MainActivity.this, contact.getId()));

        }

        public void bind(Contact contact) {
            this.contact = contact;
            this.name.setText(contact.getName());
            this.initials.setText(contact.getInitials());
        }

    }


    class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

        private List<Contact> contacts = new ArrayList<>();

        public void setContacts(List<Contact> contacts) {
            this.contacts = contacts;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.contact_list_item, parent, false);
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            Contact contact = contacts.get(position);
            holder.bind(contact);

        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }
    }

}














