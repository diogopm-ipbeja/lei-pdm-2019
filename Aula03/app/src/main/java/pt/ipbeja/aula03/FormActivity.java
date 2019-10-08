package pt.ipbeja.aula03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    public static final String TAG = FormActivity.class.getSimpleName();

    // É aconselhável criar constantes para nomes de extras dos Intents
    public static final String NAME_EXTRA = "name";
    public static final String STUDENT_NR_EXTRA = "student_nr";

    private EditText name;
    private EditText studentNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Lifecycle onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        this.name = findViewById(R.id.name);
        this.studentNr = findViewById(R.id.student_nr);

        Intent intent = getIntent();
        int number = intent.getIntExtra(STUDENT_NR_EXTRA, 0);
        String name = intent.getStringExtra(NAME_EXTRA);

        this.name.setText(name);
        if(number > 0) this.studentNr.setText(number + "");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Lifecycle onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Lifecycle onResume");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Lifecycle onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Lifecycle onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Lifecycle onDestroy");
        super.onDestroy();
    }


    /**
     * Método de callback do FloatingActionButton (ver atributo onClick em activity_form.xml)
     * @param view A View que foi pressionada (neste caso é o FloatingActionButton)
     */
    public void onConfirmBtnClicked(View view) {
        // Neste método vamos recolher os dados das EditText, embrulhá-los num Intent e devolver o resultado à Activity anterior

        String name = this.name.getText().toString();
        int studentNr = Integer.parseInt(this.studentNr.getText().toString());

        // Vamos criar um Intent vazio para guardar os valores
        Intent intent = new Intent();

        // Ver constantes no topo desta class
        intent.putExtra(NAME_EXTRA, name);
        intent.putExtra(STUDENT_NR_EXTRA, studentNr);

        // Temos de informar a Activity que esta terminou com sucesso e passar o intent
        setResult(RESULT_OK, intent);
        // Por fim, damos a ordem para terminar esta Activity (e voltar à anterior)
        finish();

    }


}
