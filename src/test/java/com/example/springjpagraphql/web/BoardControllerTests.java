package com.example.springjpagraphql.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureGraphQlTester
public class BoardControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @DisplayName("1. 게시글 1개 갖고오기")
    @Test
    void test_1(){
        this.graphQlTester.documentName("board")
                .variable("id",1L)
                .execute()
                .path("board.title")
                .entity(String.class)
                .isEqualTo("제목1");
    }

    @DisplayName("2. 게시글 목록 가져오기")
    @Test
    void test_2(){
        this.graphQlTester.documentName("boardList")
                .execute()
                .path("boardList[*].title")
                .entityList(String.class)
                .contains("제목1", "제목2", "제목3");
    }

    @DisplayName("3. 게시글 create")
    @Test
    void test_3(){
        Map<String, Object> dto = new HashMap<>();
        dto.put("title", "제목5");
        dto.put("content","내용5");

        this.graphQlTester.documentName("create")
                .variable("input",dto)
                .execute()
                .path("create.title")
                .entity(String.class)
                .isEqualTo("제목5");
    }

    @DisplayName("4. 게시글 update")
    @Test
    void test_4(){
        Map<String, Object>dto = new HashMap<>();
        dto.put("id", 4);
        dto.put("title", "제목 4가 6으로");
        dto.put("content", "내용5");

        this.graphQlTester.documentName("update")
                .variable("input",dto)
                .execute()
                .path("update.title")
                .entity(String.class)
                .isEqualTo("제목 4가 6으로");
    }

    @DisplayName("5. 게시글 delete")
    @Test
    void test_5(){
        //게시글 삭제
        this.graphQlTester.documentName("delete")
                .variable("id", 4)
                .executeAndVerify();

        this.graphQlTester.documentName("board")
                .variable("id",4)
                .execute()
                .errors();
    }
}
