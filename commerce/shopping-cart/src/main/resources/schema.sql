-- =====================================================================================================================
CREATE TABLE IF NOT EXISTS cart (
    id    UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_name        varchar(200) NOT NULL
);

COMMENT ON TABLE cart IS 'Содержит информацию о корзине';
COMMENT ON COLUMN cart.id IS 'Уникальный идентификатор корзины.';
COMMENT ON COLUMN cart.user_name IS 'Имя пользователя.';

-- =====================================================================================================================

CREATE TABLE IF NOT EXISTS cart_products (
    cart_id          UUID NOT NULL,
    product_id       UUID NOT NULL,
    quantity         BIGINT,
    CONSTRAINT cart_products_pk PRIMARY KEY (cart_id, product_id),
    CONSTRAINT cart_products_cart_fk FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE
);

COMMENT ON TABLE cart_products IS 'Содержит информацию о содержимом корзины';
COMMENT ON COLUMN cart_products.cart_id IS 'Уникальный идентификатор корзины.';
COMMENT ON COLUMN cart_products.product_id IS 'Уникальный идентификатор продукта.';
COMMENT ON COLUMN cart_products.quantity IS 'Количеств продукта.';

-- =====================================================================================================================
