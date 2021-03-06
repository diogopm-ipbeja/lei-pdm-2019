package pt.ipbeja.chat;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import pt.ipbeja.chat.db.ChatDatabase;
import pt.ipbeja.chat.db.entity.Contact;
import pt.ipbeja.chat.db.entity.Coordinates;

public class CreateContactActivity extends AppCompatActivity implements OnMapReadyCallback {

    // TODO: Esta class também poderia servir para editar um Contacto, adicione essa possibilidade
    //  (dica, utilize os Extras para passar o ID do contacto se for o caso de uma edição)
    // TODO: Dar a opção de tirar uma foto e associá-la ao contacto (ver Contact e BitmapUtils)
    // TODO: Pedir a data de nascimento do contacto

    private Marker marker;

    private TextInputLayout contactNameFieldLayout;
    private TextInputEditText contactNameField;
    private DatePicker birthdayPicker;


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
        this.birthdayPicker = findViewById(R.id.contact_birthday);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void onConfirmContactClicked(View view) {
        String name = contactNameField.getText().toString();

        if(name.trim().isEmpty()) { // O método trim retira whitespace no início e fim da String
            // Se o user não preencheu o(s) campo(s) necessário(s), podemos alertar com um Snackbar
            Snackbar.make(view, R.string.create_contact_name_hint, Snackbar.LENGTH_SHORT).show();
            // Ou até indicar no TextInputLayout o erro (ver atributo errorEnabled no elemento do
            // layout activity_create_contact)
            contactNameFieldLayout.setError(getString(R.string.create_contact_error_hint));
        }
        else {
            // TODO: Retire o ano, mês e dia do birthdayPicker (ver getters) e atribua uma data de
            //  nascimento ao novo contacto
            //  Veja os TODOs da class Contact
            //  Veja os métodos DateUtils.createDate e DateUtils.createLong

            // TODO: Atribua também ao contacto a data de criação do mesmo
            //  Dica - Pode utilizar o método System.currentTimeMillis() para obter um long que
            //  representa a data/hora actual.
            Coordinates coordinates = null;
            if(marker != null) {
                LatLng position = marker.getPosition();
                coordinates = new Coordinates(position.latitude, position.longitude);
            }
            Contact contact = new Contact(contactNameField.getText().toString(), coordinates);
            //ChatDatabase.getInstance(this).contactDao().insert(contact);
            //finish();

            new InsertContactTask().execute(contact);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(marker == null) {
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(latLng);
                    marker = googleMap.addMarker(markerOptions);
                }
                else {
                    marker.setPosition(latLng);
                }
            }
        });
    }


    public class InsertContactTask extends AsyncTask<Contact, Void, Long> {

        @Override
        protected Long doInBackground(Contact... contacts) {
            // Vai correr numa thread à parte
            long id = ChatDatabase.getInstance(CreateContactActivity.this).contactDao().insert(contacts[0]);
            return id;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);

            finish();
        }
    }

}



















