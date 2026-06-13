package com.github.fabriciolfj.test.client;

import com.github.fabriciolfj.person_server.grpc.CreatePersonRequest;
import com.github.fabriciolfj.person_server.grpc.Person;
import com.github.fabriciolfj.person_server.grpc.PersonRequest;
import com.github.fabriciolfj.person_server.grpc.PersonServiceGrpc;
import com.github.fabriciolfj.test.dto.PersonDTO;
import com.github.fabriciolfj.test.mapper.PersonMapper;
import com.google.protobuf.Empty;
import org.springframework.grpc.client.GrpcChannelFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonClientImpl {

    private final PersonServiceGrpc.PersonServiceBlockingStub stub;

    public PersonClientImpl(final GrpcChannelFactory channelFactory) {
        var channel = channelFactory.createChannel("person-service");
        this.stub = PersonServiceGrpc.newBlockingStub(channel);
    }

    public PersonDTO getById(final String code) {
        final var request = PersonRequest.newBuilder()
                .setCode(code)
                .build();

        final Person person = stub.getById(request);

        return PersonMapper.toDTO(person);
    }

    public void createPerson(final PersonDTO dto) {
        var request = CreatePersonRequest.newBuilder()
                .setName(dto.name())
                .setCode(dto.code())
                .build();

        stub.createPerson(request);
    }

    public List<PersonDTO> findAll() {
        return stub
                .listPeople(Empty.newBuilder().build())
                .getPersonList()
                .stream().map(PersonMapper::toDTO)
                .toList();
    }
}
