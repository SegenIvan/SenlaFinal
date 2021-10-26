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
    @Column(name = "password")
    private String password;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Column(name = "rating")
    private Integer rating;
    @OneToMany(mappedBy = "author")
    private List<Announcement> announcements;
    @Column(name = "is_active")
    private boolean isActive;

    public User(String login, String email, String password, String name, String phone) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

}
