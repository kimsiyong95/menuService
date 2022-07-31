package com.musinsa.menu.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MenuAddOrUpdateRequestDTO {

    @NotNull
    private String menuNm;

    @NotNull
    private String menuUrl;

    private String parentId;

    @NotNull
    private int menuOrdr;

    @NotNull
    private int menuDp;
}
