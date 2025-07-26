import React from 'react'
import { useHistory } from "react-router-dom";
import { Grid, Paper, Avatar, Typography, TextField, Button} from '@mui/material'
import AddCircleOutlineOutlinedIcon from '@mui/icons-material/AddCircleOutlineOutlined';
import {Formik,Field,Form,ErrorMessage} from 'formik'
import * as Yup from 'yup'

 function RegisterForm(){
    
    
    const paperStyle = { padding: '30px 20px', width: 300, margin: "120px auto" }
    const shadowOffset = { width: 0 ,height: 1}
    const headerStyle = { margin: 0 }
    const avatarStyle = { backgroundColor: 'blue' }
    const initialValues={
        name:'',
        emailId:'',
        password:'',
        confirmPassword:'',
        ans:'',
        
    }
    
    const validationSchema = Yup.object().shape({
        name: Yup.string().min(3, "It's too short").required("Required"),
        emailId: Yup.string().email("Enter valid email").required("Required"),
        password: Yup.string().required('Required').matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/,
        "Must Contain 8 Characters, One Uppercase, One Lowercase, One Number and One Special Case Character"),
        confirmPassword: Yup.string().oneOf([Yup.ref('password')], "Password not matched").required("Required"),
        ans: Yup.string().required('Required'),
       
    })
    const history = useHistory();
    
    const onSubmit=(values,props)=>{
        console.log(values)
        console.log(props)
        fetch('http://localhost:8080/user/save',{
            method: 'POST',
            body :JSON.stringify(values),
            headers: { 'Content-Type': 'application/json'}

        }).then(() => history.replace('/login'));
        
    }
   
    
 
     return (
         <Grid>
             <Paper elevation={20} style={paperStyle} shadowColor='#000' Offset={shadowOffset} shadowOpacity='0.8'
                 shadowRadius='2' >
                 <Grid align='center'>
                     <Avatar style={avatarStyle}>
                         <AddCircleOutlineOutlinedIcon />
                     </Avatar>
                     <h2 style={headerStyle}>Sign Up</h2>
                     <Typography variant='caption' gutterBottom>Please fill this form to create an account !</Typography>
                 </Grid>
                 <Formik  initialValues={initialValues} validationSchema={validationSchema} onSubmit={onSubmit}>
                 {(props) => (
                         <Form >
                             <Field as={TextField} fullWidth name='name' label='Name' placeholder="Enter your Name"
                                 helperText={<ErrorMessage name="name" />} />
                             <Field as={TextField} fullWidth name='emailId' label='Email' placeholder="Enter your email"
                                  helperText={<ErrorMessage name="emailId" />} />
                             <Field as={TextField} fullWidth name='password' label='Password' placeholder="Enter your password" type="password"
                                  helperText={<ErrorMessage name="password" />} />
                             <Field as={TextField} fullWidth name='confirmPassword' label='Confirm Password' placeholder="Confirm your password"
                                 type="password" helperText={<ErrorMessage name="confirmPassword" />} />
                             <p> Who is your favourite singer ?</p>
                             <Field as={TextField} fullWidth name='ans' label='Type here' placeholder="Enter you answer"
                                 helperText={<ErrorMessage name="ans" />} />
                            
                            <Button type='submit' variant='contained' color='primary'>Register</Button>
                             
                         </Form>
                 )}
                 </Formik>
             </Paper>
         </Grid>
     );
}


export default RegisterForm;