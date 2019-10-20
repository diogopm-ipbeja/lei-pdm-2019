package pt.ipbeja.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView list;
    private EditText chatInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.list = findViewById(R.id.message_list);
        this.chatInputField = findViewById(R.id.chat_text_input);

        // TODO: Depois de definir o MessageAdapter, instanciar e atribuir à RecyclerView
    }

    public void onSendMessageClicked(View view) {
        // TODO: Retirar o texto de chatInputField, criar uma ChatMessage e adicioná-la à BD
        //  Adicionar a ChatMessage ao Adapter e notificá-lo que foi adicionado um item (Adapter#notifyItemInserted)
        //  Não esquecer limpar o chatInputField depois de 'enviar' a mensagem
        //  Não permitir que se envie uma mensagem com o texto vazio
    }

    // TODO: Definir uma class MessageViewHolder subclass de RecyclerView.ViewHolder

    // TODO: Definir uma class MessageAdapter subclass de RecyclerView.Adapter<MessageViewHolder>
    //  Esta class terá um atributo do tipo List<ChatMessage>

}
