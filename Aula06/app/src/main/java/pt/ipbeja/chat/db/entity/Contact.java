package pt.ipbeja.chat.db.entity;

import androidx.room.Ignore;

// TODO: Anotar esta Entity (class e atributo(s))
public class Contact {

    private long id;
    private String name;

    public Contact(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Contact(String name) {
        this.id = 0;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
