package com.example.cokkiri.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class NoShowClassMatchList {
    @Id
    @Column
    private int matchingId;

    @ElementCollection
    private List<String> email;

    @Column
    private  String matchingType;
}
