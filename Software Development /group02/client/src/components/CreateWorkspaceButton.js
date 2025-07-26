import {Box, Button, Typography }from "@mui/material";
import style from './createworkspacebutton.module.css';
import ClearIcon from '@mui/icons-material/Clear';
import PeopleOutlineOutlinedIcon from '@mui/icons-material/PeopleOutlineOutlined';
import {useState} from "react";
import {CreateWorkSpaceConsumer as useCreateWorkSpaceDialog} from "./CreateWorkspaceDialogHook";
function CreateWorkspaceButton() {
  const {showCreateWorkSpaceDialog} = useCreateWorkSpaceDialog();
  const [showDashbaord, setShowDashboard] = useState(false);
  const handleClickCreate = () => {
    setShowDashboard(true);
  }
  const handleCreate = () => {
    showCreateWorkSpaceDialog();
  }
  return (
    <Box className={style.container}>
      <Button color="inherit" onClick={handleClickCreate}>CREATE</Button>
      {showDashbaord && (
        <Box className={style.dashboard} >
          <Box className={style.titleBar}>
            <Typography >CREATE</Typography>
            <ClearIcon onClick={() => setShowDashboard(false)}/>
          </Box>
          <Box className={style.row} onClick={handleCreate}>
            <Box className={style.titleRow}>
              <PeopleOutlineOutlinedIcon className={style.titleIcon}/>
              <Typography className={style.titleText}>Create Workspace</Typography>
            </Box>
            <Typography className={style.description}>
              The workspace is a group of board and personnel. You can use it to organize your company, sideline, family or friends
            </Typography>
          </Box>
        </Box>
      )}
    </Box>
  )
}
export default CreateWorkspaceButton;