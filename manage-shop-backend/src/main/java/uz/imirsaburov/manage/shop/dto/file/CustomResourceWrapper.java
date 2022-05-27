package uz.imirsaburov.manage.shop.dto.file;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.core.io.Resource;
import uz.imirsaburov.manage.shop.base.BaseDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomResourceWrapper extends BaseDTO {

    private String originalName;
    private String type;

    private final Resource resource;

    public CustomResourceWrapper(Resource resource) {
        this.resource = resource;
    }

    public boolean exists() {
        return resource.exists();
    }
}
