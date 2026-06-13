package com.github.fabriciolfj.test.controller;

import com.github.fabriciolfj.test.client.PersonClientImpl;
import com.github.fabriciolfj.test.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonClientImpl personClient;

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO getPerson(@PathVariable("code") final String code) {
        return personClient.getById(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerson(@RequestBody final PersonDTO dto) {
        personClient.createPerson(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PersonDTO> listPerson() {
        return personClient.findAll();
    }
}
