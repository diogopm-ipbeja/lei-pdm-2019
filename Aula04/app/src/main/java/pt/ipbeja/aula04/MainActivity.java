package pt.ipbeja.aula04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import pt.ipbeja.aula04.data.ContactDataSource;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 1) Adicionar uma RecyclerView ao layout desta Activity (será necessário incluir a
        //         biblioteca)
        //         Criar o layout dos items da lista
        // TODO 2) findViewById -> RecyclerView
        // ...
        // TODO 5) Criar uma instância da class ContactAdapter
        // TODO 6) Criar uma instância da class LinearLayoutManager (ou definir no XML)
        // TODO 7) Atribuir o Adapter e LayoutManager à RecyclerView



    }


    // TODO 3) Criar a class ContactViewHolder (extends ViewHolder)
    // TODO 4) Criar a class ContactAdapter (extends RecyclerView.Adapter<ContactViewHolder>) e
    //         implementar os métodos necessários


}