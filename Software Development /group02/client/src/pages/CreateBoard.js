import React from 'react';
import { useHistory } from "react-router-dom";
import CreateBoardForm from '../components/CreateBoardForm';

function CreateBoard(){
    /* ToDo: Create history object with useHistory hook for navigation*/
    const history = useHistory();

    function createBoardHandler(board){
        /* ToDo: Implement createBoardHandler function and bnavigate to/boards*/
        fetch('http://localhost:8080/board/add',{
            method: 'POST',
            body: JSON.stringify(board),
            headers:{
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace('/boards'))

    }

    return (
        <CreateBoardForm /*ToDo: Pass the createBoardhandler function as props */ CreateBoard={createBoardHandler}/>
    );

};

export default CreateBoard;