package com.musinsa.menu.domain;


import com.musinsa.menu.request.MenuAddOrUpdateRequestDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuNm;
    private String menuUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Menu parent;

    private int menuOrdr;
    private int menuDp;
    private String bannerImage;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Menu> children = new ArrayList<>();

    public static Menu createMenu(MenuAddOrUpdateRequestDTO requestDTO, Menu parentMenu){
//        int ordr = 1;
//        int depth = 1;
//
//        if(parentMenu != null){
//           if(parentMenu.getChildren().size() != 0){
//               for(Menu childMenu : parentMenu.getChildren()){
//                   if(childMenu.getMenuOrdr() > ordr){
//                       ordr = childMenu.getMenuOrdr();
//                   }
//               }
//               ordr++;
//           }
//
//           depth = parentMenu.getMenuDepth() + 1;
//        }


        return Menu.builder()
                .menuNm(requestDTO.getMenuNm())
                .menuOrdr(requestDTO.getMenuOrdr())
                .menuDp(requestDTO.getMenuDp())
                .menuUrl(requestDTO.getMenuUrl())
                .parent(parentMenu)
                .build();
    }
}
