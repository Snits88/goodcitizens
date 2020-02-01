package com.goodcitizens.persistence.repository;

import com.goodcitizens.persistence.model.Citizen;
import org.springframework.data.repository.CrudRepository;

public interface CitizenBasicCrudRepository extends CrudRepository<Citizen, Long>, CitizenCustomCrudRepository {
}
