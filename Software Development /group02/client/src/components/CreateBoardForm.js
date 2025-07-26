import {Button,TextField,Typography} from '@mui/material';
import React,{useRef,} from'react';

function CreateBoardForm(props){
    /*ToDO: Create board name variable using useRef*/
    const boardNameRef= useRef();

    /*ToDO: Create board description variable using useRef*/
    const boardDespRef =useRef(); 

    function createBoard(e){
        e.preventDefault();
        /*ToDO: Implement createBaord function*/
        const boardName = boardNameRef.current.value;
        const boardDesp =boardDespRef.current.value;
        
        const newBoard={
            name: boardName,
            decription: boardDesp
        };

        props.CreateBoard(newBoard);
    };

    return (
        <section style={{ marginTop: '32 px' }}>
            <Typography variant='h2' component='h2'> Create New Board</Typography>
            <form /* ToDo: Add onSubmit to call CreateBoard */ onSubmit={createBoard}>
                <TextField
                    id='boardName' placeholder='Board Name' variant='outlined' multiline rows={4} required fullWidth
                     margin='dense' inputRef={boardNameRef}
                     /* ToDo: Add inputRef and bind with the declared name of ref variable*/ />
                <TextField
                    id='boardDesp' placeholder='Board Description' variant='outlined' multiline rows={4} required fullWidth
                     margin='dense' inputRef={boardDespRef}
                     /* ToDo: Add inputRef and bind with the declared desp ref variable*/ />
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Create Board
                </Button>
            </form>
        </section>
    );

};

export default CreateBoardForm;

