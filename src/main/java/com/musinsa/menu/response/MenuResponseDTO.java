package com.musinsa.menu.response;

import com.musinsa.menu.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponseDTO {

    private Long id;
    private String menuNm;
    private String menuUrl;
    private int menuOrdr;
    private int menuDp;
    private String bannerImage;
    private List<MenuResponseDTO> children;
    public static MenuResponseDTO of(Menu menu){
        return new MenuResponseDTO(
            menu.getId(),
            menu.getMenuNm(),
            menu.getMenuUrl(),
            menu.getMenuOrdr(),
            menu.getMenuDp(),
            menu.getBannerImage(),
            menu.getChildren().stream().map(MenuResponseDTO::of).collect(Collectors.toList())
        );
    }
}
