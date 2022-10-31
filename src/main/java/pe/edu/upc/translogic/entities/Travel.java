package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "travels")

@NoArgsConstructor
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private Date dateOfStart;
    private Date dateOfEnd;
    private int duration;
    private String state;

    public Travel(Date dateOfStart, Date dateOfEnd, int duration, String state) {
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.duration = duration;
        this.state = state;
    }
}
