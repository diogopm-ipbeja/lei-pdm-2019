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


        this.list = findViewById(R.id.message_list);
        this.chatInputField = findViewById(R.id.chat_text_input);
        this.adapter = new MessageAdapter();
        list.setAdapter(adapter);


        // TODO: Depois de definir o MessageAdapter, instanciar e atribuir à RecyclerView
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter.setMessages(ChatDatabase.getInstance(this).messageDao().getAll(contactId));
    }

    public void onSendMessageClicked(View view) {

        String msg = chatInputField.getText().toString();
        ChatMessage message = ChatMessage.randomDirection(contactId, msg);
        ChatDatabase.getInstance(this).messageDao().insert(message);
        adapter.addMessage(message);
        list.smoothScrollToPosition(adapter.getItemCount()- 1);

        // TODO: Retirar o texto de chatInputField, criar uma ChatMessage e adicioná-la à BD
        //  Adicionar a ChatMessage ao Adapter e notificá-lo que foi adicionado um item
        //  (Adapter#notifyItemInserted)
        //  Não esquecer limpar o chatInputField depois de 'enviar' a mensagem
        //  Não permitir que se envie uma mensagem com o texto vazio
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        private ChatMessage chatMessage;
        private TextView messageText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.messageText = itemView.findViewById(R.id.chat_item_text);
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
            int layout = 0;
            switch (viewType) {
                case ChatMessage.INBOUND:
                    layout = R.layout.inbound_chat_list_item;
                    break;
                case ChatMessage.OUTBOUND:
                    layout = R.layout.outbound_chat_list_item;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown message type");
            }

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(layout, parent, false);

            return new MessageViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
            ChatMessage message = messages.get(position);
            holder.bind(message);
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        @Override
        public int getItemViewType(int position) {

            ChatMessage message = messages.get(position);
            return message.getDirection();
        }
    }
}