package com.example.cokkiri.repository;

import com.example.cokkiri.model.MatchAccusation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccusationRepository extends JpaRepository<MatchAccusation,Integer> {
    public List<MatchAccusation> findByMatchingType(String matchingType);
    public MatchAccusation findByMatchingIdAndMatchingType(int id, String matchingType);
}
