package org.mariworld.boardjpa.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mariworld.boardjpa.dto.GuestbookDTO;
import org.mariworld.boardjpa.dto.PageRequestDTO;
import org.mariworld.boardjpa.dto.PageResultDTO;
import org.mariworld.boardjpa.entity.Guestbook;
import org.mariworld.boardjpa.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{
    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO dto) {
        Guestbook guestbook = dtoToEntity(dto);
        guestbookRepository.save(guestbook);
        return guestbook.getGno();
    }

    public PageResultDTO<GuestbookDTO,Guestbook> getList(PageRequestDTO pageRequestDTO){
        PageRequest pageRequest =pageRequestDTO.getPageable(Sort.by("gno").descending());
        Page<Guestbook> result =guestbookRepository.findAll(pageRequest);
        Function<Guestbook,GuestbookDTO> fn = i->entityToDto(i);
        return new PageResultDTO<>(result,fn);
    }
}
