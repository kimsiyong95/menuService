package com.musinsa.menu.service;

import com.musinsa.menu.domain.Menu;
import com.musinsa.menu.exception.ApiException;
import com.musinsa.menu.exception.ExceptionEnum;
import com.musinsa.menu.repository.MenuRepository;
import com.musinsa.menu.request.MenuAddOrUpdateRequestDTO;
import com.musinsa.menu.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public ResponseDTO selectMenu(String id){
        List<Menu> findMenu;
        try {
             findMenu = menuRepository.findMenuWithChildren(Long.valueOf(id));
        }catch (NumberFormatException e){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }
        return new ResponseDTO().createResponseDTO(findMenu);
    }

    public ResponseDTO addMenu(MenuAddOrUpdateRequestDTO requestDTO){
        Menu ptMenu = null;
        if(StringUtils.hasText(requestDTO.getParentId())){
             try {
                 ptMenu = menuRepository.findById(Long.valueOf(requestDTO.getParentId()))
                         .orElseThrow(()->new ApiException(ExceptionEnum.USER_PARAM_ERROR));
             }catch (NumberFormatException e){
                 throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
             }
        }

        Menu saveMenu = menuRepository.save(Menu.createMenu(requestDTO, ptMenu));

        return new ResponseDTO().createResponseDTO(saveMenu.getId());
    }

    public ResponseDTO updateMenu(){
        return new ResponseDTO().createResponseDTO("");
    }

    public ResponseDTO deleteMenu(Long id){

        return new ResponseDTO().createResponseDTO(new HashMap<>());
    }

    public ResponseDTO addBanner(){
        return new ResponseDTO().createResponseDTO("");
    }

}
