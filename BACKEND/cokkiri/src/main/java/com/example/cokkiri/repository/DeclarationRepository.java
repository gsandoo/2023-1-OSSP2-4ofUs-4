package com.example.cokkiri.repository;

import com.example.cokkiri.model.MatchDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclarationRepository extends JpaRepository<MatchDeclaration,Integer> {
}
