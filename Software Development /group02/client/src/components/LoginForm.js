import React from 'react';
import { useHistory } from "react-router-dom";
import { Grid, Paper, Avatar, TextField, Button, Typography, Link } from '@mui/material'
import LockIcon from '@mui/icons-material/Lock';
import { Formik, Form, Field, ErrorMessage } from 'formik'
import * as Yup from 'yup'


function LoginForm() {
    


    const paperStyle = { padding: 20, height: '50vh', width: 280, margin: '120px auto' }
    const avatarSytyle = { backgroundColor: 'black' }

    const initialValues = {
        emailId: '',
        password: '',
    }
    const validationSchema = Yup.object().shape({
        emailId: Yup.string().email('please enter valid email').required("Required"),
        password: Yup.string().required("Required")
    })
    const history = useHistory();
    
    const onSubmit = (values) => {
        console.log(values)
        fetch('http://localhost:8080/user/login', {
            method: 'POST',
            body :JSON.stringify(values),
            headers: { 'Content-Type': 'application/json'}
        })
        .then(response => response.json())
        .then(data => {
          console.log('Success:', data);
          localStorage.setItem("user",data.id );
          history.push("/home")
        })
        .catch((error) => {
          console.error('Error:', error);
        });
        
    }


    // function submitHandler(event){
    //     event.preventDefault();
    // Read the values

    //    const email = emailRef.current.value;
    //    const password = passwordRef.current.value;
    //    const user = { email,password};
    //    // call
    //     props.loginUser(user);
    //sdgsgs

    return (
        <section>
            <Grid>
                <Paper elevation={10} style={paperStyle}>
                    <Grid align='center'>
                        <Avatar style={avatarSytyle}> <LockIcon /> </Avatar>
                        <h2>Sign In </h2>
                    </Grid>
                    <Formik initialValues={initialValues} onSubmit={onSubmit} validationSchema={validationSchema}>
                        {(props) => (
                            <Form>
                                <Field as={TextField} label="Email" name='emailId' placeholder="Enter email" fullWidth required
                                    helperText={<ErrorMessage name="emailId" />}
                                />
                                <Field as={TextField} label="Password" name="password" placeholder="Enter password" type="password" fullWidth required
                                    helperText={<ErrorMessage name="password" />}
                                />
                                <Button type='submit' color='primary' variant="contained" >Sign in</Button>
                            </Form>
                        )
                        }
                    </Formik>

                    <Typography>
                        <Link href="/forgot-password" fullWidth required> Forgot Password ?</Link>
                    </Typography>
                    <Typography> Don't have an account ?<Link href="/register"> Sign Up</Link>
                    </Typography>
                </Paper>
            </Grid>

        </section>
    );
}

export default LoginForm;
