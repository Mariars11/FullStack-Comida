package com.example.cardapio.controller;

import com.example.cardapio.Models.Food;
import com.example.cardapio.dtos.FoodRecordDto;
import com.example.cardapio.repository.FoodRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FoodController
{
    @Autowired
    private FoodRepository repository;
    //Post
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/food")
    public ResponseEntity<Food> save(@RequestBody @Valid
                                     FoodRecordDto foodRecordDto){
        Food food = new Food();
        BeanUtils.copyProperties(foodRecordDto, food);

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(food));
    }
    //Get
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/food")
    public ResponseEntity<List<Food>> getAll()
    {
        List<Food> listFood = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listFood);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/food/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id){
        Optional<Food> foodO = repository.findById(id);

        if(foodO.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(foodO.get());
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    //Put
    @PutMapping("/food/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid FoodRecordDto foodRecordDto){
        Optional<Food> foodOptional = repository.findById(id);

        if(foodOptional.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not found!");
        }
        Food food = new Food();
        if(foodOptional.isPresent())
        {
            food = foodOptional.get();
            BeanUtils.copyProperties(foodRecordDto, food);
        }

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(food));
    }
    //Delete
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/food/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<Food> foodOptional = repository.findById(id);

        if(foodOptional.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not found!");
        }
        foodOptional.ifPresent(food -> repository.delete(food));

        return ResponseEntity.status(HttpStatus.OK).body("Food deleted with success!");
    }
}
