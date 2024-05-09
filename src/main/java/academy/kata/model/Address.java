package academy.kata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "address")
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", updatable = false, nullable = false)
    private int id;

    @Column(name = "flat")
    private int flat; // квартира

    @Column(name = "home")
    private int home; // дом

    @Column(name = "corpus")
    private String corpus; // корпус

    @Column(name = "street")
    private String street; // улица

    @Column(name = "city")
    private String city; // город

    @Column(name = "state")
    private String state; // государство

    @Column(name = "postalCode")
    private int postalCode; // почтовый индекс


    public Address(int postalCode, String state, String city, String street, int home, int flat, String corpus) {
        this.flat = flat;
        this.home = home;
        this.corpus = corpus;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", flat=" + flat +
                ", home=" + home +
                ", corpus='" + corpus + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
