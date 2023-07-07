package demo.project.twitter.facade;

import demo.project.twitter.config.Mapper;
import demo.project.twitter.dto.UserDto;
import demo.project.twitter.dto.UserSearchDto;
import demo.project.twitter.model.User;
import demo.project.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Log4j2
public class UserFacade {

    private final UserService service;
    private User entity = new User();
    private UserDto dto = new UserDto();
    private UserSearchDto userSearchDto = new UserSearchDto();
    private final Mapper mapper;


    private UserDto makeDto (User entity) {

        dto = mapper.map().map(entity, dto.getClass());
        return dto;
    }

    private UserSearchDto makeUserSearchDto (User entity) {
        userSearchDto = mapper.map().map(entity, userSearchDto.getClass());
        return userSearchDto;
    }

    public ResponseEntity<UserDto> getEntity (Long id){

            entity = service.findById(id);
            dto = makeDto(entity);
            return ResponseEntity.accepted().body(dto);

    }

    public ResponseEntity<UserDto> getProfile (String userName){

            entity = service.findByUserName(userName);
            dto = makeDto(entity);
            return ResponseEntity.accepted().body(dto);
    }



    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<User> allusers = service.findAllUsers();
        List<UserDto> allUsrDto= allusers.stream()
                .map(this::makeDto).collect(Collectors.toList());
         return ResponseEntity.accepted().body(allUsrDto);

    }

    public ResponseEntity<UserDto> saveEntity (Long id, UserDto data){

            entity = service.updateUser(service.findById(id), data);
            dto = makeDto(entity);
            return ResponseEntity.accepted().body(dto);

    }

    public UserDto saveEntity (UserDto requestBody){

        entity = mapper.map().map(requestBody, entity.getClass());
        User entity2 = service.saveUser(entity);
        dto = makeDto(entity2);
        return dto;

    }

    public UserDto updateUser (String firstName, String location, Date birthDate, String bio, Optional<String> avUrl, Optional<String> headUrl){
        User usr = new User(firstName, location, birthDate, bio, avUrl.get(), headUrl.get());
        UserDto entity = makeDto(usr);
//        User entity2 = service.updateUser(usr, entity);
//        dto = makeDto(entity2);
        return entity;
    }


    public ResponseEntity<List<UserDto>> getFollowers (Long id){

            List <User> followers = service.getFollowers(service.findById(id));

            List<UserDto> followersDto = followers.stream().
                    map(this::makeDto).collect(Collectors.toList());
            return ResponseEntity.accepted().body(followersDto);

    }

    public ResponseEntity<List<UserDto>> getFollowing (Long id){

        List <User> followings = service.getFollowing(service.findById(id));
        List<UserDto> followingsDto = followings.stream().
                map(this::makeDto).collect(Collectors.toList());
        return ResponseEntity.accepted().body(followingsDto);

    }

    public ResponseEntity<List<UserDto>> whoToFollow (String username) {

        List<User> whoToFollow = service.whoToFollow(username);

        List<UserDto> whoToFollowDto = whoToFollow.stream().
                map(this::makeDto).collect(Collectors.toList());
        return ResponseEntity.accepted().body(whoToFollowDto);

    }

    public void follow(User follower, User following) {
        service.follow(follower, following);
    }

    public void unFollow(User follower, User following) {
        service.unFollow(follower, following);
    }


    public List<UserSearchDto> userSearch(String searchRequest) {
        return service.
                searchByUser(searchRequest).
                stream().
                map(this::makeUserSearchDto).
                collect(Collectors.toList());
    }



    public User getUserByName(String userName) {
        return service.findByUserName(userName);
    }



    public User getUserById1(Long userReciv) {
        return service.findById(userReciv);
    }

    public Long getUserReceiverFromChat(Long chatId) {
        return service.getUserReceiverFromChat(chatId).get(0).getId();
    }
}







