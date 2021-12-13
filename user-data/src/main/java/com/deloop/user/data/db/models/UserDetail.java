package com.deloop.user.data.db.models;

import com.deloop.user.data.db.enums.Gender;
import io.ebean.annotation.DbDefault;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "user_details")
public class UserDetail {
    @Id
    private long id;

    @Column
    private String profilePicture;

    @Column
    @DbDefault("")
    private String firstName;
    @Column(columnDefinition = "VARCHAR(100)")
    @DbDefault("")
    private String otherNames;
    @Column(columnDefinition = "VARCHAR(100)")
    @DbDefault("")
    private String lastName;
    @Transient
    @DbDefault("")
    private String fullName;
    @Transient
    @DbDefault("")
    private String titledFullName;
    @Transient
    @DbDefault("")
    private String shortName;
    @Transient
    @DbDefault("")
    private String initials;
    @Transient
    private int age; // do I need this property

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column
    private LocalDateTime dateOfBirth;

    @Column
    @DbDefault("")
    private String placeOfBirth;

    @Column
    @DbDefault("")
    private String prefix;

    @Column(columnDefinition = "VARCHAR(100)")
    @DbDefault("")
    private String title;

    @Column(columnDefinition = "TEXT")
    @DbDefault("")
    private String memo;

    @Column
    @DbDefault("2020-04-26 00:00")
    private LocalDateTime lastLogin;
    @Column
    @WhenCreated
    @DbDefault("2020-04-26 00:00")
    private LocalDateTime createdAt;
    @Column
    @WhenModified
    @DbDefault("2020-04-26 00:00")
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "userDetail")
    private List<Address> addresses;

    public int getAge() {
        if (dateOfBirth == null) {
            return -1;
        }
        return Period.between(dateOfBirth.toLocalDate(), LocalDate.now()).getYears() + 1;
    }

    public String getFullName() {
        return String.format("%s %s %s", firstName, otherNames, lastName);
    }

    public String getTitledFullName() {
        return String.format("%s %s %s", title, prefix, getFullName());
    }

    public String getShortName() {
        return String.format("%s %s %s", prefix, getInitials(), lastName);
    }

    public String getInitials() {
        if (otherNames == null || otherNames.length() < 1) {
            return "";
        }
        return String.format("%s. %s. %s", String.valueOf(firstName.charAt(0)).toUpperCase(),
                String.valueOf(otherNames.charAt(0)).toUpperCase(), lastName);
    }
}