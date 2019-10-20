package pt.ipbeja.chat;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.list = findViewById(R.id.contact_list);

        // TODO: Depois de definir o ContactAdapter, instanciar e atribuir à RecyclerView
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: Pedir a lista de contactos à BD e actualizar o ContactAdapter
    }

    public void onAddContactClicked(View view) {
        // TODO: Lançar a CreateContactActivity
    }


    // TODO: Definir uma class ContactViewHolder subclass de RecyclerView.ViewHolder
    //  Adicionar um OnClickListener para lançar a ChatActivity passando o id do contacto como extra

    // TODO: Definir uma class ContactAdapter subclass de RecyclerView.Adapter<ContactViewHolder>
    //  Esta class terá um atributo do tipo List<Contact>


}
