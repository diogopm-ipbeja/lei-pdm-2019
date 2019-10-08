package pt.ipbeja.aula03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    // É aconselhável criar constantes para os códigos de pedido (ver #onActivityResult)
    private static final int FORM_REQUEST_CODE = 1000;
    private static final int CAMERA_REQUEST_CODE = 2000;

    private TextView name;
    private TextView studentNr;

    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.name = findViewById(R.id.name);
        this.studentNr = findViewById(R.id.student_nr);
        this.photo = findViewById(R.id.photo);

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
     * Método de callback do Button (ver atributo onClick em activity_main.xml)
     * @param view A View que foi pressionada (neste caso é o Button)
     */
    public void onEditClicked(View view) {
        Intent intent = new Intent(this, FormActivity.class);

        intent.putExtra(FormActivity.NAME_EXTRA, name.getText().toString());
        String studentNrText = studentNr.getText().toString();
        if(!studentNrText.isEmpty()) {
            intent.putExtra(FormActivity.STUDENT_NR_EXTRA, Integer.parseInt(studentNrText));
        }

        startActivityForResult(intent, FORM_REQUEST_CODE);

    }


    /**
     * Este método é invocado quando uma Activity que foi lançada com startActivityForResult termina
     * @param requestCode Identificador do pedido (o número que definimos quando lançámos a Activity)
     * @param resultCode Resultado OK (-1) ou cancelado (0). Ver constantes RESULT_OK, RESULT_CANCELED
     * @param data O Intent com os dados (se existirem) que a Activity devolveu
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Usamos o resultCode e requestCode para filtrar o tipo de acção
        // Ter bastante atenção para não confundir os dois e fazer comparações erradas
        if(resultCode == RESULT_OK) {
            // Se o resultado está OK e o código é o que estamos à espera...
            if(requestCode == FORM_REQUEST_CODE) {

                if(data != null) {
                    // Obtemos os extras
                    int studentNr = data.getIntExtra(FormActivity.STUDENT_NR_EXTRA, 0);
                    String studentName = data.getStringExtra(FormActivity.NAME_EXTRA);
                    // E colocamos as Strings nas TextViews
                    this.name.setText(studentName);
                    this.studentNr.setText(studentNr + "");
                }
            }
            else if(requestCode == CAMERA_REQUEST_CODE) {

                if(data != null) {
                    // O thumbnail da foto tirada está num extra com nome "data"
                    Bitmap thumbnail = data.getParcelableExtra("data");
                    // Depois de recuperar o Bitmap (representação da imagem) podemos colocá-lo na ImageView
                    this.photo.setImageBitmap(thumbnail);

                }

            }
        }



    }

    public void takePhoto(View view) {
        // Criar um intent implícito. Passamos a acção e o Android tratará de lançar a aplicação
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Temos também um novo request code que identificará o resultado (ver #onActivityResult)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);

    }
}























