/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.services;

import com.people.restservice.RESTservice.models.Player;
import com.people.restservice.RESTservice.repositories.PlayerRepository;
import com.people.restservice.RESTservice.validations.PlayerNotFoundException;
import java.util.List;
import java.util.Optional;
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
    private PlayerRepository repository;
    @Autowired
    public PlayerServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }  
    
    @Override
    public Player create(Player player) {
        return this.repository.save(player);
    }

    @Override
    public Player update(Long id, Player player) {
        Optional<Player> findPlayer = this.repository.findById(id);
        if(!findPlayer.isPresent()){
            throw new PlayerNotFoundException("Team not exist in the database!");
        }else{
            this.repository.update(id, player.getName(), player.getRut());
            return findPlayer.get();
        }
    }

    @Override
    public void delete(Long id) {
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
                return f.get();
            }    
    }

    @Override
    public Player addTeam(Long id, Long teamId) {
        Optional<Player> finded = this.repository.findById(id);
        Player addedTeam = new Player(finded.get().getName(),finded.get().getRut());
        this.repository.addTeam(id, teamId);
        return addedTeam;  
    }

    @Override
    public List<Player> findAllByTeam(Long teamId) {
        return this.repository.findAllByTeam(teamId);
    }   
}