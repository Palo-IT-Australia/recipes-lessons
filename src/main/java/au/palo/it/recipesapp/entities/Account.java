package au.palo.it.recipesapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String name;

    @Column
    @Getter
    @Setter
    private Boolean isAdmin;
}
