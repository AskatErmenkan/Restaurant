package peaksoft.restaurant.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "subcategories")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
    @SequenceGenerator(name = "subcategory_gen", sequenceName = "category_seq", allocationSize = 1)
    Long id;
    String name;

    @OneToMany(mappedBy = "subcategory")
    List<MenuItem> menuItemList;

    @ManyToOne
    Category category;

}
