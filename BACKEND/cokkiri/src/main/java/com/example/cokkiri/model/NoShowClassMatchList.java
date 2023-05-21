package com.example.cokkiri.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class NoShowClassMatchList {
    @Column
    private int matchingId;

    @Id
    @Column
    private String email;

    @Column
    private  String matchingType;
}
