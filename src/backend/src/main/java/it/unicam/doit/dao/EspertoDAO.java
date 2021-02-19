package it.unicam.doit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.doit.entity.Esperto;

public interface EspertoDAO extends JpaRepository<Esperto, Integer> {

	Esperto findByUsername(String string);

}
