package com.example.cokkiri.repository;

import com.example.cokkiri.model.MatchDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclarationRepository extends JpaRepository<MatchDeclaration,Integer> {
    public List<MatchDeclaration> findByMatchingType(String matchingType);
    public  MatchDeclaration findByMatchingIdAndMatchingType(String id, String matchingType);
}
