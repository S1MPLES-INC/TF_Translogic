package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "groups")

@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @OneToMany(mappedBy = "group")
    private List<Driver> drivers;

    private String sector;

    public Group(String sector) {
        this.sector = sector;
    }
}