package models;

import lombok.*;
import javax.persistence.*;

import java.util.Date;
import java.util.Objects;
import util.DAOFactory;

@Entity
@Table(name = "records")
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class read {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "record_id")
    private long record_id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "reader_id")
    private cards reader_id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "copy_id")
    private copy copy_id;

    @Column(nullable = false, name = "taking_date")
    @NonNull
    private Date taking_date;

    @Column(name = "returning_date")
    private Date returning_date;

    public read(String reader_surname, String book_name){
        setReader_id(DAOFactory.getInstance().getReaderDAO().getcardsBySurname(reader_surname).get(0));
        copy copy = DAOFactory.getInstance().getCopyDAO().GetBookCopyByBookName(book_name);
        setCopy_id(copy);
        copy.setIs_taken_now("Yes");
        DAOFactory.getInstance().getCopyDAO().updateCopy(copy);
        Date current_date = new Date(System.currentTimeMillis());
        setTaking_date(current_date);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        read other = (read) o;
        return Objects.equals(record_id, other.record_id)
                && Objects.equals(reader_id, other.reader_id)
                && Objects.equals(copy_id, other.copy_id)
                && taking_date.equals(other.taking_date)
                && returning_date.equals(other.returning_date);
    }
}