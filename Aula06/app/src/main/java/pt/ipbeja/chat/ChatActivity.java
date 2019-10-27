package pt.ipbeja.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pt.ipbeja.chat.db.ChatDatabase;
import pt.ipbeja.chat.db.entity.ChatMessage;

public class ChatActivity extends AppCompatActivity {

    private static final String CONTACT_ID_EXTRA = "contact_id";

    private RecyclerView list;
    private EditText chatInputField;
    private long contactId;
    private MessageAdapter adapter;

    public static void start(Context context, long contactId) {
        Intent starter = new Intent(context, ChatActivity.class);
        starter.putExtra(CONTACT_ID_EXTRA, contactId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.contactId = getIntent().getLongExtra(CONTACT_ID_EXTRA, 0);

        if (contactId == 0) {
            finish();
            return;
        }

        // TODO: Colocar na toolbar o nome do contacto
        // TODO: Criar um menu com:
        //  - item para apagar todas as mensagens do contacto
        //  - item para apagar o contacto (e as suas mensagens)
        //  - item para aceder aos detalhes do contacto (ContactDetailsActivity)

        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("yo");

        this.list = findViewById(R.id.message_list);
        this.chatInputField = findViewById(R.id.chat_text_input);
        this.adapter = new MessageAdapter();
        list.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.setMessages(ChatDatabase.getInstance(this).messageDao().getAll(contactId));
        scrollToBottom();
    }

    private void scrollToBottom() {
        int position = adapter.messages.size() - 1;
        if(position > -1) list.smoothScrollToPosition(position);
    }

    public void onSendMessageClicked(View view) {

        String msgText = chatInputField.getText().toString().trim();

        if(!msgText.isEmpty()) {
            chatInputField.setText(""); // Como já retirámos o input, podemos limpar a caixa de texto

            // TODO: Incluir a data do envio da mensagem (System.currentTimeMillis() devolve a data
            //  actual na forma de um long)
            ChatMessage message = ChatMessage.randomDirection(this.contactId, msgText);
            ChatDatabase.getInstance(this).messageDao().insert(message);
            adapter.addMessage(message);
            scrollToBottom();
        }
    }



    class MessageViewHolder extends RecyclerView.ViewHolder {

        private ChatMessage chatMessage;
        private TextView messageText;
        // TODO: Adicionar uma TextView com a data/hora da mensagem (ver ChatMessage)
        //  Para formatar a data que vem como um long numa String legível, veja os métodos na class
        //  DateUtils.
        //  ex de utilização DateUtils.format(1572200219058) resulta em "27/10/2019 18:16"

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.messageText = itemView.findViewById(R.id.chat_item_text);

            // TODO: Ao pressionar prolongadamente a mensagem, mostra um AlertDialog para eliminar a
            //  mensagem.
            //  Mostre o texto, data de envio e se foi enviada ou recebida no corpo do AlertDialog
            //  Adicione um botão ao AlertDialog para confirmar a eliminação e outro para cancelar
        }

        public void bind(ChatMessage message) {
            this.chatMessage = message;
            messageText.setText(message.getText());
        }
    }


    class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

        private List<ChatMessage> messages = new ArrayList<>();

        public void setMessages(List<ChatMessage> messages) {
            this.messages = messages;
            notifyDataSetChanged();
        }


        public void addMessage(ChatMessage message) {
            this.messages.add(message);
            notifyItemInserted(messages.size() - 1);
        }

        @NonNull
        @Override
        public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Temos 2 layouts diferentes: Um para as mensagens de entrada e outro para as de saída

            // Dependendo o viewType (ver getItemViewType) escolhemos um ou outro layout para o item
            int layout; // As refs são ints (R.layout.foo, R.id.bar, R.string.fizz, etc.)
            switch (viewType) { // o viewType que nos chega é um de 2 valores possíveis (direction)
                case ChatMessage.INBOUND:
                    layout = R.layout.inbound_chat_list_item;
                    break;
                case ChatMessage.OUTBOUND:
                    layout = R.layout.outbound_chat_list_item;
                    break;
                default:
                    // Opcionalmente podemos lançar uma excepção caso seja um tipo não esperado
                    throw new IllegalArgumentException("Unknown message type");
            }

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(layout, parent, false);

            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
            // O holder que entra aqui tem o layout correcto para a mensagem que vamos "desenhar"
            ChatMessage message = messages.get(position);
            // Como a View tem exactamente os mesmos atributos para cada um dos layouts, podemos
            // utilizar o mesmo ViewHolder
            holder.bind(message);
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        @Override
        public int getItemViewType(int position) {
            // Podemos definir o tipo de cada item da lista
            ChatMessage message = messages.get(position);
            // neste caso, podemos utilizar a direcção como diferenciador (ver onCreateViewHolder)
            return message.getDirection();
        }
    }
}