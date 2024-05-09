package academy.kata.model.entry;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "urls")
@NoArgsConstructor
@Getter
@Setter
public class UrlEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "urlEntry_id", updatable = false, nullable = false)
    private int id;

    @Column(name = "urlEntry_description")
    private String description;

    @Column(name = "urlEntry_value")
    private String value;


    public UrlEntry(String description, String value) {
        this.description = description;
        this.value = value;
    }


    @Override
    public String toString() {
        return '{' +
//                "id=" + id +
                ", description='" + description + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
