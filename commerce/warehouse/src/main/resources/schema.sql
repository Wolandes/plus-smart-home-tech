-- =====================================================================================================================

CREATE TABLE IF NOT EXISTS warehouse_product (
    product_id       UUID PRIMARY KEY,
    fragile          boolean,
    width            DOUBLE PRECISION,
    height           DOUBLE PRECISION,
    depth            DOUBLE PRECISION,
    weight           DOUBLE PRECISION,
    quantity         BIGINT
);

COMMENT ON TABLE warehouse_product IS 'Содержит информацию о складе';
COMMENT ON COLUMN warehouse_product.product_id IS 'Уникальный идентификатор продукта.';
COMMENT ON COLUMN warehouse_product.fragile IS 'Хрупкость товара.';
COMMENT ON COLUMN warehouse_product.width IS 'Ширина товара.';
COMMENT ON COLUMN warehouse_product.height IS 'Высота товара.';
COMMENT ON COLUMN warehouse_product.depth IS 'Глубина товара.';
COMMENT ON COLUMN warehouse_product.weight IS 'Вес товара.';
COMMENT ON COLUMN warehouse_product.quantity IS 'Количество товара.';

-- =====================================================================================================================