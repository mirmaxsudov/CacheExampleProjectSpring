package uz.abdurahmon.cachespringproject.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    @Cacheable(value = "groups")
    public List<GroupResponse> get() {
        return getForBackList()
                .stream()
                .map(this::getGroupResponse)
                .toList();
    }

    @Override
    public Group getForBack(UUID id) {
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
    @Cacheable(value = "groups", key = "#id")
    public GroupResponse get(UUID id) {
        Group forBack = getForBack(id);
        return getGroupResponse(forBack);
    }

    @Override
    @CacheEvict(value = "groups", allEntries = true)
    public GroupResponse create(GroupRequest groupRequest) {
        Group group = groupMapper(groupRequest);
        groupRepository.saveAndFlush(group);
        return getGroupResponse(group);
    }

    private static Group groupMapper(GroupRequest groupRequest) {
        Group group = new Group();

        group.setGroupName(groupRequest.getGroupName());
        group.setGroupDescription(groupRequest.getGroupDescription());
        group.setCreateAt(LocalDateTime.now());
        return group;
    }

    @Override
    @CachePut(value = "groups", key = "#id")
    public GroupResponse update(GroupRequest groupRequest, UUID id) {
        Group group = getForBack(id);

        group.setGroupName(groupRequest.getGroupName());
        group.setGroupDescription(groupRequest.getGroupDescription());

        groupRepository.saveAndFlush(group);

        return getGroupResponse(group);
    }

    private GroupResponse getGroupResponse(Group group) {
        return GroupResponse
                .builder()
                .groupName(group.getGroupName())
                .groupDescription(group.getGroupDescription())
                .createAt(group.getCreateAt())
                .id(group.getId())
                .build();
    }

    @Override
    public Map<UUID, List<StudentResponse>> getGroupWithStudents() {
        Map<UUID, List<StudentResponse>> map = new LinkedHashMap<>();

        List<Group> groups = getForBackList();

        for (Group group : groups) {
            Set<Student> students = group.getStudents();
            map.put(
                    group.getId(),
                    students
                            .stream()
                            .map(this::toDto)
                            .toList()
            );
        }

        return map;
    }

    private List<Group> getForBackList() {
        return groupRepository.findAll();
    }

    @Override
    @CacheEvict(value = "groups", allEntries = true, key = "#groupId")
    public void delete(UUID groupId) {
        try {
            groupRepository.deleteById(groupId);
        } catch (Exception e) {
            throw new CustomNotFoundException("There is no such group with this id -> " + groupId);
        }
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