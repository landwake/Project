import * as React from 'react';
import { useHistory } from "react-router-dom";
import {Grid,Card,CardContent,Typography,Button} from '@mui/material';


function ViewBoards(props) {

  const history = useHistory();
  const onSubmit = () => {

    /*ToDo: Implement Delete Board function*/
    fetch('http://localhost:8080/board/deleteById/{id}', {
      method: 'DELETE',
      body: JSON.stringify(),
      headers: { 'Content-Type': 'application/json' }
  
    }).then(() => history.replace('/boards'));
  }
  
  return (
    <section style={{ marginTop: '32px' }}>
      <Typography variant="h2" component="h2">Boards</Typography>
      <Grid container spacing={2}>
        {props.boards.map((board) => {
          return (
            <Grid item xs='12' sm='12' md='4' lg='3' key={board.id}>
              <Card elevation='6'>
                <CardContent>
                  <Typography component='h4' variant='h4'>
                    {board.name}
                  </Typography>
                  <Typography component='p' variant='p'>
                    {board.description}
                  </Typography>
                  <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }} onSubmit={onSubmit}>
                    Delete Board
                  </Button>
                </CardContent>
              </Card>
            </Grid>
          )
        }
        )}
      </Grid>
    </section>
  )
};        

export default ViewBoards;
      
          