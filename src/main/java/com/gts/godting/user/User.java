package com.gts.godting.user;

import com.gts.godting.profile.Profile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nickname;

    private String gender;

    private String region;

    private double height;

    @Column(unique = true)
    private int studentNum;

    private int age;

    private String body;

    private String major;

    private String interest;

    private boolean smoking;

    private boolean drinking;

    private boolean duty;

    @OneToMany(mappedBy = "user")
    private Set<Profile> profiles = new HashSet<>();

    private String provider;

    private String provider_id;

    @Column(unique = true)
    private String email;

    private String emailCheckToken;

    private LocalDateTime emailCheckTokenGeneratedAt;

    private LocalDateTime create_date_time;
}
