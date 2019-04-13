package com.osanda.roihunter.fbuserdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.osanda.roihunter.fbuserdata.model.Album;

@RepositoryRestResource(exported = false)
public interface AlbumRepository extends JpaRepository<Album, Long> {
	
	Album findFirstByName(String name);

}
