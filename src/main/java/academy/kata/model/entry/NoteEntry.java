package academy.kata.model.entry;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "notes")
@NoArgsConstructor
@Getter
@Setter
public class NoteEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noteEntry_id", updatable = false, nullable = false)
    private int id;

    @Column(name = "noteEntry_description")
    private String description;

    @Column(name = "noteEntry_value")
    private String value;


    public NoteEntry(String description, String value) {
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
