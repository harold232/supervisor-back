// servisorRouter.jsx
import { Route, Routes } from 'react-router-dom'
import { SnackbarProvider } from 'notistack';
import UploadExcel  from '../pages/UploadExcel'
import { SupervisorMain } from '../pages/SupervisorMain';
export const SupervisorRouter = () => {

 return (
  <SnackbarProvider maxSnack={3} autoHideDuration={2000}>
    <Routes>
      <Route path='/' element={ <SupervisorMain/> } />
      <Route path='cargarexcel' element={ <UploadExcel/> } />        
    </Routes>
    </SnackbarProvider>
  )
}
