package com.osanda.roihunter.fbuserdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.osanda.roihunter.fbuserdata.model.User;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {

}
