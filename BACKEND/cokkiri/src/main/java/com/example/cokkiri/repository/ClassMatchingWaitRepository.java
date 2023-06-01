package com.example.cokkiri.repository;

import com.example.cokkiri.model.ClassMatchingWait;
import com.example.cokkiri.model.PublicMatchingWait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassMatchingWaitRepository extends JpaRepository<ClassMatchingWait , Integer> {

    public List<ClassMatchingWait> findByEmail(String id);
    public  ClassMatchingWait findById(int id);
}
