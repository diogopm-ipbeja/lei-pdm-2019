package pt.ipbeja.chat;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CreateContactActivity extends AppCompatActivity {

    // TODO: Esta class também poderia servir para editar um Contacto

    private TextInputLayout contactNameFieldLayout;
    private TextInputEditText contactNameField;

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
            // TODO: Criar e adicionar um Contact à BD
            finish();
        }
    }
}
