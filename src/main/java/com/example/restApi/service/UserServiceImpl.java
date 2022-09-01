package com.example.restApi.service;

import com.example.restApi.dto.RequestUserDto;
import com.example.restApi.dto.ResponseUserDto;
import com.example.restApi.enumclass.UserType;
import com.example.restApi.mapper.UserMapper;
import com.example.restApi.model.User;
import com.example.restApi.repository.Addressrepo;
import com.example.restApi.repository.custome.CustomeUserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.restApi.repository.Userdao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private Userdao userdao;
    private Addressrepo addressrepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CustomeUserDetailRepo userDetailRepo;
    @Autowired
    private UserActionService userActionService;

    @Override
    public ResponseUserDto addUser(RequestUserDto requestUserDto) {
        LocalDateTime date = LocalDateTime.now();
        User user = userMapper.requestDtoToUser(requestUserDto);
        user.setUuid(UUID.randomUUID());
        user.setCreatedBy(user.getName());
        user.setCreatedDate(date);
        User updatedUser = userdao.save(user);
        return userMapper.responseUserToDto(updatedUser);

    }

    @Override
    public List<ResponseUserDto> getUser() {
       List<User> user =  (List<User>)userdao.findAll();
        return userMapper.responseUserToDtoList(user);
    }


    @Override
    public ResponseUserDto findByUuid(UUID uuid) {
//        User user = userdao.findByUuid(uuid);
        User user=userdao.getByUuid(uuid);
        return userMapper.responseUserToDto(user);

    }

    public Optional<User> findById(Long id)
    {
        Optional<User> user = userdao.findById(id);
        return user;
    }

    @Override
    public ResponseUserDto updateUser(RequestUserDto requestUserDto) {
        Optional<User> user = userdao.findById(requestUserDto.getId());
        if (user.isPresent()) {
            LocalDateTime date = LocalDateTime.now();
            User requestedUser = userMapper.requestDtoToUser(requestUserDto);
            requestedUser.setUuid(UUID.randomUUID());
            requestedUser.setUpdatedBy(requestedUser.getName());
            requestedUser.setUpdatedDate(date);
            User updatedUser = userdao.save(requestedUser);
            return userMapper.responseUserToDto(updatedUser);
        } else {
            System.err.println("No user Found");
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userdao.findById(id);
        if (user.isPresent()) {
            userdao.deleteById(id);
        }
    }

    @Override
    public ResponseUserDto patchUser(RequestUserDto requestUserDto) {
        Optional<User> user = userdao.findById(requestUserDto.getId());
        if (user.isPresent()) {
            LocalDateTime date = LocalDateTime.now();
            User requestedUser = userMapper.requestDtoToUser(requestUserDto);
            requestedUser.setUuid(UUID.randomUUID());
            requestedUser.setUpdatedBy(requestedUser.getName());
            requestedUser.setUpdatedDate(date);
            requestedUser.setName(requestUserDto.getName());
            return userMapper.responseUserToDto(userdao.save(requestedUser));
        } else {
            System.err.println("No user Found");
            return null;
        }

    }

    @Override
    public Page<User> getPagingData() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<User> user = userdao.findAll(pageable);
        return user;
    }

    @Override
    public List<ResponseUserDto> getSortedData() {
        List<ResponseUserDto> sortedUser = userMapper.responseUserToDtoList((List<User>) userdao.findAll(Sort.by("name")));
        return sortedUser;
    }



    public List<ResponseUserDto> findAllUser() {
        return userMapper.responseUserToDtoList(userdao.findAllUser());
    }

    public List<ResponseUserDto> findUserByName(String name) {

        return userMapper.responseUserToDtoList(userdao.findUserByName(name));
    }

    @Override
    public Optional<User> findByIdLazy(Long id) {

        //Optional<User> user = userdao.findById(id);
        Query q = this.entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.credential c WHERE u.id = :id");
        q.setParameter("id", id);
        User newUser = (User) q.getSingleResult();
        return Optional.ofNullable(newUser);
    }

    @Override
    public User findByIdLazyusingBuilder(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(User.class);
        Root o = q.from(User.class);
        o.fetch("credential", JoinType.INNER);
        q.select(o);
        q.where(cb.equal(o.get("id"), id));

        User user = (User)this.entityManager.createQuery(q).getSingleResult();
        return user;
    }

    @Override
    public void deleteAddress(Long id) {
        Optional<User> user = userdao.findById(id);
        if (user.isPresent()) {
            User usernew = user.get();
            System.out.println(user.get().toString());
           // addressrepo.deleteByUserId(usernew.getId());


        }
    }

    @Override
    public List<ResponseUserDto> getByNameAndUserType(String name, UserType userType) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot =  userCriteriaQuery.from(User.class);
        Predicate namePredicate = criteriaBuilder.like(userRoot.get("name"),"%"+name+"%");
        Predicate userTypePredicate = criteriaBuilder.equal(userRoot.get("userType"),userType);
        userCriteriaQuery.where(namePredicate,userTypePredicate);
        TypedQuery<User> userTypedQuery = entityManager.createQuery(userCriteriaQuery);
       List<User>  users= userTypedQuery.getResultList();
        return userMapper.responseUserToDtoList(users);
    }

  /*public CustomeUserDetail customeUserDetail(UUID uuid)
   {
     return userdao.userDetail(uuid);
   }*/

    public void testProfile()
    {
        userActionService.userAction();
    }
}
