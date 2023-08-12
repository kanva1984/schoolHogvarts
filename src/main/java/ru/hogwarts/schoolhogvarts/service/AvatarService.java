package ru.hogwarts.schoolhogvarts.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.schoolhogvarts.dto.AvatarDTO;
import ru.hogwarts.schoolhogvarts.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long avatarId, Long studentId);

    List<AvatarDTO> getPaginatedAvatars(int pageNumber, int pageSize);
}
