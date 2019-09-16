/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.controllers;

import com.people.restservice.RESTservice.models.Player;
import com.people.restservice.RESTservice.services.PlayerService;
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
@RequestMapping("/players")
public class PlayerController {
    private PlayerService service;
    
    @Autowired    
    public PlayerController(PlayerService service){
        this.service = service;
    }
    //GET PAGE
    @GetMapping
    @ResponseBody
    public Page<Player> findAll(Pageable pageable){
        return this.service.findAll(pageable);
    }
    
    //GET LIST
    @GetMapping("/all")
    @ResponseBody
    public List<Player> findAll(){
        return this.service.findAll();
    }
    
    //GET BY ID
    @GetMapping("/{id}")
    @ResponseBody
    public Player findById(@PathVariable("id") Long id){    
        return this.service.findById(id);
    }
    
    //CREATE
    @PostMapping
    public ResponseEntity<Player> create(@Valid @RequestBody Player player){
        Player saved = this.service.create(player);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
    //PUT
    @PutMapping("/{id}") 
    public ResponseEntity<Player> update(@PathVariable("id") Long id, @Valid @RequestBody Player player){
        Player updated = this.service.update(id, player);        
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/{teamId}")
    public ResponseEntity<Player> addTeam(@PathVariable("id") Long id, @PathVariable("teamId") Long teamId){
        Player addTeam = this.service.addTeam(id, teamId);
        return new ResponseEntity<>(addTeam, HttpStatus.OK);
    }
    
    //DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        this.service.delete(id);
    }
    
    //SEARCH PLAYERS BY TEAM
    @GetMapping("/team/{teamId}")
    @ResponseBody
    public List<Player> findAllByTeam(@PathVariable("teamId") Long teamId){
        return this.service.findAllByTeam(teamId);
    }
}