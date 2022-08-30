package com.example.restApi.repository;

import com.example.restApi.model.User;
import com.example.restApi.repository.custome.CustomeUserDetailRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Primary
public interface Userdao extends JpaRepository<User,Long>, CustomeUserDetailRepo {
    public User findByUuid(UUID uuid);

    @Query("From User")
    public List<User> findAllUser();
    @Query("from User where name=:name ")
    public List<User> findUserByName(@Param("name") String name);

    @Query("from User where uuid=:uuid")
    public User getByUuid(@Param("uuid") UUID uuid);

}
