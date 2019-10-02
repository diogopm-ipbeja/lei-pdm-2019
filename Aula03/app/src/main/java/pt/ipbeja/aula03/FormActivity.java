package pt.ipbeja.aula03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    public static final String NAME_FIELD = "name";
    public static final String STUDENT_NR_FIELD = "student_nr";

    private EditText name;
    private EditText studentNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        this.name = findViewById(R.id.name);
        this.studentNr = findViewById(R.id.student_nr);

        Intent intent = getIntent();
        int number = intent.getIntExtra(STUDENT_NR_FIELD, 0);
        String name = intent.getStringExtra(NAME_FIELD);

        this.name.setText(name);
        if(number > 0) this.studentNr.setText(number + "");

    }


    public void onConfirmBtnClicked(View view) {

        String name = this.name.getText().toString();
        int studentNr = Integer.parseInt(this.studentNr.getText().toString());

        Intent intent = new Intent();
        intent.putExtra(NAME_FIELD, name);
        intent.putExtra(STUDENT_NR_FIELD, studentNr);

        setResult(RESULT_OK, intent);
        finish();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);


    }
}
