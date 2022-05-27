package uz.imirsaburov.manage.shop.service.impl;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.imirsaburov.manage.shop.dto.file.CustomResourceWrapper;
import uz.imirsaburov.manage.shop.dto.file.FileDto;
import uz.imirsaburov.manage.shop.entity.FileEntity;
import uz.imirsaburov.manage.shop.repository.FileRepository;
import uz.imirsaburov.manage.shop.service.FileService;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    @Transactional
    public FileDto upload(MultipartFile multipartFile) {
        try {

            FileEntity entity = FileDto.getEntity(multipartFile);

            fileRepository.save(entity);

            fileWrite(multipartFile, getPath(FILE, entity));

            return FileDto.getDto(entity);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CustomResourceWrapper get(Long id) {
        FileEntity entity = findById(id);

        CustomResourceWrapper fileAsResource = getFileAsResource(getPath(FILE, entity));

        fileAsResource.setOriginalName(entity.getName());
        fileAsResource.setType(entity.getType());

        return fileAsResource;
    }


    @Override
    public FileDto getForInfo(Long id) {
        return FileDto.getDto(findById(id));
    }

    protected FileEntity findById(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new RuntimeException("file not found"));
    }


    protected void fileWrite(MultipartFile multipartFile, String pathWithFilename) {

        File privateFile = new File(pathWithFilename);

        try {

            var res = privateFile.getParentFile().mkdirs();
            res = privateFile.createNewFile();

            FileCopyUtils.copy(multipartFile.getInputStream(), Files.newOutputStream(Path.of(pathWithFilename)));

        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }

    }

    protected CustomResourceWrapper getFileAsResource(String path) {

        File file = new File(path);

        CustomResourceWrapper resource;

        try {
            resource = new CustomResourceWrapper(new UrlResource(file.toPath().toUri()));

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not Found");
            }

        } catch (Throwable e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            throw new RuntimeException("File not Found");
        }

    }

    protected String getPath(String pathType, FileEntity file) {

        return pathType + "/" + file.getId();
    }

    @Override
    public Boolean delete(Long id) {
        try {
            FileEntity byId = findById(id);
            new File(getPath(FILE, byId)).delete();
            fileRepository.delete(byId);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }
}
