package pt.ipbeja.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {

    private static final String CONTACT_ID_EXTRA = "contact_id";

    private long contactId;

    public static void start(Context context, long contactId) {
        Intent starter = new Intent(context, ContactDetailsActivity.class);
        starter.putExtra(CONTACT_ID_EXTRA, contactId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        this.contactId = getIntent().getLongExtra(CONTACT_ID_EXTRA, 0);
        if(this.contactId == 0) {
            finish();
            return;
        }

        // TODO: Esta Activity deve mostrar os detalhes do contacto:
        //  - id, nome, data de nascimento e data de criação
        //  - Número total de mensagens associadas a este contacto (criar uma query em MessageDao
        //  que devolve um int)
        //  - Mostrar o texto da última mensagem associada ao contacto (criar uma query em
        //  MessageDao que devolve uma String)
    }
}
