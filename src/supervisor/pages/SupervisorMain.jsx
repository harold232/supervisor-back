import React from 'react';
import { Typography, Container, Card, Box, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export const SupervisorMain = () => {
  const navigate = useNavigate();

  const handleNavigate = () => {
    navigate('cargarexcel');
  };

  return (
    <Container maxWidth="md" sx={{ marginTop: 4 }}>
      <Card sx={{ padding: 4, boxShadow: 3 }}>
        {/* Título principal */}
        <Typography
          variant="h4"
          color="primary"
          sx={{
            marginBottom: 3,
            fontWeight: 'bold',
            textAlign: 'center',
          }}
        >
          Bienvenido al módulo de supervisor
        </Typography>

        {/* Descripción */}
        <Typography
          variant="h6"
          color="textSecondary"
          sx={{ marginBottom: 4, textAlign: 'justify' }}
        >
          Este módulo le permite cargar y subir planes de estudios, cursos y nuevas competencias.
        </Typography>

        {/* Botón para navegar */}
        <Box sx={{ display: 'flex', justifyContent: 'center' }}>
          <Button
            variant="contained"
            color="primary"
            size="large"
            onClick={handleNavigate}
            sx={{
              borderRadius: 3,
              paddingX: 4,
              textTransform: 'none',
              fontWeight: 'bold',
            }}
          >
            Ir a Cargar Plan de Estudios
          </Button>
        </Box>
      </Card>
    </Container>
  );
};

export default SupervisorMain;
