package uz.abdurahmon.cachespringproject.service.baseService;

import uz.abdurahmon.cachespringproject.model.request.StudentRequest;
import uz.abdurahmon.cachespringproject.model.response.StudentResponse;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<StudentResponse> get();
    StudentResponse get(UUID id);

    StudentResponse create(StudentRequest studentRequest);

    StudentResponse update(StudentRequest studentRequest, UUID studentId);

    void delete(UUID studentId);

    String addStudentToGroup(UUID studentId, UUID groupId);

    void deleteFromGroupById(UUID studentId, UUID groupId);
}