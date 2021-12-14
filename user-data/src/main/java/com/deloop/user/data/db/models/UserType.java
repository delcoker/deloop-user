//package com.deloop.user.data.db.models;
//
//import com.deloop.user.data.db.enums.TypeStatus;
//import io.ebean.annotation.DbDefault;
//import io.ebean.annotation.WhenCreated;
//import io.ebean.annotation.WhenModified;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Getter
//@Setter
//@Builder
//@ToString
//@Entity
//@Table(name = "user_types")
//public class UserType {
//    @Id
//    private long id;
//
//    @Column
//    @DbDefault("")
//    private String name;
//
//    @Column(columnDefinition = "TEXT")
//    @DbDefault("")
//    private String description;
//
//    @Column(columnDefinition = "TEXT")
//    @DbDefault("")
//    private String access;
//
//    @Enumerated(value = EnumType.STRING)
//    private TypeStatus status;
//
//    @Column @WhenCreated @DbDefault("2020-04-26 00:00")
//    private LocalDateTime createdAt;
//
//    @Column @WhenModified @DbDefault("2020-04-26 00:00")
//    private LocalDateTime updatedAt;
//
//    @OneToMany(mappedBy = "userType")
//    private List<User> users;
//
//    @ManyToMany(mappedBy = "userTypes")
//    private List<UserPermission> userPermissions;
//}
