package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.model.Product;

import java.util.UUID;

/**
 * Контракт хранилища данных для сущности "Продукт".
 */
public interface ProductRepository extends JpaRepository<Product, UUID> {
    /**
     *
     */
}
