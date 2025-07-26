// import{Link} from "react-router-dom";
import * as React from 'react';
import {AppBar,Box,Toolbar,Typography} from '@mui/material';
import { Link } from 'react-router-dom';

function Navigation() {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>

                    <Link to='/login' style={{
                        textDecoration: 'none',
                        color: 'white'
                    }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Login
                        </Typography>
                    </Link>
                    <Link to='/register' style={{
                        textDecoration: 'none',
                        color: 'white'
                    }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Sign Up
                        </Typography>
                    </Link>
                    <Link to='/forgot-password' style={{
                        textDecoration: 'none',
                        color: 'white'
                    }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Forgot Password
                        </Typography>
                    </Link>
                    <Link to='/boards' style={{
                        textDecoration: 'none',
                        color: 'white'
                    }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Boards
                        </Typography>
                    </Link>
                    <Link to='/create-board' style={{
                        textDecoration: 'none',
                        color: 'white'
                    }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Create Board
                        </Typography>
                    </Link>
                </Toolbar>
            </AppBar>
        </Box>
    );
}


 export default Navigation; 