package models;

import lombok.*;
import javax.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reader")
@Getter
@Setter
@ToString
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_id")
    private long reader_id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "surname")
    @NonNull
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(nullable = false, name = "card_date")
    @NonNull
    private Date card_date;

    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phone_number;

    public Cards(String name, String surname, String patronymic, String card_date, String date_of_birth,
                 String address, String phone_number) {
        setName(name);
        setSurname(surname);
        setPatronymic(patronymic);

        try {
            setCard_date(new SimpleDateFormat("yyyy-MM-dd").parse(card_date));
            setDate_of_birth(new SimpleDateFormat("yyyy-MM-dd").parse(date_of_birth));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        setAddress(address);
        setPhone_number(phone_number);
    }

    public Cards() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards other = (Cards) o;
        return Objects.equals(reader_id, other.reader_id)
                && name.equals(other.name)
                && surname.equals(other.surname)
                && patronymic.equals(other.patronymic);
    }
}