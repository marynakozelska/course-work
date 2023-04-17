package org.example.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.example.entity.Advertisement;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends CosmosRepository<Advertisement, String> {
}
