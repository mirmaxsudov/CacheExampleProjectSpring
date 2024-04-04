package uz.abdurahmon.cachespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import uz.abdurahmon.cachespringproject.model.entity.Group;
import uz.abdurahmon.cachespringproject.model.request.GroupRequest;
import uz.abdurahmon.cachespringproject.model.response.GroupResponse;
import uz.abdurahmon.cachespringproject.model.response.StudentResponse;
import uz.abdurahmon.cachespringproject.service.baseService.GroupService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/")
    @Cacheable(value = "allGroups")
    public List<Group> getAll() {
        System.out.println("Retrieving all groups from the database.");
        return groupService.get();
    }

    @GetMapping("/{groupId}")
    @Cacheable(value = "groupById", key = "#groupId")
    public Group getById(@PathVariable("groupId") UUID groupId) {

        System.out.println("Retrieving group with ID: " + groupId + " from the database.");
        return groupService.get(groupId);
    }

    @GetMapping("/get-group-with-students")
    public Map<GroupResponse, List<StudentResponse>> getWithStudents() {
        System.out.println("Retrieving groups with students from the database.");
        return groupService.getGroupWithStudents();
    }

    @PostMapping("/")
    @CachePut(value = "allGroups", key = "#result.id")
    public Group create(@RequestBody GroupRequest groupRequest) {
        System.out.println("Creating a new group.");
        return groupService.create(groupRequest);
    }
}
