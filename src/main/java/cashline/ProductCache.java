package cashline;
import entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCache {
   // private final Map<Integer, Product> productCache2 = new HashMap<>();
    private final Map<Integer, Product> productCache = new HashMap<>();

    /**
     * Получение продукта из кэша или основного списка.
     *
     * @param id          ID продукта.
     * @param productList Основной список продуктов.
     * @return Продукт из кэша или null, если продукт не найден.
     */
    public Product getProduct(int id, List<Product> productList) {
        if (productCache.containsKey(id)) {
            return productCache.get(id);
        } else {
            for (Product product : productList) {
                if (product.getId() == id) {
                    productCache.put(id, product);
                    return product;
                }
            }
            return null;
        }
    }

    /**
     * Обновить информацию о продукте в кэше и основном списке.
     *
     * @param product     Обновленная информация о продукте.
     * @param productList Основной список продуктов.
     */
    public void updateProduct(Product product, List<Product> productList) {
        productCache.put(product.getId(), product);
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == product.getId()) {
                productList.set(i, product);
                break;
            }
        }
    }

    public double getProductPrice(int productId, List<Product> productList) {
        if (productCache.containsKey(productId)) {
            for (Product product : productList) {
                if (product.getId() == productId) {
                    return product.getPrice();

                }
            }
        }
        System.out.println("Product not found");
        return 0;
    }
}





//
//import com.google.common.cache.Cache;
//import com.google.common.cache.CacheBuilder;
//import entity.Product;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
//import static com.google.common.cache.CacheBuilder.newBuilder;
//
//class ProductCache {
//   // private Map<Integer, Product> cache = new HashMap<>();
//    private final Cache<Integer, Product> cache;  //Interface Cache<K,V>
//
//    public ProductCache() {
//        CacheBuilder<Object, Object> objectObjectCacheBuilder = newBuilder();
//        objectObjectCacheBuilder.expireAfterWrite(60, TimeUnit.SECONDS);
//        objectObjectCacheBuilder.maximumSize(100);//.expireAfterWrite(60, TimeUnit.SECONDS) // Время жизни элемента в кэше
//// Время жизни элемента в кэше
//        cache = objectObjectCacheBuilder // Максимальное количество элементов в кэше
//                .build();
//    }
//
//    /**
//     * Получить продукт из кэша или основного списка.
//     *
//     * @param id - ID продукта.
//     * @param productList Основной список продуктов.
//     * @return Продукт из кэша или null, если продукт не найден.
//     */
//    public Product getProduct(int id, List<Product> productList) {
//        Product product;
//        Object b = (Integer)id;
//        if (Objects.equals(cache.getIfPresent(b), b ) {
//            product = cache.getIfPresent(id);
//            return product;
//        }
//        for (Product x : productList) {
//                if (x.getId() == id) {
//                    cache.put(id, product);
//                    return product;
//                }
//            }
//            return null;
//        }
//
//
//    /**
//     * Получить цену продукт по id из кэша или основного списка.
//     *
//     * @param id - ID продукта.
//     * @param productList Основной список продуктов.
//     * @return Продукт из кэша или null, если продукт не найден.
//     */
//    public double getProductPrice(int id, List<Product> productList) {
//        Product product1;
//        if (cache.getIfPresent(product1.getId()) {
//            for (Product x: productList) {
//                if (x.getId() == id) {
//                    return product1.getPrice();
//                } else {
//                    cache.put(id, product1);
//                    return product1.getPrice();
//                }
//            }
//            //return Double.parseDouble(null);
//        }
//        return 0;
//    }
//    /**
//     * Обновить информацию о продукте в кэше и основном списке.
//     *
//     * @param product      Обновленная информация о продукте.
//     * @param productList Основной список продуктов.
//     */
//    public void updateProduct (Product product, List < Product > productList){
//        cache.put(product.getId(), product);
//        for (int i = 0; i < productList.size(); i++) {
//            if (productList.get(i).getId() == product.getId()) {
//                productList.set(i, product);
//                break;
//            }
//        }
//    }
//}

//    public Product getProduct(int id, List<Product> productList) {
//        Product product = productCache.putIfAbsent(id);
//        if (product != null) {
//            product = dbDataLoader(id);
//            productCache.put(id, product);
//            return product;
//        } else {
//            return null;
//        }
//    }
//
//
//    private Product dbDataLoader(int id) {
//        // Загрузка заказа из базы данных
//        return null;
//    }

//    public void updateProduct(Product product, List<Product> productList) {
//        // Обновление заказа в базе данных
//        productCache.put(product.getId(), product);
//    }


//    public double getProductPrice(int productId, List<Product> productList) {
//        return 0;
//    }

//    public void updateProduct(Product product) {
//        System.out.println( "_"+ product + "Updated");
//    }
//}
