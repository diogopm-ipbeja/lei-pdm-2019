package pt.ipbeja.chat.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "message")
public class ChatMessage {

    public static final int INBOUND = 0;
    public static final int OUTBOUND = 1;

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long contactId;
    private String text;
    private int direction;
    private long timestamp;

    public ChatMessage(long id, long contactId, String text, int direction, long timestamp) {
        this.id = id;
        this.contactId = contactId;
        this.text = text;
        this.direction = direction;
        this.timestamp = timestamp;
    }

    @Ignore
    public ChatMessage(long contactId, String text,  int direction) {
        this(0, contactId, text, direction, System.currentTimeMillis());
    }

    public static ChatMessage randomDirection(long contactId, String text) {
        int randomDirection = (int) (Math.random() * 2); // INBOUND (0) ou OUTBOUND (1)
        return new ChatMessage(contactId, text, randomDirection);
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

    public int getDirection() {
        return direction;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setId(long id) {
        this.id = id;
    }
}