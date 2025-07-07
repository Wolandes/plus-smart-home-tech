package ru.yandex.practicum.client;

import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.model.ChangeProductQuantityRequest;
import ru.yandex.practicum.model.ShoppingCartDto;
import ru.yandex.practicum.util.ValidationUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Контроллер Shopping Store
 */
@FeignClient(name = "shopping-cart")
public interface ShoppingCartClient {
    /**
     * Параметр пользователя
     */
    String USERNAME_PARAM = "username";

    /**
     * Сообщение о валидации пользователя
     */
    String VALIDATION_MESSAGE = ValidationUtil.VALIDATION_USERNAME_MESSAGE;

    /**
     * Путь удаления
     */
    String REMOVE_PATH = "/remove";

    /**
     * Путь изменения количества продуктов
     */
    String CHANGE_QUANTITY_PATH = "/change-quantity";

    /**
     * Путь бронирования продукта
     */
    String BOOKING_PATH = "/booking";

    /**
     * Получить актуальную корзину для авторизованного пользователя.
     *
     * @param userName имя пользователя
     * @return трансферная сущность корзины пользователя
     */
    @GetMapping
    ShoppingCartDto getShoppingCart(@RequestParam(USERNAME_PARAM) @NotBlank(message = VALIDATION_MESSAGE) String userName);

    /**
     * Добавить товар в корзину.
     *
     * @param userName имя пользователя
     * @param products количество определенного продукта
     * @return транферная сущность корзины пользователя
     */
    @PutMapping
    ShoppingCartDto addProductToShoppingCart(@RequestParam(USERNAME_PARAM) @NotBlank(message = VALIDATION_MESSAGE) String userName,
                                             @RequestBody Map<UUID, Long> products);

    /**
     * Деактивация корзины товаров для пользователя.
     *
     * @param userName имя пользователя
     */
    @DeleteMapping
    void deactivateCurrentShoppingCart(@RequestParam(USERNAME_PARAM) @NotBlank(message = VALIDATION_MESSAGE) String userName);

    /**
     * Удалить указанные товары из корзины пользователя.
     *
     * @param userName имя пользователя
     * @param products Список идентификаторов товаров, которые нужно удалить.
     * @return транферная сущность корзины пользователя
     */
    @PutMapping(REMOVE_PATH)
    ShoppingCartDto removeFromShoppingCart(@RequestParam(USERNAME_PARAM) @NotBlank(message = VALIDATION_MESSAGE) String userName,
                                           @RequestBody List<UUID> products);

    /**
     * Изменить количество товаров в корзине.
     *
     * @param userName имя пользователя
     * @param changeProductQuantityRequest изменение количество товаров
     * @return транферная сущность корзины пользователя
     */
    @PutMapping(CHANGE_QUANTITY_PATH)
    ShoppingCartDto changeProductQuantity(@RequestParam(USERNAME_PARAM) @NotBlank(message = VALIDATION_MESSAGE) String userName,
                                          @RequestBody ChangeProductQuantityRequest changeProductQuantityRequest);

}
