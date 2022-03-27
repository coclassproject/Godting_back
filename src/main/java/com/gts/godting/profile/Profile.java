package com.gts.godting.profile;

import com.gts.godting.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
public class Profile {

    @Id @GeneratedValue
    private String id;

    @ManyToOne
    private User user;

    private String path;

    private String name;

    private String save_name;

    private LocalDateTime create_date_time;

}