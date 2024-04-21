package models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book_copy")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Copy {

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "copy_id")
    private long copy_id;

    @Column(nullable = false, name = "is_taken_now")
    @NonNull
    private String is_taken_now;

    public Copy(Books book){
        setBook_id(book);
        setIs_taken_now("No");
    }

    public Copy() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copy other = (Copy) o;
        return Objects.equals(book_id, other.book_id)
                && Objects.equals(copy_id, other.copy_id);
    }
}