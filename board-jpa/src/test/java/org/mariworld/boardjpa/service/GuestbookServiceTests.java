package org.mariworld.boardjpa.service;

import org.junit.jupiter.api.Test;
import org.mariworld.boardjpa.dto.GuestbookDTO;
import org.mariworld.boardjpa.dto.PageRequestDTO;
import org.mariworld.boardjpa.entity.Guestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    GuestbookService guestbookService;

    @Test
    public void registerTest(){
        GuestbookDTO guestbookDTO
                = GuestbookDTO.builder()
                                .title("서비스 테스트")
                                .content("서비스 테스트 내용")
                                .writer("작성자1")
                                .build();
        guestbookService.register(guestbookDTO);
    }
    @Test
    public void getListTest(){
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder().size(10).page(2).build();

        guestbookService.getList(pageRequestDTO)
                .getDtoList()
                .forEach(System.out::println);

    }
}
