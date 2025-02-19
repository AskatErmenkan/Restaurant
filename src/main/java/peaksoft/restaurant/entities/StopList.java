package peaksoft.restaurant.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Entity
@Table(name = "stopLists")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stopList_gen")
    @SequenceGenerator(name = "stopList_gen", sequenceName = "stopList_seq", allocationSize = 1)
    Long id;
    String reason;
    LocalDate date;

    @OneToOne
    MenuItem menuItem;
}
