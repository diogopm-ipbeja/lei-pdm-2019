package pt.ipbeja.chat.db.entity;

import androidx.room.Ignore;

// TODO: Anotar esta Entity (class e atributo(s))
public class ChatMessage {

    private long id;
    private long contactId;
    private String text;

    public ChatMessage(long id, long contactId, String text) {
        this.id = id;
        this.contactId = contactId;
        this.text = text;
    }

    @Ignore
    public ChatMessage(long contactId, String text) {
        this.id = 0;
        this.contactId = contactId;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public long getContactId() {
        return contactId;
    }

    public String getText() {
        return text;
    }
}
