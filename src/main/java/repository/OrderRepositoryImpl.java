package repository;


import agrigator.Order;
import agrigator.OrderItem;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Класс OrderRepository представляет репозиторий для работы с заказами.
 * Используется для интеграции бизнес-логики приложения с базой данных заказов.
 */


public class OrderRepositoryImpl {

    // Соединение с базой данных
    private final Connection connection;

    /**
     * @param databaseUrl URL базы данных для подключения
     */
    public OrderRepositoryImpl(String databaseUrl) throws SQLException {
        connection = DriverManager.getConnection(databaseUrl);
        initDatabase();
    }

    /**
     * Инициализация таблиц в базе данных (если они не существуют).
     */
    private void initDatabase() throws SQLException {
        Statement statement = connection.createStatement();
        // Создание таблицы продуктов, если она еще не создана
        statement.execute("CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY, name TEXT, price REAL)");
        // Создание таблицы заказов, если она еще не создана
        statement.execute("CREATE TABLE IF NOT EXISTS orders (id INTEGER PRIMARY KEY AUTOINCREMENT, totalAmount REAL)");
        // Создание таблицы элементов заказа, если она еще не создана
        statement.execute("CREATE TABLE IF NOT EXISTS order_items (orderId INTEGER, productId INTEGER, quantity INTEGER)");
        statement.close();
    }

    /**
     * Сохранение информации о заказе в базе данных.
     *
     * @param order Заказ, который необходимо сохранить
     */
    public void saveOrder(Order order) throws SQLException {
        connection.setAutoCommit(false);

        // Вставка данных о заказе в таблицу заказов
        PreparedStatement orderStmt = connection.prepareStatement("INSERT INTO orders(totalAmount) VALUES (?)");
        orderStmt.setDouble(1, order.getTotalPrice());
        orderStmt.executeUpdate();
        ResultSet generatedKeys = orderStmt.getGeneratedKeys();
        int orderId = generatedKeys.getInt(1);
        generatedKeys.close();
        orderStmt.close();

        // Вставка данных об элементах заказа в таблицу элементов заказа
        PreparedStatement itemStmt = connection.prepareStatement("INSERT INTO order_items(orderId, productId, quantity) VALUES (?, ?, ?)");
        for (OrderItem item : order.getItems()) {
            itemStmt.setInt(1, orderId);
            itemStmt.setInt(2, item.getProduct().getId());
            itemStmt.setInt(3, item.getQuantity());
            itemStmt.executeUpdate();
        }
        itemStmt.close();

        connection.commit();
        connection.setAutoCommit(true);
    }

    /**
     * Извлечение последнего заказа из базы данных.
     *
     * @return Последний сохраненный заказ или null, если заказов нет
     */
    public Order getLastOrder() throws SQLException {
        String query = "SELECT * FROM orders ORDER BY id DESC LIMIT 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int orderId = resultSet.getInt("id");
            Order order = new Order();
            order.setItems(getOrderItemsByOrderId(orderId));

            return order;
        }

        return null;
    }

    /**
     * Извлечение заказа из базы данных по его идентификатору.
     *
     * @param id Идентификатор заказа
     * @return Заказ с указанным идентификатором или null, если такого заказа нет
     */
    public Order getById(int id) throws SQLException {
        Order order = new Order();
        order.setItems(getOrderItemsByOrderId(id));
        return order;
    }

    /**
     * Вспомогательный метод для извлечения элементов заказа по идентификатору заказа.
     *
     * @param orderId Идентификатор заказа
     * @return Список элементов указанного заказа
     */
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM order_items WHERE orderId=?");
        stmt.setInt(1, orderId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int productId = rs.getInt("productId");
            int quantity = rs.getInt("quantity");
            Product product = new Product(productId, "Product " + productId, 10.0);
            orderItems.add(new OrderItem(product, quantity));
        }
        rs.close();
        stmt.close();

        return orderItems;
    }
    /**
     * Извлечение последнего заказа из базы данных.
     *
     * @return Последний сохраненный заказ или null, если заказов нет
     */
    public Order getAllOrder() throws SQLException {
        String query = "SELECT * FROM orders";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
           int orderId = resultSet.getInt("id");
            Order order = new Order();
            order.setItems(getOrderItemsByOrderId(orderId));

            return order;
        }

        return null;
    }

}


