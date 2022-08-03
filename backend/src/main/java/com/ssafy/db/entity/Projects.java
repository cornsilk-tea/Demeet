package com.ssafy.db.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString(of = {"pid", "ownerId", "pjtStartDate", "pjtEndDate","pjtName","pjtDesc","totalMeetTime","activation"})
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    Long pid;
    @Column(nullable = false)
    Long ownerId;
    @Column(nullable = false)
    LocalDateTime pjtStartDate;

    LocalDateTime pjtEndDate;

    @Column(nullable = false)
    String pjtName;

    String pjtDesc;
    @Column(nullable = false)
    LocalDateTime totalMeetTime;
    @Column(nullable = false, columnDefinition = "tinyint(1) default 1")
    boolean activation;

    @OneToMany(mappedBy = "projects")
    List<UserProject> userProjectList = new ArrayList<UserProject>();
}
