package agrigator;

import java.util.ArrayList;
import java.util.List;

public class Order implements  ActionsItem {
    public  Integer orderId;

    public Order(Integer orderId) { this.orderId = orderId; }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Client orderClient;

    public Order(Client orderClient) {
        this.orderClient = orderClient;
    }

    public Client getOrderClient() {
        return orderClient;
    }


    public void setOrderClient(Client orderClient) {
        this.orderClient = orderClient;
    }


    // Список элементов заказа
    private List<OrderItem> items;

    /**
     * Конструктор, инициализирующий пустой список элементов заказа.
     */
    public Order() {

        this.items = new ArrayList<>();
    }

    /**
     * Геттер для получения списка элементов заказа.
     *
     * @return список элементов заказа
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Сеттер для установки списка элементов заказа.
     *
     * @param items список элементов заказа
     */
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    /**
     * Метод для добавления элемента заказа в текущий заказ.
     *
     * @param item элемент заказа для добавления
     */
    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    /**
     * Метод для расчета общей стоимости заказа, учитывая цену и количество каждого продукта.
     *
     * @return общая стоимость заказа
     */
    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public String toString() {
        return "Order {" + "OrderId= " + orderId + " Client= " + orderClient + " items= " + items + '}';
    }
    /**
     * Метод для расчета скидки на общий заказ.
     *
     * @return общая стоимость заказа с учетом скидки
     */
    @Override
    public void totalSale(Order order) {

    }
    /**
     * Метод для расчета скидки на позицию в заказе OrderItem.
     *
     * @return Стоимость одного товара с учетом скидки.
     */
    @Override
    public void itemSale(OrderItem item) {

    }
}
