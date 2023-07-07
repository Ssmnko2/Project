package demo.project.twitter.service;


import demo.project.twitter.abstractclass.FunctionAbstract;
import demo.project.twitter.model.chat.ChatNew;
import demo.project.twitter.repository.RepoChatNew;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Log4j2
public class ServiceChatNew1 implements FunctionAbstract<ChatNew> {
    private final RepoChatNew repo;

    @Override
    public ChatNew saveOne(ChatNew chat) {
        return repo.save(chat);
    }



    @Override
    public Optional<ChatNew> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    public List<ChatNew> getAll() {
        List<ChatNew> chats = new ArrayList<>();
        return StreamSupport.stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void addUserToChat(Long chatId, Long userId) {
        repo.addUserToChat(chatId, userId);
    }

    public void deleteUserFromChat(Long chatId, Long userId) {
        repo.deleteUserFromChat(chatId, userId);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }


    public List<ChatNew> getAllByUserId(Long userId) {
        return repo.getAllByUserId(userId);
    }

    public List<ChatNew> getChatByUser(Long userInit, Long userReciv) {
        List<ChatNew> listChat = repo.getChatByUser(userInit, userReciv);
        return listChat;
    }


    public List<ChatNew> getListChat(Long generalChatId) {
        return repo.getListChat(generalChatId);
    }

    public List<ChatNew> getListChatByGeneralId(Long generalChatId) {
        return repo.getListChatByGeneralId(generalChatId);
    }


}
