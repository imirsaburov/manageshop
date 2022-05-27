package uz.imirsaburov.manage.shop.service;

import org.springframework.web.multipart.MultipartFile;
import uz.imirsaburov.manage.shop.dto.file.CustomResourceWrapper;
import uz.imirsaburov.manage.shop.dto.file.FileDto;

public interface FileService {

    String GLOBAL = "./static";
    String FILE = GLOBAL + "/file";

    FileDto upload(MultipartFile multipartFile);

    CustomResourceWrapper get(Long id);

    FileDto getForInfo(Long id);

    Boolean delete(Long id);
}
