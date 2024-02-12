package com.kodilla.ecommercee.productGroup.controller;

import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import com.kodilla.ecommercee.productGroup.domain.ProductGroupsDTO;
import com.kodilla.ecommercee.productGroup.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.productGroup.service.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final ProductGroupService productGroupService;
    private final ProductGroupMapper productGroupMapper;

    @Autowired
    public GroupController(ProductGroupService productGroupService, ProductGroupMapper productGroupMapper){
        this.productGroupService = productGroupService;
        this.productGroupMapper = productGroupMapper;
    }

    @GetMapping
    public List<ProductGroupsDTO> getListOfGroups(){
        List<ProductGroups> listOfGroups = productGroupService.getAllProductGroups();
        return listOfGroups.stream()
                .map(productGroupMapper::mapToProductGroupDTO)
                .collect(Collectors.toList());
    }
    @PostMapping
    public void createGroup(@RequestBody ProductGroupsDTO productGroupsDTO){
        ProductGroups productGroups = productGroupMapper.mapToProductGroups(productGroupsDTO);
        productGroupService.createProductGroup(productGroups);
        System.out.println("The group has been created");
    }
    @GetMapping(value = "{groupId}")
    public ProductGroupsDTO getGroup(@PathVariable Long groupId){
        ProductGroups group = productGroupService.getProductGroupById(groupId)
                .orElseThrow(()-> new RuntimeException("Product group not found by id:" + groupId));
        return productGroupMapper.mapToProductGroupDTO(group);
    }
    @PutMapping(value = "{groupId}")
    public ProductGroupsDTO updateGroup(@PathVariable Long groupId, @RequestBody ProductGroupsDTO productGroupsDTO){
       ProductGroups existingGroup = productGroupService.getProductGroupById(groupId)
               .orElseThrow(()-> new RuntimeException("Product group not found by id:" + groupId));

       ProductGroups updatedGroup = productGroupMapper.mapToProductGroups(productGroupsDTO);
       updatedGroup.setId(existingGroup.getId());

       ProductGroups savedGroup = productGroupService.updateProductGroup(updatedGroup);
       return productGroupMapper.mapToProductGroupDTO(savedGroup);
    }
    @DeleteMapping(value = "{groupId}")
    public void deleteGroup (@PathVariable Long groupId){
        productGroupService.deleteProductGroup(groupId);
        System.out.println("The group has been deleted");
    }
}

