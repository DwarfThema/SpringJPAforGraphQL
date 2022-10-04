package com.example.springjpagraphql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {
    private Long id;
    private String title;
    private String content;
}
