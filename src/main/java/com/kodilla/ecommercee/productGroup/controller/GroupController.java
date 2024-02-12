package com.kodilla.ecommercee.productGroup.controller;

import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import com.kodilla.ecommercee.productGroup.domain.ProductGroupsDTO;
import com.kodilla.ecommercee.productGroup.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.productGroup.service.ProductGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final ProductGroupService productGroupService;
    private final ProductGroupMapper productGroupMapper;

    @GetMapping
    public ResponseEntity<List<ProductGroupsDTO>> getListOfGroups(){
        List<ProductGroups> listOfGroups = productGroupService.getAllProductGroups();
        List<ProductGroupsDTO> dtos = listOfGroups.stream()
                .map(productGroupMapper::mapToProductGroupDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Object> createGroup(@RequestBody ProductGroupsDTO productGroupsDTO){
        ProductGroups productGroups = productGroupMapper.mapToProductGroups(productGroupsDTO);
        productGroupService.createProductGroup(productGroups);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "{groupId}")
    public ResponseEntity<ProductGroupsDTO> getGroup(@PathVariable Long groupId){
        ProductGroups group = productGroupService.getProductGroupById(groupId)
                .orElseThrow(()-> new RuntimeException("Product group not found by id:" + groupId));
        ProductGroupsDTO dto = productGroupMapper.mapToProductGroupDTO(group);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PutMapping(value = "{groupId}")
    public ResponseEntity<ProductGroupsDTO> updateGroup(@PathVariable Long groupId, @RequestBody ProductGroupsDTO productGroupsDTO){
       ProductGroups existingGroup = productGroupService.getProductGroupById(groupId)
               .orElseThrow(()-> new RuntimeException("Product group not found by id:" + groupId));

       ProductGroups updatedGroup = productGroupMapper.mapToProductGroups(productGroupsDTO);
       updatedGroup.setId(existingGroup.getId());

       ProductGroups savedGroup = productGroupService.updateProductGroup(updatedGroup);
       ProductGroupsDTO savedDto = productGroupMapper.mapToProductGroupDTO(savedGroup);
       return new ResponseEntity<>(savedDto, HttpStatus.OK);
    }
    @DeleteMapping(value = "{groupId}")
    public ResponseEntity<Object> deleteGroup (@PathVariable Long groupId){
        productGroupService.deleteProductGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

