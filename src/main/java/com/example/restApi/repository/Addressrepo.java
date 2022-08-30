package com.example.restApi.repository;

import com.example.restApi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Addressrepo extends JpaRepository<Address,Long> {

    //@Query()
    //public void deleteByUserId (Long id);
}
