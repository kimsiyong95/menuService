package com.musinsa.menu.domain;


import com.musinsa.menu.request.BannerRequestDTO;
import com.musinsa.menu.request.MenuAddOrUpdateRequestDTO;
import lombok.*;
import org.hibernate.annotations.BatchSize;

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

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Menu> children = new ArrayList<>();

    public static Menu createMenu(MenuAddOrUpdateRequestDTO requestDTO, Menu parentMenu){
        return Menu.builder()
                .menuNm(requestDTO.getMenuNm())
                .menuOrdr(requestDTO.getMenuOrdr())
                .menuDp(requestDTO.getMenuDp())
                .menuUrl(requestDTO.getMenuUrl())
                .parent(parentMenu)
                .build();
    }

    public void updateMenu(MenuAddOrUpdateRequestDTO requestDTO){
        this.menuNm = requestDTO.getMenuNm();
        this.menuDp = requestDTO.getMenuDp();
        this.menuOrdr = requestDTO.getMenuOrdr();
        this.menuUrl = requestDTO.getMenuUrl();

        if(requestDTO.getParentId() != null){
            this.parent.id = Long.valueOf(requestDTO.getParentId());
            this.bannerImage = null; // 부모가 있다면 최상위 메뉴가 아니기때문에 배너 제거
        }
    }

    public void addBanner(BannerRequestDTO requestDTO){
        this.bannerImage = requestDTO.getBannerImage();
    }

}
