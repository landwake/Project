import React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import CreateWorkspaceButton from "./CreateWorkspaceButton";
import { Link, useHistory } from 'react-router-dom';



function Header() {
  const history = useHistory();
  const logOut = () => {
    localStorage.setItem("user","");
    localStorage.setItem("board","");
    history.push("login");
  }
  return (
    <Box sx={{ flexGrow: 1 }}>
      <button><Link to="/login">Login</Link></button>
      <button><Link to="/register">Register</Link></button>
      <button><Link to="/forgot-password">Forgot password</Link></button>
      <button><Link to="/workspace">Workspace and Boards</Link></button>
      <button><Link to="/tasks">Tasks</Link></button>
      {/* <button><Link to="/home">Good Home Page</Link></button> */}
      <button onClick={logOut}>Logout</button>
      <AppBar position="static">
        <Toolbar variant="dense">
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <CreateWorkspaceButton />
        </Toolbar>
      </AppBar>
    </Box>
  )
}

export default Header;