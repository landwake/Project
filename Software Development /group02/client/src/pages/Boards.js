import React,{useEffect,useState} from "react";
import ViewBoards from '../components/ViewBoards'
import boards from '../dummy/dummyBoards'; 

function Boards(){
    /* ToDO: Create state using useState hook for boards data */
    const [boardsData,setBoardsData] = useState([]);

    function getAlllBoards(){
        /* ToDo: Implement getAllBoards function*/
        fetch('http://localhost:8080/workspace/getBoards')
        .then(response => response.json())
        .then(boards => {
            setBoardsData(boards);

        });
    };

  
    /* ToDo: Use useEffect hook to call getAllBoards()*/
    useEffect(function(){
        getAlllBoards();
    },[])


    return(
        <section>
            <ViewBoards /* ToDo: Pass the state variable as props*/ boards={boardsData}/>
        </section>
    )
}

export default Boards;
