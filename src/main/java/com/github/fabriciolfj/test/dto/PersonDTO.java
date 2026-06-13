package com.github.fabriciolfj.test.dto;

import lombok.Builder;

@Builder
public record PersonDTO(String code, String name) {
}
