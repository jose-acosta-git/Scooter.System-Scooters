package scooters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import scooters.model.Stop;

public interface StopsRepository extends JpaRepository<Stop, Integer> {}