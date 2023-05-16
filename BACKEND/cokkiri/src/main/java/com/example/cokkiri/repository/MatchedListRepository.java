package com.example.cokkiri.repository;

import com.example.cokkiri.model.ClassMatchedList;
import com.example.cokkiri.model.PublicMatchedList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchedListRepository extends JpaRepository<ClassMatchedList, Integer> {


}
