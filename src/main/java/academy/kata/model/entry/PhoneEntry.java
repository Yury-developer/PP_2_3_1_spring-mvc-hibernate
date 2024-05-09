package academy.kata.model.entry;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "phones")
@NoArgsConstructor
@Getter
@Setter
public class PhoneEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phoneEntry_id", updatable = false, nullable = false)
    private int id;

    @Column(name = "phoneEntry_description")
    private String description;

    @Column(name = "phoneEntry_value")
    private String value;


    public PhoneEntry(String description, String value) {
        this.description = description;
        this.value = value;
    }


    @Override
    public String toString() {
        return "description='" + description + '\'' +
                ", value='" + value + "\n";
    }
}
