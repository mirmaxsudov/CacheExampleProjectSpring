package uz.abdurahmon.cachespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.abdurahmon.cachespringproject.model.request.StudentRequest;
import uz.abdurahmon.cachespringproject.model.response.StudentResponse;
import uz.abdurahmon.cachespringproject.service.baseService.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StudentControllerUsingGraphQL {
    private final StudentService studentService;

    @QueryMapping
    public List<StudentResponse> students() {
        return studentService.get();
    }

    @QueryMapping
    public StudentResponse getStudentById(@Argument UUID id) {
        return studentService.get(id);
    }

    @MutationMapping
    public StudentResponse createStudent(@Argument StudentRequest studentRequest) {
        return studentService.create(studentRequest);
    }
}