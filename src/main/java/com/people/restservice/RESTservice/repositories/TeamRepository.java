/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.repositories;

import com.people.restservice.RESTservice.models.Team;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reta_
 */
@Repository
public interface TeamRepository extends PagingAndSortingRepository<Team, Long>{
    List<Team> findAll();
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Team t SET t.name = :name, t.stadium = :stadium WHERE t.id = :id", nativeQuery = true)
    public void update(@Param("id") Long id, @Param("name") String name, @Param("stadium") String stadium);
}
