/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.services;

import com.people.restservice.RESTservice.models.Team;
import com.people.restservice.RESTservice.repositories.TeamRepository;
import com.people.restservice.RESTservice.validations.TeamNotFoundException;
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
public class TeamServiceImpl implements TeamService{
    private static final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);
    private TeamRepository repository;

    @Autowired
    public TeamServiceImpl(TeamRepository repository){
        this.repository = repository;
    }
    
    @Override
    public Team create(Team team) {
        log.info("Creating team...");
        return this.repository.save(team);
    }
    
    @Override
    public void delete(Long id) {
       //Team deleteTeam = this.findById(id);
       log.info("Deleting team...");
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

    @Override
    public Team update(Long id, Team team) {      
        Team findingTeam = this.repository.findById(id).get();
        if(findingTeam == null){
            log.info("Updating is not possible: The team not exist in the database");
            throw new TeamNotFoundException("Team not exist in the database!");
        }else{
            log.info("Updating team...");
            //this.repository.update(id, team.getName(), team.getStadium());
            findingTeam.setName(team.getName());
            findingTeam.setStadium(team.getStadium());
            this.repository.save(findingTeam);
            return findingTeam;
        }  
    }
}