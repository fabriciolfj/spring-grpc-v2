package com.github.fabriciolfj.test.mapper;

import com.github.fabriciolfj.person_server.grpc.CreatePersonRequest;
import com.github.fabriciolfj.person_server.grpc.Person;
import com.github.fabriciolfj.test.dto.PersonDTO;

public class PersonMapper {

    private PersonMapper() { }

    public static Person toEntity(final CreatePersonRequest person) {
        return Person.newBuilder()
                .setName(person.getName())
                .setCode(person.getCode())
                .build();
    }

    public static PersonDTO toDTO(final Person person) {
        return PersonDTO.builder()
                .name(person.getName())
                .code(person.getCode())
                .build();
    }
}
