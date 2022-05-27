package uz.imirsaburov.manage.shop.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.FileEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto extends BaseDTO {

    private Long id;
    private String name;
    private String type;
    private Long size;

    public static FileDto getDto(FileEntity entity) {
        return new FileDto(entity.getId(), entity.getName(), entity.getType(), entity.getSize());
    }

    public static FileEntity getEntity(MultipartFile file) {
        return new FileEntity(file.getOriginalFilename(), file.getContentType(), file.getSize());
    }

    public static FileEntity getEntity(MultipartFile file, FileEntity fileEntity) {
        fileEntity.setName(file.getName());
        fileEntity.setType(file.getContentType());
        fileEntity.setSize(file.getSize());
        return fileEntity;
    }
}
