package uz.abdurahmon.cachespringproject.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.abdurahmon.cachespringproject.exceptions.CustomNotFoundException;
import uz.abdurahmon.cachespringproject.model.entity.Group;
import uz.abdurahmon.cachespringproject.model.entity.Student;
import uz.abdurahmon.cachespringproject.model.request.GroupRequest;
import uz.abdurahmon.cachespringproject.model.response.GroupResponse;
import uz.abdurahmon.cachespringproject.model.response.StudentResponse;
import uz.abdurahmon.cachespringproject.repository.GroupRepository;
import uz.abdurahmon.cachespringproject.service.baseService.GroupService;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public List<Group> get() {
        return groupRepository.findAll();
    }

    @Override
    public Group get(UUID id) {
        return groupRepository
                .findById(
                        id
                )
                .orElseThrow(
                        () -> new CustomNotFoundException(
                                "There is no such group with this id -> " + id
                        )
                );
    }

    @Override
    public Group create(GroupRequest groupRequest) {
        Group group = groupMapper(groupRequest);

        groupRepository.saveAndFlush(group);
        return group;
    }

    private static Group groupMapper(GroupRequest groupRequest) {
        Group group = new Group();

        group.setGroupName(groupRequest.getGroupName());
        group.setGroupDescription(groupRequest.getGroupDescription());
        group.setCreateAt(LocalDateTime.now());
        return group;
    }

    @Override
    public Group update(GroupRequest groupRequest, UUID id) {
        Group group = this.get(id);

        group.setGroupName(groupRequest.getGroupName());
        group.setGroupDescription(groupRequest.getGroupDescription());

        groupRepository.saveAndFlush(group);

        return group;
    }

    @Override
    public Map<GroupResponse, List<StudentResponse>> getGroupWithStudents() {
        Map<GroupResponse, List<StudentResponse>> map = new LinkedHashMap<>();

        List<Group> groups = get();

        for (Group group : groups) {
            List<Student> students = group.getStudents();
            map.put(
                    toDto(group),
                    students
                            .stream()
                            .map(this::toDto)
                            .toList()
            );
        }

        return map;
    }

    private StudentResponse toDto(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .phoneNumber(student.getPhoneNumber())
                .address(student.getAddress())
                .build();
    }

    private GroupResponse toDto(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .groupDescription(group.getGroupDescription())
                .createAt(group.getCreateAt())
                .build();
    }
}