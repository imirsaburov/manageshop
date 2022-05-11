package uz.imirsaburov.manage.shop.base;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseFilterPageable extends BaseFilter {
    private int page;
    private int size;

    public static Pageable getPageAble(BaseFilterPageable filter) {
        if (filter.getSize() < 1 || filter.getSize() > 50)
            filter.setSize(10);

        if (filter.getPage() < 0)
            filter.setPage(0);

        if (filter.getSortField() == null)
            filter.setSortField("id");

        if (filter.getDirection() == null)
            filter.setDirection(Sort.Direction.DESC);

        return PageRequest.of(filter.getPage(), filter.getSize(), filter.getDirection(), filter.getSortField());
    }
}
