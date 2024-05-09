package academy.kata.model;

import academy.kata.model.entry.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "contact")
@NoArgsConstructor
@Getter
@Setter
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", updatable = false, nullable = false)
    private int id;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JoinColumn(name = "fk_phoneEntry_id", referencedColumnName = "phoneEntry_id") // в таблице 'PhoneEntry' будет столбец 'fk_phoneEntry_id', который будет содержать внешние ключи, ссылки на записи в таблице 'Contact' поле 'contact_id'
    @JoinColumn(name = "fk_phoneEntry_id", referencedColumnName = "contact_id") // в таблице 'PhoneEntry' будет столбец 'fk_phoneEntry_id', который будет содержать внешние ключи, ссылки на записи в таблице 'Contact' поле 'contact_id'
    private List<PhoneEntry> phoneNumbers; // наименование и номер тел.

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JoinColumn(name = "fk_emailEntry_id", referencedColumnName = "emailEntry_id") // Подставьте имя колонки, которое ссылается на контакт в таблице EmailEntry
    @JoinColumn(name = "fk_emailEntry_id", referencedColumnName = "contact_id") // Подставьте имя колонки, которое ссылается на контакт в таблице EmailEntry
    private Set<EmailEntry> emails; // наименование и email

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JoinColumn(name = "fk_urlEntry_id", referencedColumnName = "urlEntry_id") // Подставьте имя колонки, которое ссылается на контакт в таблице UrlEntry
    @JoinColumn(name = "fk_urlEntry_id", referencedColumnName = "contact_id") // Подставьте имя колонки, которое ссылается на контакт в таблице UrlEntry
    private Set<UrlEntry> urls; // наименование и URL ресурса


    public Contact(List<PhoneEntry> phoneNumbers, Set<EmailEntry> emails, Set<UrlEntry> urls) {
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
        this.urls = urls;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "\nid=" + id +
                ", \nphoneNumbers=" + phoneNumbers +
                ", \nemails=" + emails +
                ", \nurls=" + urls +
                '}';
    }
}
