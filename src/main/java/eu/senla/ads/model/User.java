package eu.senla.ads.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "rating")
    private Integer rating;
    @OneToMany(mappedBy = "author")
    private List<Announcement> announcements;
    @OneToOne (cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name="credential_id")
    private Credentials credentials;

    public User(String email, String name, String phone, Credentials credentials) {
        this.credentials = credentials;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

}
