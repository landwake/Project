import {Box, Modal, Typography, TextField, Button} from '@mui/material';
import style from './style.module.css';
import ClearIcon from '@mui/icons-material/Clear';
import {useEffect, useState} from "react";
function CreateBoardDialog({show, onClose, onSubmit}) {
  const [boardTitle, setBoardTitle] = useState('');
  const [boardDescription, setBoardDescription] = useState('');
  const [valid, setValid] = useState(false);
  const [open, setOpen] = useState(false);
  useEffect(() => {
    setOpen(show);
  }, [show]);
  useEffect(() => {
    if (!open) {
      onClose && onClose();
    }
  }, [open]);
  useEffect(() => {
    setValid(boardTitle && boardDescription);
  }, [boardTitle, boardDescription]);
  return (
    <Modal open={open} hideBackdrop={true} >
      <Box className={style.content}>
        <Box className={style.titleBar}>
          <Typography>Create Board</Typography>
          <ClearIcon style={{cursor: 'pointer'}} onClick={() => onClose && onClose()}/>
        </Box>
        <Box marginTop={'10px'}>
          <Typography variant={'p'}>Board Title</Typography>
          <TextField fullWidth
                     value={boardTitle}
                     onChange={e => setBoardTitle(e.target.value)}
                     size={'small'}/>
        </Box>
        <Box marginTop={'10px'}>
          <Typography variant={'p'}>Board Description</Typography>
          <TextField fullWidth
                     value={boardDescription}
                     onChange={e => setBoardDescription(e.target.value)}
                     multiline
                     rows={4}
                     size={'small'}/>
        </Box>
        <Box marginTop={'10px'}>
          <Button variant={'contained'}
                  fullWidth
                  disabled={!valid}
                  onClick={() => {
                    onSubmit && onSubmit(boardTitle, boardDescription)
                  }}
          >Create</Button>
        </Box>
      </Box>
    </Modal>
  )
}

export default CreateBoardDialog;