package pt.ipbeja.aula03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int FORM_REQUEST_CODE = 1000;

    private TextView name;
    private TextView studentNr;
    private Button editBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.name = findViewById(R.id.name);
        this.studentNr = findViewById(R.id.student_nr);

        this.editBtn = findViewById(R.id.edit_btn);

    }

    public void onEditClicked(View view) {
        Intent intent = new Intent(this, FormActivity.class);
        String name = this.name.getText().toString();
        String text = this.studentNr.getText().toString();

        int number = 0;
        if(!text.isEmpty()) {
            number = Integer.parseInt(text);
        }

        intent.putExtra(FormActivity.NAME_FIELD, name);
        intent.putExtra(FormActivity.STUDENT_NR_FIELD, number);

        startActivityForResult(intent, FORM_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            
            if(requestCode == FORM_REQUEST_CODE) {

                if(data != null) {

                    int studentNr = data.getIntExtra(FormActivity.STUDENT_NR_FIELD, 0);
                    String studentName = data.getStringExtra(FormActivity.NAME_FIELD);

                    this.name.setText(studentName);
                    this.studentNr.setText(studentNr + "");
                }
            }
        }



    }
}























