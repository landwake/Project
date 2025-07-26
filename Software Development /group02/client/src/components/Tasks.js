import React, { useState, useEffect, useRef } from "react";
import { useHistory } from "react-router-dom";
import "../styles/Task.scss";
import StatusLine from "./StatusLine";


function Tasks(){

    const [tasks, setTasks] = useState([]);
    const history = useHistory();
    // const [searchQuery, setSearchQuery] = useState("");
    var searchQuery = "";
    var dateFilter = "";
    const search = useRef();


    useEffect(() => {
        loadTasksFromLocalStorage();
    }, []);

  function addEmptyTask(status) {
    const lastTask = tasks[tasks.length - 1];

    let newTaskId = -1;

    // if (lastTask !== undefined) {
    //   newTaskId = lastTask.id + 1;
    // }

    setTasks((tasks) => [
      ...tasks,
      {
        id: newTaskId,
        title: "",
        description: "",
        urgency: "",
        status: status
      }
    ]);
  }

  function addTask(taskToAdd) {
    let filteredTasks = tasks.filter((task) => {
      return task.id !== taskToAdd.id;
    });


    if(taskToAdd.id === -1){
      // new task
      const dueDate = {
        'dueDate':taskToAdd.date + " "+"12:00:00"
      }
  
      fetch('http://localhost:8080/dueDate/create', {
        method: 'POST',
        body :JSON.stringify(dueDate),
        headers: { 'Content-Type': 'application/json'}
      })
      .then(response => response.json())
      .then(data => {
        console.log('Success:', data);
        // save the task when the date saves
        const newTask = {
          "createTime":"2022-07-22 00:00:00",
          "name":taskToAdd.title,
          "status":taskToAdd.status
        }
        const boardId = localStorage.getItem("board"); 
  
        fetch('http://localhost:8080/task/create/'+data.id+"/"+boardId, {
          method: 'POST',
          body :JSON.stringify(newTask),
          headers: { 'Content-Type': 'application/json'}
        })
        .then(response => response.json())
        .then(data => {
          console.log('Success:', data);
          loadTasksFromLocalStorage();
        })
        .catch((error) => {
          console.error('Error:', error);
          loadTasksFromLocalStorage();
        });
  
  
      })
      .catch((error) => {
        console.error('Error:', error);
        loadTasksFromLocalStorage();
      });
    }
    else{
      //update task 
      if(taskToAdd.description != undefined){
        fetch('http://localhost:8080/task/assignTo/'+taskToAdd.id, {
          method: 'POST',
          body :JSON.stringify({
            userEmail:taskToAdd.description
          }),
          headers: { 'Content-Type': 'application/json'}
        })
        .then(response => response.json())
        .then(data => {
          console.log('Success:', data);
          setTasks([]);
          loadTasksFromLocalStorage();

        })
        .catch((error) => {
          console.error('Error:', error);
          setTasks([]);

          loadTasksFromLocalStorage();

        });
      }
    }

    


    console.log(taskToAdd);

    // let newTaskList = [...filteredTasks, taskToAdd];

    // setTasks(newTaskList);
    // setTasks([]);

    // loadTasksFromLocalStorage();

  }


  function moveTask(id, newStatus) {
    console.log(id);
    console.log(newStatus);
    fetch('http://localhost:8080/task/changeStatus/'+id+"/"+newStatus, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json'}
    })
    .then(response => response.json())
    .then(data => {
      console.log('Success:', data);
      loadTasksFromLocalStorage();
    })
    .catch((error) => {
      console.error('Error:', error);
    });
    // let task = tasks.filter((task) => {
    //   return task.id === id;
    // })[0];

    // let filteredTasks = tasks.filter((task) => {
    //   return task.id !== id;
    // });

    // task.status = newStatus;

    // let newTaskList = [...filteredTasks, task];

    // setTasks(newTaskList);

    // saveTasksToLocalStorage(newTaskList);
  }

  function saveTasksToLocalStorage(tasks) {
    localStorage.setItem("tasks", JSON.stringify(tasks));
  }

  const boardId = localStorage.getItem("board");
  let loadedTasks = [];

  const searchTasks = () => {
    // setSearchQuery(search.current.value);
    // setSearchQuery("tasks");
    dateFilter = "";
    searchQuery = search.current.value;
    console.log("input box: "+searchQuery);
    loadTasksFromLocalStorage();
  }

  const filterDate = (filterType) => {
    searchQuery = "";
    dateFilter = filterType;
    loadTasksFromLocalStorage();
  }

  function loadTasksFromLocalStorage() {
    console.log("board: "+localStorage.getItem("board"))
    if(localStorage.getItem('user') == ""){
      history.push("login");
    }
    if(localStorage.getItem("board") == ""){
      history.push("workspace");
    }
    let fetchUrl = "http://localhost:8080/task/";
    if(searchQuery != ""){
      console.log("getting search");
      fetchUrl += "search/"+boardId+"/?query="+searchQuery;
    }
    else if(dateFilter != ""){
      fetchUrl += "dateFilter/"+boardId+"/?filterType="+dateFilter;
    }
    else{
      fetchUrl += "getAll/"+boardId;
    }



    fetch(fetchUrl, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json'}
    })
    .then(response => response.json())
    .then(data => {
      console.log('Success:', data);
      for(let i = 0; i < data.length; i++){
        let user = "";
        let dueDate = "";
        if( data[i].user != undefined){
          user = data[i].user.emailId;
        }
        if( data[i].dueDate != undefined ){
          dueDate = data[i].dueDate.dueDate.split(" ")[0];
        }
        loadedTasks.push({
          id:data[i].id,
          status:data[i].status,
          urgency:'',
          description:user,
          isCollapsed:true,
          title:data[i].name,
          date:dueDate
        });
      }
      let tasks = loadedTasks;
      setTasks(tasks);
      console.log(tasks);

    })
    .catch((error) => {
      console.error('Error:', error);
    });


    // let loadedTasks = localStorage.getItem("tasks");

    // let tasks = JSON.parse(loadedTasks);
    console.log(tasks);

    // if (tasks) {
    //   setTasks(tasks);
    // }
  }

  return (
    
        <div className="TaskCom">
        <h1>Task Management</h1>
        <span>
          <h3>search: </h3>
          <input ref={search}></input>
          <button onClick={() => searchTasks()}>Go</button>
          </span>
          <div>
            <h3>Filter</h3>
            <button onClick={() => filterDate('dueToday')}>Due Today</button>
            <button onClick={() => filterDate('dueInWeek')}>Due in a week</button>
            <button onClick={() => filterDate('overdue')}>Overdue</button>
          </div>
        <main>
            <section>
            <StatusLine
                tasks={tasks}
                addEmptyTask={addEmptyTask}
                addTask={addTask}
                // deleteTask={deleteTask}
                moveTask={moveTask}
                status="To-Do"
            />
            <StatusLine
                tasks={tasks}
                addEmptyTask={addEmptyTask}
                addTask={addTask}
                // deleteTask={deleteTask}
                moveTask={moveTask}
                status="Doing"
            />
            <StatusLine
                tasks={tasks}
                addEmptyTask={addEmptyTask}
                addTask={addTask}
                // deleteTask={deleteTask}
                moveTask={moveTask}
                status="Done"
            />
            </section>
        </main>
        </div>
       
  );
}


export default Tasks;