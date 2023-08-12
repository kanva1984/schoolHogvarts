package ru.hogwarts.schoolhogvarts.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolhogvarts.dto.AvatarDTO;
import ru.hogwarts.schoolhogvarts.service.AvatarService;

import java.util.List;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private  AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }


    @GetMapping
    public List<AvatarDTO> getPaginatedAvatars(
            @RequestParam int pageNumber,
            @RequestParam int pageSize
    ) {
        return avatarService.getPaginatedAvatars(pageNumber, pageSize);

    }


}
