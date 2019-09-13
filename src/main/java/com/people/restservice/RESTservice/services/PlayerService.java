/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.services;

import com.people.restservice.RESTservice.models.Player;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author reta_
 */

public interface PlayerService {
    
    Player create(Player player);
    Player update(Long id, Player player);
    void delete(Long id);
    Page<Player> findAll(Pageable pageable);
    List<Player> findAll();
    Player findById(Long id);
    Player addTeam(Long id, Long teamId);
    List<Player> findAllByTeam(Long teamId);
}
