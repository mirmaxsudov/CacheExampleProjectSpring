package uz.abdurahmon.cachespringproject.service.baseService;


import uz.abdurahmon.cachespringproject.model.entity.Group;
import uz.abdurahmon.cachespringproject.model.request.GroupRequest;
import uz.abdurahmon.cachespringproject.model.response.GroupResponse;
import uz.abdurahmon.cachespringproject.model.response.StudentResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface GroupService {
    List<Group> get();

    Group get(UUID id);

    Group create(GroupRequest groupRequest);

    Group update(GroupRequest groupRequest, UUID id);
    Map<GroupResponse, List<StudentResponse>> getGroupWithStudents();

    void delete(UUID groupId);
}