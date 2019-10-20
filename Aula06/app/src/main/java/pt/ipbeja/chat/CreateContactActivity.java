package pt.ipbeja.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class CreateContactActivity extends AppCompatActivity {

    private EditText contactNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        this.contactNameField = findViewById(R.id.contact_name);

    }

    public void onConfirmContactClicked(View view) {
        String name = contactNameField.getText().toString();

        if(name.trim().isEmpty()) {
            // Se o user não preencheu o(s) campo(s) necessário(s), podemos alertar com um Snackbar
            Snackbar.make(view, "Insira o nome", Snackbar.LENGTH_SHORT).show();
        }
        else {
            // TODO: Criar e adicionar um Contact à BD
            finish();
        }
    }
}
