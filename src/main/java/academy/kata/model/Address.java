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
public class Address  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", updatable = false, nullable = false)
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;


    public Address(String city, String address) {
        this.city = city;
        this.address = address;
    }


    @Override
    public String toString() {
        return "city='" + city +
                "; address='" + address +
                '}';
    }
}
