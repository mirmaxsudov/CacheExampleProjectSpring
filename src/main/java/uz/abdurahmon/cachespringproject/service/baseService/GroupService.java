package uz.abdurahmon.cachespringproject.service.baseService;


import uz.abdurahmon.cachespringproject.model.entity.Group;
import uz.abdurahmon.cachespringproject.model.request.GroupRequest;
import uz.abdurahmon.cachespringproject.model.response.GroupResponse;
import uz.abdurahmon.cachespringproject.model.response.StudentResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface GroupService {
    List<GroupResponse> get();

    GroupResponse get(UUID id);

    GroupResponse create(GroupRequest groupRequest);

    GroupResponse update(GroupRequest groupRequest, UUID id);
    Map<UUID, List<StudentResponse>> getGroupWithStudents();

    void delete(UUID groupId);

    Group getForBack(UUID id);
}