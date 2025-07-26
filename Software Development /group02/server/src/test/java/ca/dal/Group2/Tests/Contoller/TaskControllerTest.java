package ca.dal.Group2.Tests.Contoller;

import ca.dal.Group2.ServerApplication;
import ca.dal.Group2.Task.Entity.TaskEntity;
import cn.hutool.json.JSONUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes= ServerApplication.class)
public class TaskControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName("taskA");
        String jsonStr = JSONUtil.toJsonStr(taskEntity);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/task/create/2/1")
                .header("Content-Type", "application/json")
                .content(jsonStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result is: " + responseString);
    }

    @Test
    public void assignTo() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/task/assignTo/1/7")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result is: " + responseString);
    }

    @Test
    public void changeStatus() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/task/changeStatus/7/doing")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result is: " + responseString);
    }
}
