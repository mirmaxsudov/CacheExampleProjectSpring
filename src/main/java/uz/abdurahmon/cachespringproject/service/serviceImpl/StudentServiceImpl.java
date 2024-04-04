package uz.abdurahmon.cachespringproject.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.abdurahmon.cachespringproject.exceptions.CustomNotFoundException;
import uz.abdurahmon.cachespringproject.model.entity.Group;
import uz.abdurahmon.cachespringproject.model.entity.Student;
import uz.abdurahmon.cachespringproject.model.request.StudentRequest;
import uz.abdurahmon.cachespringproject.model.response.StudentResponse;
import uz.abdurahmon.cachespringproject.repository.StudentRepository;
import uz.abdurahmon.cachespringproject.service.baseService.GroupService;
import uz.abdurahmon.cachespringproject.service.baseService.StudentService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final GroupService groupService;
    private final StudentRepository studentRepository;

    @Override
    @Cacheable(value = "students")
    public List<StudentResponse> get() {
        return getForBackList()
                .stream()
                .map(this::getStudentResponse)
                .toList();
    }

    @Override
    @Cacheable(value = "students")
    public StudentResponse get(UUID id) {
        return getStudentResponse(getForBack(id));
    }

    @Override
    @CacheEvict(value = "students", allEntries = true)
    public StudentResponse create(StudentRequest studentRequest) {
        Student student = new Student();

        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setAddress(studentRequest.getAddress());
        student.setGroups(null);

        studentRepository.saveAndFlush(student);
        return getStudentResponse(student);
    }

    @Override
    @CachePut(value = "students", key = "#studentId")
    public StudentResponse update(StudentRequest studentRequest, UUID studentId) {
        Student student = getForBack(studentId);

        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setAddress(studentRequest.getAddress());

        studentRepository.saveAndFlush(student);
        return getStudentResponse(student);
    }

    @Override
    @CacheEvict(value = "students", key = "#studentId")
    public void delete(UUID studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public String addStudentToGroup(UUID studentId, UUID groupId) {
        Group group = groupService.getForBack(groupId);
        Student student = getForBack(studentId);
        group.getStudents().add(student);

        studentRepository.save(student);
        return "Successfully added to group";
    }

    @Override
    public void deleteFromGroupById(UUID studentId, UUID groupId) {
        Group group = groupService.getForBack(groupId);
        Student student = getForBack(studentId);
        student.getGroups().remove(group);
        studentRepository.save(student);
    }

    private StudentResponse getStudentResponse(Student student) {
        return StudentResponse
                .builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .phoneNumber(student.getPhoneNumber())
                .address(student.getAddress())
                .build();
    }


    private List<Student> getForBackList() {
        return studentRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );
    }

    private Student getForBack(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(
                        () -> new CustomNotFoundException("There is no such student with this id -> " + id)
                );
    }
}