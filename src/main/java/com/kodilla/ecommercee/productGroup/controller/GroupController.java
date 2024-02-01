package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductGroupsDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @GetMapping
    public List<ProductGroupsDTO> getListOfGroups(){
        List<ProductGroupsDTO> listOfGroups = new ArrayList<>();
        return listOfGroups;
    }
    @PostMapping
    public void createGroup(ProductGroupsDTO productGroupsDTO){
        System.out.println("The group has been created");
    }
    @GetMapping(value = "{groupId}")
    public ProductGroupsDTO getGroup(Long id){
        ProductGroupsDTO group = new ProductGroupsDTO(1L, "Nazwa grupy", "Opis grupy");
        return group;
    }
    @PutMapping
    public ProductGroupsDTO updateGroup(ProductGroupsDTO productGroupsDTO){
        return new ProductGroupsDTO(2L, "Nazwa grupy zaktualizowana", "Opis grupy zaktualizowany");
    }
}

