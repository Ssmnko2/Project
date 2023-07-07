package demo.project.twitter.repository;

import demo.project.twitter.model.chat.GeneralChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepoGeneralChat extends JpaRepository<GeneralChat, Long> {


    boolean existsByUserId(Long profile);

    GeneralChat findByUserId(Long profile);




}
