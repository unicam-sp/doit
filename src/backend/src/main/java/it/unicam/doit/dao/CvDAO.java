package it.unicam.doit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.doit.entity.Cv;

public interface CvDAO extends JpaRepository<Cv, Integer> {

}
