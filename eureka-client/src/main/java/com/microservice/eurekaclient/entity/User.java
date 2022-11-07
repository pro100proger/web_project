package com.microservice.eurekaclient.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="USERS", indexes = {
        @Index(columnList = "email", name = "user_email_idx")
})
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="first_name", length=255, nullable=false, unique=false)
    //@NameConstraint
    private String firstName;

    @Column(name="last_name", length=255, nullable=false, unique=false)
    //@NameConstraint
    private String lastName;

    @Column(name="email", length=255, nullable=false, unique=true)
    //@EmailConstraint
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="role", length=10, nullable=false, unique=false)
    private Role role;

    @Column(name="password", length=255, nullable=false, unique=false)
    private String password;

    @Column(name="is_active")
    private Boolean isActive;
}