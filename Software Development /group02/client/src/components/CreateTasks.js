import "../styles/createTask.scss";
import { useState } from "react";
import { DatePicker } from 'react-datepicker';

import "react-datepicker/dist/react-datepicker.css";

function Task(props) {
  const { addTask, moveTask, task } = props;


  const [collapsed, setCollapsed] = useState(task.isCollapsed);
  const [formAction, setFormAction] = useState("");
  const [startDate, setStartDate] = useState(new Date());


  

  function handleSubmit(event) {
    event.preventDefault();

    if (formAction === "save") {
      if (collapsed) {
        setCollapsed(false);
      } else {
        let newTask = {
          id: task.id,
          title: event.target.elements.title.value,
          description: event.target.elements.description.value,
        
          status: task.status,
          isCollapsed: true,
          date:event.target.elements.date.value
        };

        addTask(newTask);
        setCollapsed(true);
      }
    }

  }

  function handleMoveLeft() {
    let newStatus = "";

    if (task.status === "Doing") {
      newStatus = "To-Do";
    } else if (task.status === "Done") {
      newStatus = "Doing";
    }

    if (newStatus !== "") {
      moveTask(task.id, newStatus);
    }
  }

  function handleMoveRight() {
    let newStatus = "";

    if (task.status === "To-Do") {
      newStatus = "Doing";
    } else if (task.status === "Doing") {
      newStatus = "Done";
    }

    if (newStatus !== "") {
      moveTask(task.id, newStatus);
    }
  }

  function handleUserUpdate(user){
    // console.log("User changed");
  }

  return (
    <div className={`task ${collapsed ? "collapsedTask" : ""}`}>
      <button onClick={handleMoveLeft} className="button moveTask">
        &#171;
      </button>
      <form onSubmit={handleSubmit} className={collapsed ? "collapsed" : ""}>
        <input
          type="text"
          className="title input"
          name="title"
          placeholder="Enter Title"
          disabled={collapsed}
          defaultValue={task.title}
        />
        <textarea
          rows="2"
          className="description input"
          name="description"
          placeholder="Enter User"
          defaultValue={task.description}
        />
        <input name="date" disabled={collapsed} type="date" defaultValue={task.date} selected={startDate} onChange={(date) => setStartDate(date)} />
        
        <button
          onClick={() => {
            setFormAction("save");
          }}
          className="button"
        >
          {collapsed ? "Edit" : "Save"}
        </button>
        
      </form>
      <button onClick={handleMoveRight} className="button moveTask">
        &#187;
      </button>
    </div>
  );
}
export default Task;
