package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.error.NoProductsInShoppingCartException;
import ru.yandex.practicum.error.NotAuthorizedUserException;
import ru.yandex.practicum.error.NotFoundException;
import ru.yandex.practicum.mapper.ShoppingCartMapper;
import ru.yandex.practicum.model.ChangeProductQuantityRequest;
import ru.yandex.practicum.model.ShoppingCart;
import ru.yandex.practicum.model.ShoppingCartDto;
import ru.yandex.practicum.repository.ShoppingCartRepository;

import java.util.*;

/**
 * Контракт для сущности корзины пользователя
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShoppingCartServiceImpl implements ShoppingCartService {
    /**
     * Хранилище корзины продуктов
     */
    private final ShoppingCartRepository repository;

    /**
     * Маппер корзины продуктов
     */
    private final ShoppingCartMapper mapper;

    /**
     * Хранилище сущности
     */

    /**
     * Получить актуальную корзину для авторизованного пользователя.
     *
     * @param userName имя пользователя
     * @return трансферная сущность корзины пользователя
     */
    @Override
    public ShoppingCartDto getShoppingCart(String userName) {
        ShoppingCart shoppingCart = findByUserName(userName);
        return mapper.toShoppingCartDto(shoppingCart);
    }

    /**
     * Добавить товар в корзину.
     *
     * @param userName имя пользователя
     * @param products количество определенного продукта
     * @return транферная сущность корзины пользователя
     */
    @Override
    @Transactional
    public ShoppingCartDto addProductToShoppingCart(String userName, Map<UUID, Long> products) {
        ShoppingCart shoppingCart = repository.findByUserName(userName)
                .orElseGet(() -> createNewShoppingCart(userName));

        Map<UUID, Long> oldProducts = Optional.ofNullable(shoppingCart.getProducts())
                .map(HashMap::new)
                .orElseGet(HashMap::new);

        oldProducts.putAll(products);
        shoppingCart.setProducts(oldProducts);
        return mapper.toShoppingCartDto(repository.save(shoppingCart));
    }

    /**
     * Деактивация корзины товаров для пользователя.
     *
     * @param userName имя пользователя
     */
    public void deactivateCurrentShoppingCart(String userName) {
        if (!repository.existsByUserName(userName)) {
            throw new NotFoundException("Корзина не найдено по имени: " +  userName);
        }
        repository.deleteByUserName(userName);
    }

    /**
     * Удалить указанные товары из корзины пользователя.
     *
     * @param userName имя пользователя
     * @param products Список идентификаторов товаров, которые нужно удалить.
     * @return транферная сущность корзины пользователя
     */
    public ShoppingCartDto removeFromShoppingCart(String userName, List<UUID> products){
        ShoppingCart cart = findByUserName(userName);
        products.forEach(cart.getProducts()::remove);
        return mapper.toShoppingCartDto(repository.save(cart));
    }

    /**
     * Изменить количество товаров в корзине.
     *
     * @param userName                     имя пользователя
     * @param changeProductQuantityRequest изменение количество товаров
     * @return транферная сущность корзины пользователя
     */
    public ShoppingCartDto changeProductQuantity(String userName, ChangeProductQuantityRequest changeProductQuantityRequest){
        ShoppingCart cart = findByUserName(userName);
        if (!cart.getProducts().containsKey(changeProductQuantityRequest.getProductId())){
            throw new NoProductsInShoppingCartException("Продукция на найдено в корзине");
        }
        cart.getProducts().put(changeProductQuantityRequest.getProductId(), changeProductQuantityRequest.getQuantity());
        return mapper.toShoppingCartDto(repository.save(cart));
    }

    private ShoppingCart findByUserName(String userName) {
        return repository.findByUserName(userName).orElseThrow(() ->
                new NotAuthorizedUserException("Не найден пользователь с именем: " + userName));

    }

    private ShoppingCart createNewShoppingCart(String userName) {
        return new ShoppingCart().toBuilder()
                .userName(userName)
                .products(new HashMap<>())
                .build();
    }
}
