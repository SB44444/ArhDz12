package repository;
import agrigator.Order;
import java.sql.SQLException;

public class OrderRepository {

    // Репозиторий для работы с заказами в базе данных
    private OrderRepositoryImpl repository;

    /**
     * Конструктор класса OrderService.
     *
     * @param repository Репозиторий для работы с заказами в базе данных.
     */
    public OrderRepository(OrderRepositoryImpl repository) {
        this.repository = repository;
    }

    /**
     * Метод для создания и сохранения заказа в базе данных.
     *
     * @param order Объект заказа, который необходимо сохранить.
     * @throws SQLException Если возникнет проблема с базой данных.
     */
    public void createOrder(Order order) throws SQLException {  // Метод сохранения заказа
        repository.saveOrder(order);
    }


}
















//    /**
//     * Метод для получения заказа по его идентификатору.
//     *
//     * @param id Идентификатор заказа.
//     * @return Объект заказа или null, если заказ не найден.
//     * @throws SQLException Если возникнет проблема с базой данных.
//     */
//    public Order getOrderById(int id) throws SQLException {
//        return repository.getById(id);
//    }