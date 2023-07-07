package demo.project.twitter.abstractclass;

import demo.project.twitter.model.tweet.Tweet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract interface FunctionAbstract <T>{


    T saveOne(T t);
    Optional<T> getById(Long id);
    boolean existsById(Long id);

    List<T> getAll();


}
