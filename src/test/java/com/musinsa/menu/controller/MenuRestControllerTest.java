package com.musinsa.menu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.menu.request.MenuAddOrUpdateRequestDTO;
import com.musinsa.menu.service.MenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuRestController.class)
class MenuRestControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MenuService menuService;


    @DisplayName("상위 메뉴 아이디로 하위 메뉴 전부 조회 테스트")
    @Test
    public void selectMenuTest() throws Exception {
        mvc.perform(get("/menu/1"))
            .andExpect(status().isOk());
    }

    @DisplayName("메뉴 등록 테스트")
    @Test
    public void addMenu() throws Exception {
        MenuAddOrUpdateRequestDTO requestDto = createMenuRequestDto();
        
        mvc.perform(post("/menu")
                .content(objectMapper.writeValueAsString(requestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @DisplayName("메뉴 수정 테스트")
    @Test
    public void updateMenu() throws Exception {
        MenuAddOrUpdateRequestDTO requestDto = createMenuRequestDto();


        mvc.perform(put("/menu/1")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @DisplayName("메뉴 삭제 테스트")
    @Test
    public void deleteMenu() throws Exception{
        mvc.perform(delete("/menu/1"))
                .andExpect(status().isOk());
    }

    public MenuAddOrUpdateRequestDTO createMenuRequestDto(){
        return  MenuAddOrUpdateRequestDTO.builder()
                .menuNm("회원관리")
                .menuDp(1)
                .menuOrdr(1)
                .menuUrl("test.com")
                .build();
    }

}