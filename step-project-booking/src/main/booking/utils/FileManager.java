package main.booking.utils;

import main.booking.objects.Flight;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class FileManager {
    //Сохранения БД с использованием дженериков
    public static <T> boolean upload(List<T> item, String configFiles) {
        Path path = Path.of(configFiles);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeInt(item.size());

            for (int i = 0; i < item.size(); i++) {
                objectOutputStream.writeObject(item.get(i));
            }
        } catch (IOException e) {
            System.out.println("Database save error");
            return false;
        }
        return true;
    }
    //Загрузка БД с использованием дженериков
    public static <T> boolean load(List<T> item, String configFiles) {
        Path path = Path.of(configFiles);

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            int count = objectInputStream.readInt();

            for (int i = 0; i < count; i++) {
                T object = (T) objectInputStream.readObject();
                item.add(object);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Database load error");
            return false;
        }
        return true;
    }

}
