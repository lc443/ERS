package com.leron.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leron.models.Reimbursement;

@RepositoryRestResource(collectionResourceRel= "reimbursements", path= "reimbursements")
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Long> {

}
