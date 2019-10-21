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
        this(0, name);
    }

    public String getInitials() {
        // TODO: Devolver uma String com (máx 2) iniciais
        //  (eg. "Arthur C. Clarke" -> "AC"; "Isaac Asimov" -> "IA"; "Verne" -> "V")
        //  Será utilizado para colocar as iniciais em cada item da lista de contactos
        return null;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
