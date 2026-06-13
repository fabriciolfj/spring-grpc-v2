package com.github.fabriciolfj.test.server;

import com.github.fabriciolfj.person_server.grpc.*;
import com.github.fabriciolfj.test.mapper.PersonMapper;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {

    private static List<Person> persons = new ArrayList<>();

    static {
        for(int i = 0; i < 3; i++) {
            persons.add(Person.newBuilder()
                            .setCode("00" + i)
                            .setName("test" + i)
                    .build());
        }
    }

    @Override
    public void createPerson(final CreatePersonRequest request, final StreamObserver<Person> responseObserver) {
        final var person = PersonMapper.toEntity(request);
        persons.add(person);

        responseObserver.onNext(person);
        responseObserver.onCompleted();
    }

    @Override
    public void getById(PersonRequest request, StreamObserver<Person> responseObserver) {
        final var person = persons.stream().filter(p -> p.getCode().equals(request.getCode()))
                .findFirst().orElseGet(() -> Person.newBuilder().build());

        responseObserver.onNext(person);
        responseObserver.onCompleted();
    }

    @Override
    public void listPeople(Empty request, StreamObserver<PeopleListResponse> responseObserver) {
        responseObserver.onNext(PeopleListResponse.newBuilder().addAllPerson(persons).build());
        responseObserver.onCompleted();
    }
}
