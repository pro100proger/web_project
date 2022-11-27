package com.microservice.eurekaclient.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Permutation {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="data", length=255, nullable=false)
    private String data;

    @Column(name="duration")
    private Long duration;

    @Column(name="start_calculation")
    private LocalDateTime startCalculation;
    @Column(name="end_calculation")
    private LocalDateTime endCalculation;

    @ElementCollection
    @CollectionTable(name = "output_data")
    private List<String> outputData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_logs_user"))
    private User user;
    public Permutation(String data, LocalDateTime startCalculation) {
        this.data = data;
        this.startCalculation = startCalculation;
    }
}
