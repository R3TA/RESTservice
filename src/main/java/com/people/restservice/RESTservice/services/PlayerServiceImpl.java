/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.services;

import com.people.restservice.RESTservice.models.Player;
import com.people.restservice.RESTservice.models.Team;
import com.people.restservice.RESTservice.repositories.PlayerRepository;
import com.people.restservice.RESTservice.repositories.TeamRepository;
import com.people.restservice.RESTservice.validations.PlayerNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author reta_
 */
@Service
public class PlayerServiceImpl implements PlayerService{
    private static final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);
    private PlayerRepository repository;
    private TeamRepository repository2;
    
    @Autowired
    public PlayerServiceImpl(PlayerRepository repository, TeamRepository repository2) {
        this.repository = repository;
        this.repository2 = repository2;
    }  
 
    @Override
    public Player create(Player player) {
        log.info("Creating player...");
        return this.repository.save(player);
    }

    @Override
    public Player update(Long id, Player player) {
        Player findPlayer = this.repository.findById(id).get();
        if(findPlayer == null){
            log.info("Updating is not possible: The player not exist in the database");
            throw new PlayerNotFoundException("Team not exist in the database!");
        }else{
            findPlayer.setName(player.getName());
            findPlayer.setName(player.getRut());           
            this.repository.save(findPlayer);
            log.info("Updating player...");
            return findPlayer;
        }
    }

    @Override
    public void delete(Long id) {
       log.info("Deleting player..."); 
       this.repository.deleteById(id);
    }

    @Override
    public Page<Player> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public List<Player> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Player findById(Long id) {
        Optional<Player> f = this.repository.findById(id);
        if(!f.isPresent()){
           throw new PlayerNotFoundException("Player not exists in the database!");               
        }else{
            log.info("Searching player...");
            return f.get();
        }    
    }

    @Override
    public Player addTeam(Long id, Long teamId) {
        log.info("Adding player to team...");
        Player finded = this.repository.findById(id).get();
        Team addedTeam = this.repository2.findById(teamId).get();
        finded.setTeam(addedTeam);
        this.repository.save(finded);
        return finded;
    }

    @Override
    public List<Player> findAllByTeam(Long teamId) {
        List<Player> players = this.repository.findAll();
        List<Player> playersById = new ArrayList<>();
        for(Player p: players){
           if((p.getTeam().getId()) == (teamId)){
                playersById.add(p);
           }
        }
        return playersById;
    }
}
