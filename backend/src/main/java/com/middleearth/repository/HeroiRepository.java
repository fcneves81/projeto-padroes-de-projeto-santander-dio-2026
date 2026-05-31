package com.middleearth.repository;

import com.middleearth.model.Heroi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroiRepository extends JpaRepository<Heroi, Long> {
}
