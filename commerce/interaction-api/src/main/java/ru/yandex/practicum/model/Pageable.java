package ru.yandex.practicum.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pageable {
    @Min(value = 0, message = "Минимальная страница не может быть меньше 0")
    private int page;

    @Positive(message = "Размер страницы не может быть больше 100")
    private int size = 100;

    @NotNull
    private List<String> sort = Collections.emptyList();

    public PageRequest toPageRequest() {
        Sort sortObj = parseSort();
        return PageRequest.of(page, size, sortObj);
    }

    private Sort parseSort() {
        if (sort == null || sort.isEmpty()) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = new ArrayList<>();
        for (String entry : sort) {
            String[] parts = entry.split(",");
            String property = parts[0].trim();
            Sort.Direction direction = (parts.length > 1 && parts[1].equalsIgnoreCase("desc"))
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            orders.add(new Sort.Order(direction, property));
        }
        return Sort.by(orders);
    }
}
