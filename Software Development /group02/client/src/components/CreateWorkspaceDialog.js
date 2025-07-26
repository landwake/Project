import {Dialog, Box, Grid, Typography, TextField, Button, Select, MenuItem} from '@mui/material';
import {useEffect, useState, useContext, createContext} from "react";
import {CreateWorkSpaceConsumer as useCreateWorkSpaceDialog} from './CreateWorkspaceDialogHook';
import style from './CreateWorkspaceDialog.module.css'
import {SERVER_HOST} from "../config";

function ValidateEmail(email) {
  if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
  {
    return (true)
  }
  return (false)
}


const STEPS = {
  'SET_WORKSPACE': 'SET_WORKSPACE',
  'INVITE_PARTNER': 'INVITE_PARTNER'
}
export default function CreateWorkspaceDialog({onSubmit}) {
  const {show, hiddenCreateWorkSpaceDialog} = useCreateWorkSpaceDialog();
  const [memberEmail, setMemberEmail] = useState('');
  const [valid, setValid] = useState(false);
  const [step, setStep] = useState(STEPS.SET_WORKSPACE);
  const [workspaceType, setWorkspaceType] = useState('business');
  const [workspaceName, setWorkspaceName] = useState('');
  const [workspaceDescription, setWorkspaceDescription] = useState('');
  useEffect(() => {
    if (show) {
      setStep(STEPS.SET_WORKSPACE);
    }
  },[show]);
  useEffect(() => {
    if (step === STEPS.SET_WORKSPACE) {
      setValid(
        workspaceName && workspaceType && workspaceDescription
      )
    } else if(step === STEPS.INVITE_PARTNER) {
      setValid(ValidateEmail(memberEmail));
    }
  }, [memberEmail, workspaceName, workspaceType, workspaceDescription, step]);
  const handleClickNext = () => {
    setStep(STEPS.INVITE_PARTNER);
  }
  const handleConfirmCreate = () => {
    onSubmit && onSubmit(workspaceName, workspaceDescription, workspaceType);
  }
  return (
    <Dialog maxWidth={"lg"}
            fullWidth={true}
            onClose={() => hiddenCreateWorkSpaceDialog()}
            open={show}
            disableAutoFocus={true} >
      { step === STEPS.SET_WORKSPACE && (
        <Box>
          <Grid container  className={style.content}>
            <Grid item xs={6} className={style.form}>
              <Typography variant={'h5'}>Start your works!</Typography>
              <Typography variant={'p'}>
                Improve your productivity by making it easier for everyone to access board in one place
              </Typography>
              <Box className={style.smallTitle}>
                <Typography variant={'p'} >Workspace Name</Typography>
              </Box>
              <Box style={{marginTop: '10px'}}>
                <TextField fullWidth
                           value={workspaceName}
                           onChange={e => setWorkspaceName(e.target.value)}
                           placeholder={`Taco's company`}
                           size={'small'}/>
                <Typography className={style.inputTip}>This is the name of your company, team or organization.</Typography>
              </Box>
              <Box className={style.smallTitle}>
                <Typography variant={'p'} >Workspace Type</Typography>
              </Box>
              <Box style={{marginTop: '10px'}}>
                <Select fullWidth
                        onChange={e => setWorkspaceType(e.target.value)}
                        style={{height: 35}}
                        value={workspaceType}>
                  <MenuItem value={'business'}>business</MenuItem>
                  <MenuItem value={'home'}>home</MenuItem>
                  <MenuItem value={'bookclub'}>bookclub</MenuItem>
                </Select>
              </Box>
              <Box className={style.smallTitle}>
                <Typography variant={'p'} >Workspace Description</Typography>
                <span className={style.optional}>Optional</span>
              </Box>
              <Box style={{marginTop: '10px'}}>
                <TextField multiline
                           value={workspaceDescription}
                           onChange={e => setWorkspaceDescription(e.target.value)}
                           fullWidth
                           rows={5}/>
              </Box>
              <Box style={{marginTop: '40px'}}>
                <Button variant={'contained'}
                        disabled={!valid}
                        onClick={handleClickNext}
                        fullWidth
                        color={'info'}>
                  Next
                </Button>
              </Box>
            </Grid>
            <Grid item xs={6} className={style.background}></Grid>
          </Grid>
        </Box>
      )
      }
      {step === STEPS.INVITE_PARTNER && (
        <Box>
          <Grid container  className={style.content}>
            <Grid item xs={6} className={style.form}>
              <Typography variant={'h5'}>Invite your team</Typography>
              <Typography variant={'p'}>
                Trello makes teamwork work best. Invite your new team members to continue to cooperate!
              </Typography>
              <Box className={style.smallTitle}>
                <Typography variant={'p'} >Workspace members</Typography>
              </Box>
              <Box style={{marginTop: '10px'}}>
                <TextField fullWidth
                           value={memberEmail}
                           onChange={e => setMemberEmail(e.target.value)}
                           placeholder={'Eg. dwq3213@cloud.ci'}
                           size={'small'}/>
              </Box>
              <Box style={{marginTop: '40px'}}>
                <Button variant={'contained'}
                        disabled={!valid}
                        onClick={handleConfirmCreate}
                        fullWidth
                        color={'info'}>Invite to workspace</Button>
              </Box>
            </Grid>
            <Grid item xs={6} className={style.background}></Grid>
          </Grid>
        </Box>
      )}

    </Dialog>
  )
}