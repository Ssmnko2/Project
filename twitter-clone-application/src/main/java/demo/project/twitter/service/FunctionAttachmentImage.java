package demo.project.twitter.service;

import demo.project.twitter.model.tweet.AttachmentImage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FunctionAttachmentImage {

/* Дальнейший код приведен для примера.
        В данном интерфейсе декларируются методы для их реализации в Service.
        Их количество, семантика и логическая нагрузка определяется индивидуально для каждой entity из списка models
        */

    // ************************************** EXAMPLE START **************************************
    AttachmentImage saveOne(AttachmentImage img);
    Optional<AttachmentImage> getById(Long id);
    boolean existsById(Long id);

//    ************************************** EXAMPLE END **************************************
}
