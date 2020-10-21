package com.leron.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leron.models.User;

@RepositoryRestResource(collectionResourceRel= "users", path= "allusers")
public interface UserRepository extends JpaRepository<User, Long> {
	
}
