package com.musinsa.menu.request;


import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BannerRequestDTO {

    @NotNull
    private String menuId;

    @NotNull
    private String bannerImage;
}
