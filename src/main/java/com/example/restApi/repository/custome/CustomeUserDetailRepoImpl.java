package com.example.restApi.repository.custome;

import com.example.restApi.custome.CustomeUserDetail;
import com.example.restApi.dto.ResponseUserDto;
import com.example.restApi.enumclass.UserType;
import com.example.restApi.mapper.UserMapper;
import com.example.restApi.model.Address;
import com.example.restApi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomeUserDetailRepoImpl implements CustomeUserDetailRepo {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserMapper userMapper;

    CustomeUserDetail customeUserDetail= new CustomeUserDetail();
    @Override
    public CustomeUserDetail userDetail(UUID uuid) {
       //User user = userdao.findByUuid(uuid);
        Query nameQuery = entityManager.createQuery("Select u.name FROM User u Where u.uuid LIKE :uuid").setParameter("uuid",uuid);
        String nameResult = String.valueOf(nameQuery.getResultList());
        Query addressQuery = entityManager.createQuery("Select u.address FROM User u Where u.uuid LIKE :uuid").setParameter("uuid",uuid);
        List<Address> addressResult = addressQuery.getResultList();
        customeUserDetail.setName(nameResult);
        customeUserDetail.setAddress(addressResult);
        return  customeUserDetail;
    }

    public List<ResponseUserDto> getNameAndUserType(String name, UserType userType) {

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


}

