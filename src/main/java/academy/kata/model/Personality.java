package academy.kata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "personality")
@NoArgsConstructor
@Getter
@Setter
public class Personality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personality_id", updatable = false, nullable = false)
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "dateBirth")
    private Date dateBirth;


    public Personality(Date dateBirth, String firstName, String lastName, String patronymic) {
        this.dateBirth = dateBirth;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.firstName = firstName;
    }


    @Override
    public String toString() {
        return "Personality{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateBirth=" + dateBirth +
                '}';
    }
}
