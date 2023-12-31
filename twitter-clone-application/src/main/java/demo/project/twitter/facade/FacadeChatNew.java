package demo.project.twitter.facade;

import demo.project.twitter.config.Mapper;
import demo.project.twitter.dto.DtoChat;
import demo.project.twitter.dto.DtoChatMessage;
import demo.project.twitter.dto.DtoMessage;
import demo.project.twitter.service.ServiceChatNew1;
import demo.project.twitter.service.ServiceGeneralChat;
import demo.project.twitter.service.ServiceMessage;
import demo.project.twitter.model.User;
import demo.project.twitter.model.chat.ChatNew;
import demo.project.twitter.model.chat.GeneralChat;
import demo.project.twitter.model.chat.Message;
import demo.project.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Log4j2
public class FacadeChatNew {

    private final ServiceChatNew1 service;
    private final UserService serviceUser;
    private final ServiceMessage serviceMessage;
    private final Mapper mapper;
    private final ServiceGeneralChat serviceGeneralChat;


    public ChatNew getChatByUser(Long userInit, Long userReciv) {
        List<ChatNew> listChatInit = service.getChatByUser(userInit, userReciv);
        List<ChatNew> listChatReciv = service.getChatByUser(userReciv, userInit);
        ChatNew newChat;
        if ((listChatInit.size() == 0) && (listChatReciv.size() == 0)){

            User userIn = serviceUser.findById(userInit);
            User userRe = serviceUser.findById(userReciv);
            ChatNew chat = new ChatNew(userIn.getId(), userRe);
            newChat = service.saveOne(chat);
            userRe.getUserChatsNew().add(newChat);
            serviceUser.saveUser(userRe);

        } else {
            newChat = (listChatInit.size() == 0) ? listChatReciv.get(0) : listChatInit.get(0);
        }


        return newChat;
    }

    public Message saveMessage(Long profileID, DtoMessage dtoM) {
        ChatNew chat = service.getById(dtoM.getChat_id()).get();
        User user = serviceUser.findById(profileID);
        Message message = new Message(user, chat, dtoM.getTextMessage());
        message.setCreatedDate(new Date());
        serviceMessage.saveOne(message);
        return message;
    }

    public DtoChatMessage getChatAllMessages(Long chatId, Long profileId, Integer sizePage, Integer namberPage) {
        Page<Message> page = serviceMessage.getChatAllMessages(chatId, sizePage, namberPage);
        DtoChatMessage dtoChatMessage = new DtoChatMessage();
        dtoChatMessage.setTotalElements(page.getTotalElements());
        dtoChatMessage.setTotalPage(page.getTotalPages());

        List<DtoMessage> listDtoMessage = page.
                stream().
                map(m -> transMessageToDto(m, profileId)).
                collect(Collectors.toList());

        dtoChatMessage.setListDto(listDtoMessage);
        return dtoChatMessage;
    }

    private DtoMessage transMessageToDto(Message m, Long profileId) {
        DtoMessage dtoMessage = new DtoMessage();
        dtoMessage.setDateMessage(m.getCreatedDate());
        dtoMessage.setTextMessage(m.getTextMessage());
        dtoMessage.setChat_id(m.getChat().getId());
        dtoMessage.setUser_author(m.getUser().getId());

        int i = (profileId == m.getUser().getId()) ? 1 : -1;
        dtoMessage.setTypeMessage(i);
        return dtoMessage;
    }

    public DtoChat transChatToDtoChat(ChatNew chat, int keyMessage) {
        DtoChat dtoChat = new DtoChat();
        User user = serviceUser.getUserReceiverFromChat(chat.getId()).get(0);
        mapper.map().map(user,dtoChat);
        dtoChat.setUserResivId(user.getId());
        dtoChat.setInitiatorId(chat.getInitiatorId());
        dtoChat.setChatId(chat.getId());
        if (keyMessage == 1) {
            List<String> lastMessage = serviceMessage.getLastMessage(chat.getId());
            if (lastMessage.size() > 0)
                dtoChat.setLastMessage(lastMessage.get(0));
        }

        return dtoChat;
    }


    public ChatNew getChatById(Long chatId) {
        return service.getById(chatId).get();
    }

    public void delChat(Long chatId, Long profileId) {
        ChatNew chat = getChatById(chatId);
        if (chat.getInitiatorId() == profileId){
            serviceMessage.delMessageByChatId(chatId);
            User userReceiver = serviceUser.getUserReceiverFromChat(chatId).get(0);
            userReceiver.getUserChatsNew().remove(chat);
            GeneralChat generalChat = serviceGeneralChat.getListChatByUserId(userReceiver.getId()).get(0);
            generalChat.getListChat().remove(chat);
            serviceUser.saveUser(userReceiver);

        }

        GeneralChat generalChat = serviceGeneralChat.getListChatByUserId(profileId).get(0);
        generalChat.getListChat().remove(chat);
        serviceGeneralChat.saveOne(generalChat);


        if (chat.getInitiatorId() == profileId) service.deleteById(chatId);
    }
}






