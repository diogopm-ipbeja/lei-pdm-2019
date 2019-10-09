package pt.ipbeja.aula04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pt.ipbeja.aula04.data.Contact;
import pt.ipbeja.aula04.data.ContactDataSource;

@SuppressWarnings({"SpellCheckingInspection"})
public class ContactListActivity extends AppCompatActivity {

    private RecyclerView list;
    private ContactAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        list = findViewById(R.id.list);
        this.adapter = new ContactAdapter();

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        list.setAdapter(adapter);
        list.setLayoutManager(lm); // Alternativamente, também é possível atribuir um LayoutManager à RecyclerView a partir do XML (por ex: app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager")

        // TODO 1) Permitir pressionar um item da lista e aceder a uma Activity de detalhe do mesmo
        // TODO 2) Eliminar items com clique longo (ver OnLongClickListener) com confirmação do utilizador (ver AlertDialog [https://medium.com/@suragch/making-an-alertdialog-in-android-2045381e2edb]) e feedback da acção (ver SnackBar [https://material.io/develop/android/components/snackbar/])
        // TODO 3) Criar uma Activity para criar Contacts. Quando volta a esta Activity deve mostrar a lista actualizada com o novo Contact
        //          Altere o Contact para (além dos atributos existentes) ter o sexo, data de nascimento e uma fotografia (Bitmap)
        //          Para seleccionar o sexo do contacto deve utilizar RadioButtons (+ RadioGroup)
        //          Para seleccionar a data de nascimento deve utilizar um DatePicker
    }

    public void onAddContactClicked(View view) {
        Contact c = ContactDataSource.createRandomContact();

        this.adapter.contacts.add(c); // Adicionamos o contact à fonte de dados do Adapter
        int lastPosition = this.adapter.contacts.size() - 1;
        this.adapter.notifyItemInserted(lastPosition); // Notificamos o Adapter que foi inserido um novo item na dada posição

        this.list.smoothScrollToPosition(lastPosition); // Podemos ainda pedir à RecyclerView para fazer scroll até ao novo item
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        private Contact contact;

        private TextView name;
        private TextView email;
        private ImageView photo;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.email = itemView.findViewById(R.id.email);
            this.photo = itemView.findViewById(R.id.photo);

            // Os ClickListeners devem ser criados no construtor do ViewHolder e não no onBindViewHolder
            // Isso evita que para cada item que entre em cena se crie um novo Listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition(); // Podemos saber a posição do item clickado
                    // E também temos acesso ao modelo que suporta este ViewHolder (o Contact)
                    String text = String.format(Locale.getDefault(), "#%d %s clicked", position, contact.getName());

                    // Um Snackbar é um pequeno popup que podemos usar para feedback (experimente correr a aplicação e clickar num dos contactos da lista)
                    Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
                }
            });

            // TODO itemView.setOnLongClickListener ... delete!

        }

        public void bind(Contact contact) {
            // Neste método preenchemos os widgets
            this.contact = contact;
            this.name.setText(contact.getName());
            this.email.setText(contact.getEmail());
        }
    }


    class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

        private List<Contact> contacts = new ArrayList<>();

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Este método é invocado um número limitado de vezes
            // A partir do momento que existem ViewHolders suficientes na "reciclagem", deixa de ser invocado

            // Criar a View do item da lista a partir de um ficheiro de layout
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
            // Criar e devolver o ViewHolder com essa View
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            // Neste método preenchemos o ViewHolder com a informação do Contact

            // Com a position podemos ir buscar o contacto à lista e usá-lo para preencher a View
            holder.bind(contacts.get(position));
        }

        @Override
        public int getItemCount() {
            // O Adapter precisa de saber quantos items estão na fonte de dados
            return contacts.size();
        }
    }

}
