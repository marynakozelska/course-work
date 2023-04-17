package org.example.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.example.entity.Volunteer;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends CosmosRepository<Volunteer, String> {
}
