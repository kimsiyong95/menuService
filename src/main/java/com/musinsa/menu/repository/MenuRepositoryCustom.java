package com.musinsa.menu.repository;

import com.musinsa.menu.domain.Menu;

import java.util.List;

public interface MenuRepositoryCustom {

    public List<Menu> findMenuWithChildren(Long id);
}
