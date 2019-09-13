/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.services;

import com.people.restservice.RESTservice.models.Player;
import com.people.restservice.RESTservice.models.Team;
import com.people.restservice.RESTservice.repositories.TeamRepository;
import com.people.restservice.RESTservice.validations.PlayerNotFoundException;
import com.people.restservice.RESTservice.validations.TeamNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author reta_
 */
@Service
public class TeamServiceImpl implements TeamService{
    private TeamRepository repository;

    @Autowired
    public TeamServiceImpl(TeamRepository repository){
        this.repository = repository;
    }
    
    @Override
    public Team create(Team team) {
        return this.repository.save(team);
    }
    
    @Override
    public Team update(Long id, Team team) {        
        Optional<Team> findTeam = this.repository.findById(id);
        if(!findTeam.isPresent()){
            throw new TeamNotFoundException("Team not exist in the database!");
        }else{
            this.repository.update(id, team.getName(), team.getStadium());
            return findTeam.get();
        }
    }

    @Override
    public void delete(Long id) {
       //Team deleteTeam = this.findById(id);
       this.repository.deleteById(id);
    }
    
    @Override
    public Page<Team> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public List<Team> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Team findById(Long id) {
        Optional<Team> f = this.repository.findById(id);
            if(!f.isPresent()){
               throw new TeamNotFoundException("Team not exists in database!");               
            }else{
                return f.get();
            }   
    }
}
