package com.server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.Model.UpdateModel;

@Repository
public interface UpdateRepo extends JpaRepository<UpdateModel, Long> {

    
}
