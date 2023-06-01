package com.example.cokkiri.repository;

import com.example.cokkiri.model.MatchingWait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingWaitRepository extends JpaRepository<MatchingWait , Integer> {
    public List<MatchingWait> findByEmail(String id);
    public MatchingWait findById(int id);
}
