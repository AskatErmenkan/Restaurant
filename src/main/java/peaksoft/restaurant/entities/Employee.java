package peaksoft.restaurant.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import peaksoft.restaurant.enums.Role;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@FieldDefaults(level= AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_gen")
    @SequenceGenerator(name = "employee_gen",sequenceName = "employee_seq", allocationSize = 1)
    Long id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String email;
    String password;
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    Role role;
    int experience;

    @ManyToOne
    Restaurant restaurant;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    List<Cheque> cheques;

    public Employee(String firstName, String lastName, LocalDate dateOfBirth, String email, String password, String phoneNumber, Role role, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.experience = experience;
    }

    public Object getName() {
        return null;
    }
}
