package agrigator;

import entity.User;

public class Client extends User {
    public String phone;

    public Client(int id, String name, String phone) {
        super(id, name);
        this.phone = phone;
    }

    /**
     * Класс Client наследует поля id и name клиента от User
     * @param id
     * @param name
     */
    public Client(int id, String name) {
        super(id, name);
    }

    /** Метод получить номер клиента
     * @return String phone
     */
    public String getPhone() {
        return phone;
    }

    /** Метод установить номер клиента
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" + "ClientId= " + id + " ClientName= " + name + " phone= " + phone + '\'' + '}';
    }
}
