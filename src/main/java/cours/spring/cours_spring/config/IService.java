package cours.spring.cours_spring.config;

import java.util.List;

public interface IService<T> {

    T create(T value);

    List<T> getAll();

    T update(Integer id, T value);

    T getById(Integer id);

    Boolean delete(Integer id);
}
