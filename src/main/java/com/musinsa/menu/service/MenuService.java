package com.musinsa.menu.service;

import com.musinsa.menu.domain.Menu;
import com.musinsa.menu.exception.ApiException;
import com.musinsa.menu.exception.ExceptionEnum;
import com.musinsa.menu.repository.MenuRepository;
import com.musinsa.menu.request.BannerRequestDTO;
import com.musinsa.menu.request.MenuAddOrUpdateRequestDTO;
import com.musinsa.menu.response.MenuResponseDTO;
import com.musinsa.menu.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public ResponseDTO selectMenu(String id){
        List<MenuResponseDTO> result;
        try {
            result = menuRepository
                    .findAll(Long.valueOf(id))
                    .stream()
                    .map(MenuResponseDTO::of)
                    .collect(Collectors.toList());

        }catch (NumberFormatException e){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }
        return new ResponseDTO().createResponseDTO(result);
    }


    @Transactional
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

    @Transactional
    public ResponseDTO updateMenu(String id, MenuAddOrUpdateRequestDTO requestDTO){

        try {
            Optional<Menu> findMenu = menuRepository.findById(Long.valueOf(id));

            if(!findMenu.isPresent()){
                throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
            }

            findMenu.get().updateMenu(requestDTO);

        }catch (NumberFormatException e){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }

        return new ResponseDTO().createResponseDTO(new HashMap<>());
    }

    @Transactional
    public ResponseDTO deleteMenu(String id){
        try {
            Optional<Menu> findMenu = menuRepository.findById(Long.valueOf(id));

            if(!findMenu.isPresent()){
                throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
            }

            menuRepository.delete(findMenu.get());

        }catch (NumberFormatException e){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }

        return new ResponseDTO().createResponseDTO(new HashMap<>());
    }

    @Transactional
    public ResponseDTO addBanner(BannerRequestDTO requestDTO){
        Optional<Menu> findMenu;
        try {
            findMenu = Optional.ofNullable(menuRepository.findByIdAndParentId(Long.valueOf(requestDTO.getMenuId()), null));

            if(!findMenu.isPresent()){
                throw new ApiException(ExceptionEnum.BANNER_NOT_ALLOW);
            }

            findMenu.get().addBanner(requestDTO);
        }catch (NumberFormatException e){
            throw new ApiException(ExceptionEnum.USER_PARAM_ERROR);
        }

        return new ResponseDTO().createResponseDTO(findMenu.get().getId());
    }

}
