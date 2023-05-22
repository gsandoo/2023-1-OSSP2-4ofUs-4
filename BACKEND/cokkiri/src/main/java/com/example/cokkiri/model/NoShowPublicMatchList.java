package com.example.cokkiri.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class NoShowPublicMatchList {
    @Id
    @Column
    private int matchingId;

    @ElementCollection
    private List<String> email;

    @Column
    private  String matchingType;
}
