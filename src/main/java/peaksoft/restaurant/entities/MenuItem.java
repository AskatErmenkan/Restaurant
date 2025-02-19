package peaksoft.restaurant.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menuItems")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuItem_gen")
    @SequenceGenerator(name = "menuItem_gen", sequenceName = "menuItem_seq", allocationSize = 1)
    Long id;
    String name;
    String imageUrl;
    BigDecimal price;
    String description;
    Boolean isVegetarian;

    @ManyToOne
    Restaurant restaurant;

    @ManyToMany(mappedBy = "menuItem")
    List<Cheque> cheques;

    @OneToOne
    StopList stopList;

    @ManyToOne
    SubCategory subcategory;

    public MenuItem(String name, String imageUrl, BigDecimal price, String description, Boolean isVegetarian, Restaurant restaurant, StopList stopList, SubCategory subcategory) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.restaurant = restaurant;
        this.stopList = stopList;
        this.subcategory = subcategory;
    }
}
