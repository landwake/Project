package ca.dal.Group2.Task.Controller;

import ca.dal.Group2.Task.Entity.DueDateEntity;
import ca.dal.Group2.Task.Service.DueDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/dueDate")
public class DueDateController {

    @Autowired
    private DueDateService dueDateService;

    @PostMapping("/create")
    public DueDateEntity create(@RequestBody DueDateEntity model){
        return dueDateService.save(model);
    }

    @GetMapping("/list")
    public List<DueDateEntity> list(){
        return dueDateService.list();
    }
}
