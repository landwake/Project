import {createContext, useContext, useEffect, useState} from "react";

const useCreateWorkSpaceDialog = () => {
  const [show, setShow] = useState(false);
  const showCreateWorkSpaceDialog = () => {
    setShow(true);
  }
  const hiddenCreateWorkSpaceDialog = () => {
    setShow(false);
  }
  useEffect(() => {

  }, [show]);
  return {
    show,
    showCreateWorkSpaceDialog,
    hiddenCreateWorkSpaceDialog
  }
}

const CreateWorkSpaceContext = createContext();
export const CreateWorkSpaceProvider = ({children}) => {
  const value = useCreateWorkSpaceDialog();
  return <CreateWorkSpaceContext.Provider value={value}>{children}</CreateWorkSpaceContext.Provider>
}

export const CreateWorkSpaceConsumer = () => {
  return useContext(CreateWorkSpaceContext);
}
