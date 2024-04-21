package models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.*;

@Entity
@Table(name = "book_copy")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class copy {

    @ManyToOne
    @JoinColumn(name = "book_id")
    private books book_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "copy_id")
    private long copy_id;

    @Column(nullable = false, name = "is_taken_now")
    @NonNull
    private String is_taken_now;

    public copy(books book){
        setBook_id(book);
        setIs_taken_now("No");
    }

    public copy() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        copy other = (copy) o;
        return Objects.equals(book_id, other.book_id)
                && Objects.equals(copy_id, other.copy_id);
    }
}