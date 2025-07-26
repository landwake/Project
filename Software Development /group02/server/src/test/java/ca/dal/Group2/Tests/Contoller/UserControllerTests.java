
package ca.dal.Group2.Tests.Contoller;

import ca.dal.Group2.ServerApplication;
import ca.dal.Group2.User.Entity.UserEntity;
import ca.dal.Group2.User.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ContextConfiguration(classes = {UserCont.class})
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserCont.class)
//@Import(UserCont.class)
//@AutoConfigureMockMvc(addFilters = false)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes= ServerApplication.class)
public class UserControllerTests {
    @MockBean
    private UserService service;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private UserEntity empty = new UserEntity("Birch","Birch@trees.com","Ilovetrees!", "Latin name?","Betula papyrifera");

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void signupUserTest() throws Exception{
//        when(service.signupUser(empty)).thenReturn(empty);
//        mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\": \"Birch\", \"emailId\":\"Birch@trees.com\", \"password\":\"Ilovetrees!\", \"secruityQ\":\"Latin name?\", \"answer\": \"Betula papyrifera\"}"))
//                .andExpect(status().isOk());
        //String jsonStr = JSONUtil.toJsonStr(empty);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                .header("Content-Type", "application/json")
                .content("{\"name\": \"Birch\", \"emailId\":\"Birch@trees.com\", \"password\":\"Ilovetrees!\", \"secruityQ\":\"Latin name?\", \"answer\": \"Betula papyrifera\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result is：" + responseString);
    }

    @Test
    public void loginTest() throws  Exception{
        when(service.login(empty)).thenReturn(empty);
        String responseString = mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"emailId\":\"Birch@trees.com\", \"password\":\"Ilovetrees!\"}"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("result is：" + responseString);
    }


    @Test
    public void oopsTest() throws Exception{
        when(service.gimmePassword(empty.getEmailId())).thenReturn(empty.getPassword());
        String responseString = mockMvc.perform(post("/user/forgot").contentType(MediaType.APPLICATION_JSON)
                .content("{\"emailId\":\"Birch@trees.com\"}"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("result is：" + responseString);
    }

    @Test
    public void questionTest() throws Exception{
        when(service.getQuestion(empty.getEmailId())).thenReturn(empty.getSecQ());
        String responseString = mockMvc.perform(post("/user/askQuestion").contentType(MediaType.APPLICATION_JSON)
                .content("{\"emailId\":\"Birch@trees.com\"}"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("result is：" + responseString);
    }

}
