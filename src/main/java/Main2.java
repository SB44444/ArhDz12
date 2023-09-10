
import agrigator.Client;
import agrigator.Order;
import agrigator.OrderItem;
import repository.OrderRepositoryImpl;
import repository.OrderRepository;
import entity.Product;

import java.sql.SQLException;


public class Main2 {

    public static void main(String[] args) {
        try {
            // Инициализация репозитория и базы данных.

            OrderRepositoryImpl productMenu1 = new OrderRepositoryImpl("jdbc:sqlite:shop.db");

            // Создание экземпляра заказа.

            OrderRepository service1 = new OrderRepository(productMenu1);

            // Создание экземпляра клиента.
            Client client1 = new Client(1, "Den", "+74546");

            // Создание и инициализация продукта.
            Product product1 = new Product(1, "Пицца - Peyronie's", 599.90);
            Product product2 = new Product(2, "Пицца - light", 499.90);
            Product product3 = new Product(3, "Пицца - Chile", 459.90);

            // Создание нового заказа.
            Order order1 = new Order();
            // Добавление нового клиента
            order1.setOrderClient(client1);
            // Добавление ID нового заказа
            order1.setOrderId(3);
            // Добавление продукта в заказ.
            OrderItem orderItem1 = new OrderItem(product1,2);
            order1.addItem(orderItem1);
            OrderItem orderItem2 = new OrderItem(product2,1);
            order1.addItem(orderItem2);
            OrderItem orderItem3 = new OrderItem(product3,2);
            order1.addItem(orderItem3);

            // Сохранение заказа в базе данных
            service1.createOrder(order1);
            // Вывод текущего заказа
            System.out.println("Текущий заказ:");
            System.out.println(order1);

            // Получение и вывод последнего заказа из базы данных.
            Order lastOrder = productMenu1.getLastOrder();
            if (lastOrder != null) {
                System.out.println("Последний сохраненный заказ:");
                System.out.println(lastOrder);
            } else {
                System.out.println("В базе данных нет заказов.");
            }
            // Получение и вывод заказа по ID из базы данных
            Order OrderById = productMenu1.getById(1);
            if (OrderById != null) {
                System.out.println("Заказ, где id == 1:");
                System.out.println(lastOrder);
            } else {
                System.out.println("В базе данных нет заказов.");
            }

            // Получение и вывод всех заказов из базы данных

            Order allOrder = productMenu1.getAllOrder();
            if (allOrder != null) {
                System.out.println("Список всех сохранённых заказов:");
                System.out.println(allOrder);
            } else {
                System.out.println("В базе данных нет заказов.");
            }

        } catch (SQLException e) {  // Обработка исключений, связанных с работой базы данных.

            e.printStackTrace();
        }
    }
}







/*
Product(“Apple”, 60);
Product product2 = new Product(“Orange”, 80);
// Создание и добавление товаров в заказ.
OrderItem orderItem1 = new OrderItem(product1, 2);
OrderItem orderItem2 = new OrderItem(product2, 4);
Order order = service.saveOrder(client1, orderItem1, orderItem2);
System.out.println("New order with id: " + order.getId());
} catch (SQLException e) {
e.printStackTrace();
}
}
}
*/