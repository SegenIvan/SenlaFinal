package eu.senla.ads.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "announcements")
public class Announcement extends AEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    @OneToMany(mappedBy = "announcement")
    private List<Comment> comments;
    @Column(name = "date_of_create")
    private LocalDate dateOfCreate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "announcement_state",
            joinColumns = @JoinColumn(name = "announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "state_id"))
    private Set<StateOfAnnouncement> states = new HashSet<>();
    @Column(name = "date_of_payment")
    private LocalDate dateOfPayment;
    @Column(name = "text")
    private String text;
    @Column(name = "tag")
    private String tag;
}
