package uz.abdurahmon.cachespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.abdurahmon.cachespringproject.model.response.GroupResponse;
import uz.abdurahmon.cachespringproject.service.baseService.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupControllerUsingGraphQL {
    private final GroupService groupService;

    @QueryMapping
    public List<GroupResponse> groups() {
        return groupService.get();
    }

}