package com.musinsa.menu.repository;

import com.musinsa.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>{

    @Query("select m from Menu m where m.id = :id")
    public List<Menu> findAll(Long id);

    public Menu findByIdAndParentId(Long id, Long parentId);
}
