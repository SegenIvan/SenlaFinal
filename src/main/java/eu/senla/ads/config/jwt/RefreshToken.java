package eu.senla.ads.config.jwt;

import eu.senla.ads.model.User;

import java.time.Instant;

import javax.persistence.*;

public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /*@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;*/

    /*@Column(nullable = false)
    private Instant expiryDate;*/
}
