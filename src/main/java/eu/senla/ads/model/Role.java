package eu.senla.ads.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "roles")
public class Role extends AEntity {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
