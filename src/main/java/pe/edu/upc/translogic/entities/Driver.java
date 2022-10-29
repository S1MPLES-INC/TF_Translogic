package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "drivers")

@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String names;
    private String surnames;
    private Date dateOfJoin;
    private Date dateOfBrith;

    @OneToMany(mappedBy = "driver")
    private List<Travel> travels;

    private String state;

    public Driver(String names, String surnames, Date dateOfJoin, Date dateOfBrith, String state) {
        this.names = names;
        this.surnames = surnames;
        this.dateOfJoin = dateOfJoin;
        this.dateOfBrith = dateOfBrith;
        this.state = state;
    }

}
