-- =====================================================================================================================

CREATE TABLE IF NOT EXISTS products (
    product_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    product_name VARCHAR(250) NOT NULL,
    description VARCHAR(3000) NOT NULL,
    image_src VARCHAR(1000),
    quantity_state VARCHAR(20) NOT NULL,
    product_state VARCHAR(20) NOT NULL,
    product_category VARCHAR(50) NOT NULL,
    price DOUBLE PRECISION
);

COMMENT ON TABLE products IS 'Содержит информацию о продуктах';
COMMENT ON COLUMN products.product_id IS 'Уникальный идентификатор продукта.';
COMMENT ON COLUMN products.product_name IS 'Имя продукта.';
COMMENT ON COLUMN products.description IS 'Описание продукта.';
COMMENT ON COLUMN products.image_src IS 'Ссылка на картинку во внешнем хранилище или SVG.';
COMMENT ON COLUMN products.quantity_state IS 'Доступность товара';
COMMENT ON COLUMN products.product_state IS 'Статус, перечисляющий состояние остатка как свойства товара.';
COMMENT ON COLUMN products.product_category IS 'Состояние товара.';
COMMENT ON COLUMN products.price IS 'Цена товара.';

-- =====================================================================================================================
