package pt.ipbeja.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        // TODO: Esta Activity deve mostrar os detalhes do contacto:
        //  - id, nome
        //  - Número total de mensagens associadas a este contacto (criar uma query em MessageDao
        //  que devolve um int)
        //  - Mostrar o texto da última mensagem enviada (criar uma query em MessageDao que devolve
        //  uma String)
    }
}
