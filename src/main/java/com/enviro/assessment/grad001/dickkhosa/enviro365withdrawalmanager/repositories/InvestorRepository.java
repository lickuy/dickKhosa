package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.repositories;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {

}
