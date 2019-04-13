package com.osanda.roihunter.fbuserdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.osanda.roihunter.fbuserdata.model.Image;

@RepositoryRestResource(exported = false)
public interface ImageRepository extends JpaRepository<Image, Long> {

}
