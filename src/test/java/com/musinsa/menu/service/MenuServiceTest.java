package com.musinsa.menu.service;

import com.musinsa.menu.domain.Menu;
import com.musinsa.menu.exception.ApiException;
import com.musinsa.menu.exception.ExceptionEnum;
import com.musinsa.menu.repository.MenuRepository;
import com.musinsa.menu.request.BannerRequestDTO;
import com.musinsa.menu.request.MenuAddOrUpdateRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @DisplayName("메뉴 조회 Exception 테스트")
    @Test
    public void selectMenuExceptionTest(){
        ApiException error = Assertions.assertThrows(ApiException.class, () -> {
            menuService.selectMenu("A");
        });

        assertThat(error.getError().getCode()).isEqualTo(ExceptionEnum.USER_PARAM_ERROR.getCode());

    }

    @DisplayName("메뉴 등록 Exception 테스트")
    @Test
    public void addMenuExceptionTest(){

        when(menuRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        ApiException error = Assertions.assertThrows(ApiException.class, () -> {
            menuService.addMenu(MenuAddOrUpdateRequestDTO
                    .builder()
                    .parentId("3")
                    .build());
        });

        assertThat(error.getError().getCode()).isEqualTo(ExceptionEnum.USER_PARAM_ERROR.getCode());

    }


    @DisplayName("배너 등록 Exception 테스트")
    @Test
    public void addBannerExceptionTest(){

        when(menuRepository.findByIdAndParentId(any(), any())).thenReturn(null);

        ApiException error = Assertions.assertThrows(ApiException.class, () -> {
            menuService.addBanner(BannerRequestDTO
                            .builder()
                            .menuId("1")
                            .bannerImage("image")
                            .build()
            );
        });

        assertThat(error.getError().getCode()).isEqualTo(ExceptionEnum.BANNER_NOT_ALLOW.getCode());
    }

}