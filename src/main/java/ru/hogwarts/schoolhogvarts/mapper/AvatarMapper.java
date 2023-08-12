package ru.hogwarts.schoolhogvarts.mapper;

import org.springframework.stereotype.Component;
import ru.hogwarts.schoolhogvarts.dto.AvatarDTO;
import ru.hogwarts.schoolhogvarts.model.Avatar;

@Component

public class AvatarMapper {

    public AvatarDTO mapToDTO(Avatar avatar) {
        return new AvatarDTO(
                avatar.getId(),
                avatar.getFilePath(),
                avatar.getFileSize(),
                avatar.getMediaType(),
                avatar.getStudent().getId()
        );
    }
}
