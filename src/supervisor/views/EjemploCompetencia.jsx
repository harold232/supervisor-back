import React from 'react';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

export const EjemploCompetencia = () => {
  return (
    <Box sx={{ maxWidth: 1400, margin: '0 auto', padding: 4 }}>
      <Typography variant="h4" component="h1" align="center" gutterBottom>
        Ejemplo de Formato de Competencia
      </Typography>

      {/* Ejemplo visual del formato esperado */}
      <TableContainer component={Paper} sx={{ overflowX: 'auto' }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Código</TableCell>
              <TableCell>Nombre</TableCell>
              <TableCell>Descripción</TableCell>
              <TableCell>Plan ID</TableCell>
              <TableCell>Institución ID</TableCell>
              <TableCell>Departamento ID</TableCell>
              <TableCell>Tipo</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>CE14</TableCell>
              <TableCell>Desarrollo de aplicaciones móviles</TableCell>
              <TableCell>
                Diseña y desarrolla aplicaciones móviles utilizando las mejores
                prácticas y tecnologías actuales.
              </TableCell>
              <TableCell>1</TableCell>
              <TableCell>1</TableCell>
              <TableCell>3</TableCell>
              <TableCell>E</TableCell>
            </TableRow>
            <TableRow>
              <TableCell>CE15</TableCell>
              <TableCell>Gestión de bases de datos</TableCell>
              <TableCell>
                Gestión - optimización y administración de bases de datos en
                entornos distribuidos.
              </TableCell>
              <TableCell>1</TableCell>
              <TableCell>1</TableCell>
              <TableCell>3</TableCell>
              <TableCell>E</TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default EjemploCompetencia;
