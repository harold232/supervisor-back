import { Navigate, Route, Routes } from 'react-router-dom'

import { AuthRoutes } from '../../seguridad/routes/AuthRoutes'
import { CursosRoutes } from '../../cursos/routes/CursosRoutes'

import { CheckingAuth } from '../../seguridad/components';
import { useCheckAuth } from '../../seguridad/hooks';
import { SupervisorRouter } from '../../supervisor/routes/SupervisorRouter';
export const AppRouter = () => {

  const status = useCheckAuth();

  if ( status === 'checking' ) {
    return <CheckingAuth />
  }

 return (
    <Routes>
      
        {/*
          (status === 'authenticated') 
           ? 
            <Route path="/cursos/*" element={ <CursosRoutes /> } />
            : <Route path="/auth/*" element={ <AuthRoutes /> } />
        */}
        <Route path='/supervisor/*' element ={<SupervisorRouter/>}/>
        <Route path='/*' element={ <Navigate to='/auth/login' />  } />
        
    </Routes>
  )
}
