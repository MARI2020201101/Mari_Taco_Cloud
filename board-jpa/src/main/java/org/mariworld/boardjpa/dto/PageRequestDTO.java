package org.mariworld.boardjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
public class PageRequestDTO {

    private int page;
    private int size;
    public PageRequestDTO(){
        this.page=1;
        this.size=10;
    }
    public PageRequest getPageable(Sort sort){
        return PageRequest.of(page-1, size, sort);
    }

}