package scooters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import scooters.model.Scooter;

public interface ScootersRepository extends JpaRepository<Scooter, Integer> {}