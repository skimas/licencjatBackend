package com.wlazlowski.licencjat;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository  extends MongoRepository<RFIDModule, String> {
    Optional<RFIDModule> findById(String id);
    List<RFIDModule> findByTimeGreaterThan(Long time);
}
