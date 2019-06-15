package pl.comp;

import java.io.IOException;

public interface Dao<T> {

     T read() throws Throwable,FileExeption;
     void write(T obj) throws Throwable,FileExeption;
}
