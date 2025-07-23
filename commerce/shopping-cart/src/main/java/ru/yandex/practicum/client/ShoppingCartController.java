package ru.yandex.practicum.client;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.model.BookedProductsDto;
import ru.yandex.practicum.model.ChangeProductQuantityRequest;
import ru.yandex.practicum.model.ShoppingCartDto;
import ru.yandex.practicum.service.ShoppingCartService;
import ru.yandex.practicum.util.ValidationUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shopping-cart")
@RequiredArgsConstructor
@Validated
public class ShoppingCartController implements ShoppingCartClient {
    /**
     * Сервис корзины продуктов
     */
    private final ShoppingCartService service;

    /**
     * Получить актуальную корзину для авторизованного пользователя.
     *
     * @param userName имя пользователя
     * @return трансферная сущность корзины пользователя
     */
    @GetMapping
    @Override
    public ShoppingCartDto getShoppingCart(@NotBlank(message = ValidationUtil.VALIDATION_USERNAME_MESSAGE) String userName) {
        return service.getShoppingCart(userName);
    }

    /**
     * Добавить товар в корзину.
     *
     * @param userName имя пользователя
     * @param products количество определенного продукта
     * @return транферная сущность корзины пользователя
     */
    @PutMapping
    public ShoppingCartDto addProductToShoppingCart(@NotBlank(message = ValidationUtil.VALIDATION_USERNAME_MESSAGE) String userName,
                                                    @RequestBody Map<UUID, Long> products) {
        return service.addProductToShoppingCart(userName, products);
    }

    /**
     * Деактивация корзины товаров для пользователя.
     *
     * @param userName имя пользователя
     */
    @DeleteMapping
    public void deactivateCurrentShoppingCart(@NotBlank(message = ValidationUtil.VALIDATION_USERNAME_MESSAGE) String userName) {
        service.deactivateCurrentShoppingCart(userName);
    }

    /**
     * Удалить указанные товары из корзины пользователя.
     *
     * @param userName имя пользователя
     * @param products Список идентификаторов товаров, которые нужно удалить.
     * @return транферная сущность корзины пользователя
     */
    @PostMapping("/remove")
    public ShoppingCartDto removeFromShoppingCart(@NotBlank(message = ValidationUtil.VALIDATION_USERNAME_MESSAGE) String userName,
                                                  @RequestBody List<UUID> products) {
        return service.removeFromShoppingCart(userName, products);
    }

    /**
     * Изменить количество товаров в корзине.
     *
     * @param userName                     имя пользователя
     * @param changeProductQuantityRequest изменение количество товаров
     * @return транферная сущность корзины пользователя
     */
    @PostMapping("/change-quantity")
    public ShoppingCartDto changeProductQuantity(@NotBlank(message = ValidationUtil.VALIDATION_USERNAME_MESSAGE) String userName,
                                                 @RequestBody ChangeProductQuantityRequest changeProductQuantityRequest) {
        return service.changeProductQuantity(userName, changeProductQuantityRequest);
    }

    @Override
    public BookedProductsDto bookProducts(
            @NotBlank(message = ValidationUtil.VALIDATION_USERNAME_MESSAGE) String userName) {
        return service.bookProducts(userName);
    }
}
