package pt.ipbeja.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import pt.ipbeja.chat.db.ChatDatabase;
import pt.ipbeja.chat.db.entity.Contact;

public class CreateContactActivity extends AppCompatActivity {

    // TODO: Esta class também poderia servir para editar um Contacto

    private TextInputLayout contactNameFieldLayout;
    private TextInputEditText contactNameField;


    public static void start(Context context) {
        Intent starter = new Intent(context, CreateContactActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        this.contactNameField = findViewById(R.id.contact_name);
        this.contactNameFieldLayout = findViewById(R.id.contact_name_layout);

    }

    public void onConfirmContactClicked(View view) {
        String name = contactNameField.getText().toString();

        if(name.trim().isEmpty()) { // O método trim retira whitespace no início e fim da String
            // Se o user não preencheu o(s) campo(s) necessário(s), podemos alertar com um Snackbar
            Snackbar.make(view, "Insira o nome", Snackbar.LENGTH_SHORT).show();
            // Ou até indicar no TextInputLayout o erro (ver atributo errorEnabled no elemento do
            // layout activity_create_contact)
            contactNameFieldLayout.setError("Este campo tem de ser preenchido.");
        }
        else {
            Contact contact = new Contact(contactNameField.getText().toString());
            ChatDatabase.getInstance(this).contactDao().insert(contact);
            finish();
        }
    }
}
