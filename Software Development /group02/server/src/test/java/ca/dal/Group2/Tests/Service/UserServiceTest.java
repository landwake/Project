package ca.dal.Group2.Tests.Service;


import ca.dal.Group2.User.Entity.UserEntity;
import ca.dal.Group2.User.Repository.UserRepo;
import ca.dal.Group2.User.Service.UserService;
import ca.dal.Group2.Workspace.Repository.WorkspaceRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepo repo;


    @MockBean
    private WorkspaceRepo workspaceRepo;

    private UserEntity empty = new UserEntity("Birch","Birch@trees.com","Ilovetrees!", "Latin name?","Betula papyrifera");//sec

    @Test
    public void testLogin(){
        when(repo.findByEmailId(empty.getEmailId())).thenReturn(empty);

        assertEquals(empty, service.login(empty));
    }

    @Test
    public void testSignUpUser(){
        when(repo.save(empty)).thenReturn(empty);
        assertEquals(empty, service.signupUser(empty));
    }

    @Test
    public void testGimmePassword(){
        when(repo.findByEmailId(empty.getEmailId())).thenReturn(empty);
        assertEquals(empty.getPassword(), service.gimmePassword("Birch@trees.com"));
    }


    @Test
    public void testGetQuestion(){
        when(repo.findByEmailId(empty.getEmailId())).thenReturn(empty);
        assertEquals(empty.getSecQ(), service.getQuestion("Birch@trees.com"));
    }




}
