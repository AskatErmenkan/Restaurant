package peaksoft.restaurant.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cheques")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_gen")
    @SequenceGenerator(name = "cheque_gen", sequenceName = "cheque_seq", allocationSize = 1)
    Long id;
    BigDecimal priceAverage;
    LocalDateTime createdAt;

    @ManyToOne
    Employee employee;

    @ManyToMany
    List<MenuItem> menuItem;


}
