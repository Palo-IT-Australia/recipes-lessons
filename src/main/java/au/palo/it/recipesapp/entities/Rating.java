package au.palo.it.recipesapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ratings")
@NoArgsConstructor
public class Rating {

    @Column
    @Getter
    @Setter
    private int rating;

    @Column
    @Getter
    @Setter
    private Date ratedAt;

    @Column
    @Getter
    @Setter
    private String comment;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @Getter
    @Setter
    private Recipe recipe;

    public Rating(int rating, Date date, String comment) {
        this.rating = rating;
        this.ratedAt = date;
        this.comment = comment;
    }
}
