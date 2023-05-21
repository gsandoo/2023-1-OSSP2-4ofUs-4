package com.example.cokkiri.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MatchDeclaration {

    @Id
    @Column
    private int matchingId;

    @Column
    private String comment;

    @Column
    private  String email;

}
