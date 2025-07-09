package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.model.ShoppingCart;

import java.util.Optional;
import java.util.UUID;

/**
 * Контракт хранилища данных для сущности "Корзины продуктов".
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
    /**
     * Поиск по имени пользователя
     *
     * @param userName имя пользователя
     * @return сущность корзины продукта
     */
    Optional<ShoppingCart> findByUserName(String userName);

    /**
     * Удаление пользователя по имени
     *
     * @param userName имя пользователя
     */
    @Transactional
    @Modifying
    void deleteByUserName(String userName);

    /**
     * Наличие корзины у пользователя
     *
     * @param userName имя пользователя
     * @return есть ли корзина у пользователя
     */
    boolean existsByUserName(String userName);
}
