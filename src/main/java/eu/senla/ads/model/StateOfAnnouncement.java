package eu.senla.ads.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "states")
public class StateOfAnnouncement extends AEntity {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EStateOfAnnouncement name;

}
