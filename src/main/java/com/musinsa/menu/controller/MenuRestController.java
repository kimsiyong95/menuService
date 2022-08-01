package com.musinsa.menu.controller;


import com.musinsa.menu.exception.ApiException;
import com.musinsa.menu.exception.ExceptionEnum;
import com.musinsa.menu.request.BannerRequestDTO;
import com.musinsa.menu.request.MenuAddOrUpdateRequestDTO;
import com.musinsa.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MenuRestController {

    private final MenuService menuService;


    @GetMapping("/menu/{id}")
    public ResponseEntity selectMenu(@PathVariable(required = false) String id){

        if(!StringUtils.hasText(id)){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(menuService.selectMenu(id));
    }

    @PostMapping("/menu")
    public ResponseEntity addMenu(@Valid @RequestBody MenuAddOrUpdateRequestDTO requestDTO
            , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.addMenu(requestDTO));
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity updateMenu(@PathVariable(required = false) String id
            , @Valid @RequestBody MenuAddOrUpdateRequestDTO requestDTO
            , BindingResult bindingResult){

        if(bindingResult.hasErrors()||!StringUtils.hasText(id)){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(menuService.updateMenu(id, requestDTO));
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity deleteMenu(@PathVariable(required = false) String id){

        if(!StringUtils.hasText(id)){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(menuService.deleteMenu(id));
    }

    @PostMapping("/banner")
    public ResponseEntity addBanner(@Valid @RequestBody BannerRequestDTO requestDTO
            ,BindingResult bindingResult ){

        if(bindingResult.hasErrors()){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.addBanner(requestDTO));
    }






}
