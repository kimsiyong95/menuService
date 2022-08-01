package com.musinsa.menu.request;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class BannerRequestDTO {

    @NotNull
    private String menuId;

    @NotNull
    private String bannerImage;
}
