/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.repositories;

import com.people.restservice.RESTservice.models.Player;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author reta_
 */
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long>{
    List<Player> findAll();
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Player p SET p.name = :name, p.rut= :rut WHERE p.id = :id", nativeQuery = true)
    public void update(@Param("id") Long id, @Param("name") String name, @Param("rut") String rut);
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Player p SET p.team_id = :teamId WHERE p.id = :id", nativeQuery = true)
    public void addTeam(@Param("id") Long id, @Param("teamId") Long teamId);
      
    @Query(value = "SELECT * FROM Player p WHERE p.team_id = :teamId", nativeQuery = true)
    List<Player> findAllByTeam(@Param("teamId") Long teamId);  
}
