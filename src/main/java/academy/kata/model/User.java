package academy.kata.model;

import academy.kata.model.entry.NoteEntry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
//@Table(name = "users", schema = "your_schema", catalog = "your_catalog", indexes = {}, uniqueConstraints = {}, options = "ENGINE=MyISAM")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false) // поле не может быть обновлено при выполнении операции обновления (UPDATE) в базе данных; не может содержать значение NULL
    private int id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_personality_id", referencedColumnName = "personality_id")
    private Personality personality;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_address_id", referencedColumnName = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_contact_id", referencedColumnName = "contact_id")
    private Contact contact;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_noteEntry_id", referencedColumnName = "user_id")
    private List<NoteEntry> notes;


    public User(Personality personality, Address address, Contact contact, List<NoteEntry> notes) {
        this.personality = personality;
        this.address = address;
        this.contact = contact;
        this.notes = notes;
    }


    @Override
    public String toString() {
        return "User{" +
                "\nid=" + id +
                ", \npersonality=" + personality +
                ", \naddress=" + address +
                ", \ncontact=" + contact +
                ", \nnotes=" + notes +
                '}';
    }
}
