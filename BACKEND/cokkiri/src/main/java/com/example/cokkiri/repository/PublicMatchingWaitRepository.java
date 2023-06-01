package com.example.cokkiri.repository;

import com.example.cokkiri.model.PublicMatchingWait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicMatchingWaitRepository extends JpaRepository<PublicMatchingWait, Integer> {
    public List<PublicMatchingWait> findByEmail(String id);
    public PublicMatchingWait findById(int id);
}
