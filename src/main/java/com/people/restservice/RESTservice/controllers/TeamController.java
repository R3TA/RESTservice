/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.controllers;

import com.people.restservice.RESTservice.models.Player;
import com.people.restservice.RESTservice.models.Team;
import com.people.restservice.RESTservice.services.TeamService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author reta_
 */
@RestController
@RequestMapping("/teams")
public class TeamController {
    private TeamService service;
    
    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }
    //GET PAGE
    @GetMapping
    @ResponseBody
    public Page<Team> findAll(Pageable pageable){
        return this.service.findAll(pageable);
    }
    
    //GET LIST
    @GetMapping("/all")
    @ResponseBody
    public List<Team> findAll(){
        return this.service.findAll();
    }
    
    //GET BY ID
    @GetMapping("/{id}")
    @ResponseBody
    public Team findById(@PathVariable("id") Long id){    
        return this.service.findById(id);
    }
    
    //CREATE
    @PostMapping
    public ResponseEntity<Team> create(@Valid @RequestBody Team team){
        Team saved = this.service.create(team);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
    //PUT
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Team> update(@PathVariable("id") Long id, @Valid @RequestBody Team team){
        Team updated = this.service.update(id, team);        
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    
    //DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        this.service.delete(id);
    }
}
