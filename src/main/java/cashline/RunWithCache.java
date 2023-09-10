package cashline;

import entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
/**
 * Реализации паттерна Кэширования
 */
public class RunWithCache {
    public static void main(String[] args) throws ExecutionException {
        // Создание списка продуктов
        List<Product> productList = createProductList();

        // Создание кэша для продуктов
        ProductCache productCache = new ProductCache();
  //      public  static Cache<Integer, Product> orderCache;

        // Запрос продукта по ID
        int productId = 1;
        Product product = productCache.getProduct(productId, productList);
        System.out.println("Product from cache or list: " + product);

        // Запрос обновленного продукта по тому же ID
        product = productCache.getProduct(productId, productList);
        System.out.println("Updated product from cache or list: " + product);
        // Запрос цены продукта по тому же ID
        double  price = productCache.getProductPrice(productId, productList);
        System.out.println("Price product from cache or list: " + price );

        // Обновление цены продукта
        try {
            product.setPrice(15.99);
            productCache.updateProduct(product, productList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Создание списка продуктов для примера
    private static List<Product> createProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 9.99));
        productList.add(new Product(2, "Product 2", 19.99));
        productList.add(new Product(3, "Product 3", 29.99));
        return productList;
    }
}
