package demo.project.twitter.service;


import demo.project.twitter.abstractclass.FunctionAbstract;
import demo.project.twitter.model.User;
import demo.project.twitter.model.chat.GeneralChat;
import demo.project.twitter.repository.RepoListChat;
import demo.project.twitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Log4j2
public class ServiceGeneralChat implements FunctionAbstract<GeneralChat> {
    private final RepoListChat repo;
    private final UserRepository repoUser;


    @Override
    public GeneralChat saveOne(GeneralChat listchat) {
        return repo.save(listchat);
    }

    @Override
    public Optional<GeneralChat> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public List<GeneralChat> getAll() {
        return null;
    }

    public boolean existsByUserId(Long profileID) {
        return repo.existsByUserId(profileID);
    }

    public List<GeneralChat> getListChatByUserId(Long profileID) {
        return repo.findByUserId(profileID);
    }

    public List<User> getListChat(Long listChatId) {


        return repoUser.getListChat(listChatId);




//        return null;

    }

    public boolean existsChatInGeneralChat(Long chatId, Long generalChatId) {
        if (repo.existsChatInGeneralChat(chatId, generalChatId).size() > 0) return true;
        else return false;
    }
}
