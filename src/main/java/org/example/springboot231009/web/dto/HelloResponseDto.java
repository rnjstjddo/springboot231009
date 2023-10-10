package org.example.springboot231009.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class HelloResponseDto {

    private final String name;
    private final int amount;


}
