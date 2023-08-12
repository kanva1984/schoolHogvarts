package ru.hogwarts.schoolhogvarts.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.schoolhogvarts.dto.AvatarDTO;
import ru.hogwarts.schoolhogvarts.mapper.AvatarMapper;
import ru.hogwarts.schoolhogvarts.model.Avatar;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.repository.AvatarRepository;
import ru.hogwarts.schoolhogvarts.repository.StudentRepository;
import ru.hogwarts.schoolhogvarts.service.AvatarService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarServiceImpl implements AvatarService {
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    private final AvatarMapper avatarMapper;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;


    public AvatarServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository, AvatarMapper avatarMapper) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
        this.avatarMapper = avatarMapper;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.findById(studentId).orElseThrow();

        Path path = saveToDisk(student, avatarFile);
        saveToDb(student, avatarFile, path, studentId);
    }

    @Override
    public Avatar findAvatar(Long avatarId, Long studentId) {
        return avatarRepository
                .findByStudent_id(studentId)
                .orElse(new Avatar());
    }

    @Override
    public List<AvatarDTO> getPaginatedAvatars(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository
                .findAll(pageable)
                .getContent()
                .stream()
                .map(avatarMapper::mapToDTO)
                .collect(Collectors.toList());
    }


    private void saveToDb(Student student, MultipartFile avatarFile, Path filePath, Long studentId) throws IOException {
        Avatar avatar = findAvatar(student.getId(), studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);

    }

    private Path saveToDisk(Student student, MultipartFile avatarFile) throws IOException {
        Path filePath = Path.of(
                avatarsDir,
                student.getId() + "." + getExtensions(avatarFile.getOriginalFilename())
        );

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        return filePath;
    }




    private String getExtensions(String fileName) {
        return fileName.substring(
                fileName
                        .lastIndexOf(".") + 1);
    }
}



