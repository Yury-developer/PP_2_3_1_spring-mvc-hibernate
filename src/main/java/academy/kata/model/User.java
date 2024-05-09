package academy.kata.model;

import academy.kata.model.entry.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
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
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "dateBirth")
    private Date dateBirth;

    @JoinColumn(name = "fk_address_id", referencedColumnName = "address_id")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Address address;

    @JoinColumn(name = "fk_phoneEntry_id", referencedColumnName = "user_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PhoneEntry> phones;

    @JoinColumn(name = "fk_emailEntry_id", referencedColumnName = "user_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<EmailEntry> emails;


    public User(String name, Date dateBirth, Address address, List<PhoneEntry> phones, List<EmailEntry> emails) {
        this.emails = emails;
        this.phones = phones;
        this.address = address;
        this.dateBirth = dateBirth;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateBirth=" + dateBirth +
                ", address=" + address +
                ", phones=" + phones +
                ", emails=" + emails +
                '}';
    }
}
