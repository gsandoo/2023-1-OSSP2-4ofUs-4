package com.example.cokkiri.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MatchAccusation {

    @Id
    @Column
    private int matchingId;

    @ElementCollection
    private List<String> email;

    @Column
    private String title;

    @Column
    private String comment;

    @Column
    private String matchingType;

}
