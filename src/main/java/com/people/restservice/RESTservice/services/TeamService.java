/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.services;

import com.people.restservice.RESTservice.models.Team;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author reta_
 */
public interface TeamService {
    Team create(Team team);
    Team update(Long id, Team team);
    void delete(Long id);
    Page<Team> findAll(Pageable pageable);
    List<Team> findAll();
    Team findById(Long id);
}
