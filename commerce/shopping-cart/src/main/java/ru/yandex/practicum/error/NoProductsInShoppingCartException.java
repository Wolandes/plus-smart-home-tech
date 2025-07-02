package ru.yandex.practicum.error;

/**
 * Исключение - нет продукции в корзине
 */
public class NoProductsInShoppingCartException extends RuntimeException {
    public NoProductsInShoppingCartException(String message) {
        super(message);
    }
}
