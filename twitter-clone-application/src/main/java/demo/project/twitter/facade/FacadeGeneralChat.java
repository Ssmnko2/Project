package demo.project.twitter.facade;

import demo.project.twitter.service.ServiceChatNew1;
import demo.project.twitter.service.ServiceGeneralChat;
import demo.project.twitter.model.chat.ChatNew;
import demo.project.twitter.model.chat.GeneralChat;
import demo.project.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Log4j2
public class FacadeGeneralChat {

    private final ServiceGeneralChat service;
    private final ServiceChatNew1 serviceChatNew;
    private final UserService serviceUser;

    private boolean existsChatInGeneralChat(Long chatId, Long generalChatId) {
        return service.existsChatInGeneralChat(chatId, generalChatId);
    }
public GeneralChat newGenegarChat(Long chatId, Long profileId){
//        Long userReceiver = serviceUser.getUserReceiverFromChat(chatId).get(0).getId();
        addChatToChatList(chatId, serviceUser.getUserReceiverFromChat(chatId).get(0).getId());
        return addChatToChatList(chatId, profileId);
}
    public GeneralChat addChatToChatList(Long chatId, Long profileID) {
        GeneralChat listChat;
        if (service.existsByUserId(profileID)) {

            listChat = service.getListChatByUserId(profileID).get(0);

//            boolean b = existsChatInGeneralChat(chatId, listChat.getId());

            if (!existsChatInGeneralChat(chatId, listChat.getId())) {

                ChatNew chat = serviceChatNew.getById(chatId).get();

                listChat.getListChat().add(chat);

                service.saveOne(listChat);

            }
            /*Chat chat = serviceChatNew.getById(chatId).get();
            listChat.getListChat().add(chat);
            service.saveOne(listChat);*/
            log.info("::::::: end");

        } else {
            log.info(":::::: start2");
            listChat = new GeneralChat(profileID);
            log.info(":::::: listChat = " + listChat.toString());
            GeneralChat listChat1 = service.saveOne(listChat);
            log.info("::::::: save ok");

            log.info(":::::: listChat = " + listChat1.toString());

            ChatNew chat = serviceChatNew.getById(chatId).get();
            log.info("::::::: chat = " + chat.getId());
            /*List<ListChat> listlistChat = new ArrayList<>();
            chat.setListListChat(listlistChat);*/
            chat.getListGeneralChat().add(listChat1);
            log.info("::::::: chat = " + chat.getListGeneralChat().size());

            chat = serviceChatNew.saveOne(chat);
            listChat.getListChat().add(chat);
            service.saveOne(listChat);

        }
        return listChat;
    }


    public List<ChatNew> getListChat(Long profileId) {
        List<ChatNew> listChat = new ArrayList<>();
        List<GeneralChat> listGeneraCaht = service.getListChatByUserId(profileId);
        if (listGeneraCaht.size() > 0) {
            Long generalChatId = listGeneraCaht.get(0).getId();
            listChat = serviceChatNew.getListChatByGeneralId(generalChatId);
        }
        return listChat;
    }
}






