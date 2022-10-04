package com.example.springjpagraphql.web;

import com.example.springjpagraphql.dto.BoardDto;
import com.example.springjpagraphql.entity.BoardEntity;
import com.example.springjpagraphql.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @QueryMapping
    public BoardDto board(@Argument Long id) {
        System.out.println("호출되었습니다.");
        return boardService.getBoard(id);
    }

    @QueryMapping
    public List<BoardDto> boardList() {
        return boardService.getBoardList();
    }

    @MutationMapping
    public BoardDto create(@Argument BoardDto boardInput) {
        System.out.println("BoardInput :: " + boardInput);
        return boardService.create(boardInput);
    }

    @MutationMapping
    public BoardDto update(@Argument BoardDto boardInput) {
        System.out.println("BoardInput :: " + boardInput);
        return boardService.update(boardInput);
    }

    @MutationMapping
    public Boolean delete(@Argument Long id) {
        boardService.delete(id);
        return true;
    }




}
