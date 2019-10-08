package pt.ipbeja.aula04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        // TODO 1) Adicionar uma RecyclerView ao layout desta Activity
        //         Criar o layout dos items da lista
        // TODO 2) findViewById -> RecyclerView
        // ...
        // TODO 5) Criar uma instância da class ContactAdapter
        // TODO 6) Criar uma instância da class LinearLayoutManager (ou definir no XML)
        // TODO 7) Atribuir o Adapter e LayoutManager à RecyclerView
        // ...
        // TODO 8) Adicionar items à lista
        // TODO 9) Permitir pressionar um item da lista e aceder a uma Activity de detalhe do mesmo
        // TODO 10) Eliminar items (OnLongClickListener) com confirmação do utilizador
        //          (ver AlertDialog) e feedback da acção (ver SnackBar ou Toast)
        // TODO 11) Criar uma Activity para criar Contacts. Quando volta à Activity anterior deve mostrar a lista actualizada com o novo Contact
        //          Altere o Contact para (além dos atributos existentes) ter o sexo, data de nascimento e uma fotografia (Bitmap)
        //          Para seleccionar o sexo do contacto deve utilizar RadioButtons (+ RadioGroup)
        //          Para seleccionar a data de nascimento deve utilizar um DatePicker
    }




    // TODO 3) Criar a class ContactViewHolder (extends ViewHolder)
    // TODO 4) Criar a class ContactAdapter (extends RecyclerView.Adapter<ContactViewHolder>) e
    //         implementar os métodos necessários


}
