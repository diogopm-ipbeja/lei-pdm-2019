package pt.ipbeja.chat.db.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    // TODO: Adicionar atributos (e modificar construtor(es)):
    //  - long para indicar a data de criação do contacto
    //  - long para a data de nascimento
    //  - Array de bytes para a foto do contacto (ver BitmapUtils)
    //  Não se esqueça que como estará a alterar o schema da base de dados terá de incrementar a
    //  versão da BD

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    @Embedded
    private Coordinates coordinates;

    @Ignore // Este atributo não será tido em conta na criação da tabela "contact"
    private String initials;


    public Contact(long id, String name, Coordinates coordinates) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        generateInitials();
    }

    @Ignore
    public Contact(String name, Coordinates coordinates) {
        this(0, name, coordinates);
    }

    private void generateInitials() {
        String[] names = name.split(" ");
        String initials;
        if(names.length == 1) {
             initials = names[0].charAt(0) + "";
        }
        else {
            initials = names[0].charAt(0) + "" + names[names.length-1].charAt(0);
        }
        this.initials = initials.toUpperCase();
    }

    public String getInitials() {
        return initials;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        generateInitials();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
