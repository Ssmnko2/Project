package main.booking.DAO;

public interface MenuDAO {
    String getTitleAction();
    String getDesc();
    void doAction();
    default boolean exit() {
        return false;
    }

}
