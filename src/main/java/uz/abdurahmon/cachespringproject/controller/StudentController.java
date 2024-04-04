package uz.abdurahmon.cachespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.abdurahmon.cachespringproject.model.request.StudentRequest;
import uz.abdurahmon.cachespringproject.model.response.StudentResponse;
import uz.abdurahmon.cachespringproject.service.baseService.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    public List<StudentResponse> get() {
        return studentService.get();
    }

    @GetMapping("/{studentId}")
    public StudentResponse getById(@PathVariable("studentId") UUID studentId) {
        return studentService.get(studentId);
    }

    @PostMapping("/")
    public StudentResponse create(@RequestBody StudentRequest studentRequest) {
        return studentService.create(studentRequest);
    }

    @PostMapping("/add-student-to-group/{studentId}")
    public String addStudentToGroup(
            @PathVariable("studentId") UUID studentId,
            @RequestParam("groupId") UUID groupId
    ) {
        return studentService.addStudentToGroup(studentId, groupId);
    }

    @DeleteMapping("/delete-student-from-group/{studentId}")
    public void delete(@PathVariable("studentId") UUID studentId,
                       @RequestParam("groupId") UUID groupId) {
        studentService.deleteFromGroupById(studentId, groupId);
    }

    @PutMapping("/{studentId}")
    public StudentResponse update(@PathVariable("studentId") UUID studentId, @RequestBody StudentRequest studentRequest) {
        return studentService.update(studentRequest, studentId);
    }

    @DeleteMapping("/{studentId}")
    public void delete(@PathVariable("studentId") UUID studentId) {
        studentService.delete(studentId);
    }
}