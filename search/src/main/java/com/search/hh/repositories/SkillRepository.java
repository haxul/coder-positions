package com.search.hh.repositories;

import com.search.hh.entities.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Integer> {
    SkillEntity findSkillEntitiesByName(String name);
}
