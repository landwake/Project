import React from 'react';
import { useHistory } from "react-router-dom";
import { Grid, Paper, Avatar, TextField, Button} from '@mui/material'
import LockIcon from '@mui/icons-material/Lock';
import { Formik, Form, Field, ErrorMessage } from 'formik'
import * as Yup from 'yup'


function ForgotPasswordForm() {
    // const emailRef = useRef();
    // const passwordRef = useRef();


    const paperStyle = { padding: 20, height: '75vh', width: 280, margin: '120px auto' }
    const avatarSytyle = { backgroundColor: 'black' }
    const btnstyle = { margin: '8px 0' }

    const initialValues = {
        email:'',
        answer:'',
        password:'',
        confirmNewPassword:''
        
    }
    const validationSchema = Yup.object().shape({
        email: Yup.string().email("Enter valid email").required("Required"),
        answer: Yup.string().oneOf([Yup.ref('answer')], "Incorect").required("Required"),
        password: Yup.string().required('Required').matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/,
        "Must Contain 8 Characters, One Uppercase, One Lowercase, One Number and One Special Case Character"),
        confirmNewPassword: Yup.string().oneOf([Yup.ref('password')], "Password not matched").required("Required"),
        
    })
    const history = useHistory();

    const onSubmit = (values) => {
        console.log(values)
        fetch('http://localhost:8080/user/forgetPassword',{
            method: 'POST',
            body :JSON.stringify(values),
            headers: { 'Content-Type': 'application/json'}

        }).then((res) => {
            console.log(res);
            history.replace('/login');
        
        });
        

    }


    return (
        <section>
            <Grid>
                <Paper elevation={10} style={paperStyle}>
                    <Grid align='center'>
                        <Avatar style={avatarSytyle}> <LockIcon /> </Avatar>
                        <h2>Enter details below </h2>
                    </Grid>
                    <Formik initialValues={initialValues} onSubmit={onSubmit} validationSchema={validationSchema}>
                        {(props) => (
                            <Form>
                                <Field as={TextField}  name='email' label='Email' placeholder="Enter your email"
                                 fullWidth helperText={<ErrorMessage name="email" />} />
                                <p> Who is your favourite singer ?</p>
                                <Field as={TextField} label="Security Answer" name='answer' placeholder="Enter answer" 
                                fullWidth required helperText={<ErrorMessage name="answer" />}
                                />
                                <Field as={TextField} label="New Password" name="password" placeholder="Enter password" type="password"
                                 fullWidth required helperText={<ErrorMessage name="password" />}
                                />
                                <Field as={TextField} label=" Confirm New Password" name="confirmNewPassword" placeholder="Enter password" 
                                type="password" fullWidth required
                                    helperText={<ErrorMessage name="confirmNewPassword" />}
                                />
                                <Button type='submit' color='primary' variant="contained" disabled={props.isSubmitting}
                                    style={btnstyle} fullWidth>Sign in</Button>
                            </Form>
                        )
                        }
                    </Formik>
                </Paper>
            </Grid>

        </section>
    );
}

export default ForgotPasswordForm;
