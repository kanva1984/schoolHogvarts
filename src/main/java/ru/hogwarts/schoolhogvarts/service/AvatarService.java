package ru.hogwarts.schoolhogvarts.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.schoolhogvarts.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long avatarId);
}
