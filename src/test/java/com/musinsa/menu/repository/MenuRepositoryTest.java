package com.musinsa.menu.repository;

import com.musinsa.menu.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @DisplayName("메뉴 repo 주입 테스트")
    @Test
    public void repoNotNullTest(){
        assertThat(menuRepository).isNotNull();
    }

    @DisplayName("메뉴 등록 테스트")
    @Test
    public void menuAddTest(){
        Menu menu = Menu.builder()
                        .menuNm("회원관리")
                        .menuDp(1)
                        .menuOrdr(1)
                        .menuUrl("test.com")
                        .build();

        Menu saveMenu = menuRepository.save(menu);


        assertThat(saveMenu).isNotNull();
        assertThat(saveMenu.getMenuNm()).isEqualTo("회원관리");
    }
}