import React from 'react';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

export const EjemploPlanEstudios = () => {
  return (
    <Box sx={{ maxWidth: 1400, margin: '0 auto', padding: 4 }}>
      <Typography variant="h4" component="h1" align="center" gutterBottom>
        Ejemplo de Formato de Planes de Estudios
      </Typography>

      {/* Ejemplo visual del formato esperado */}
      <TableContainer component={Paper} sx={{ overflowX: 'auto' }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Código</TableCell>
              <TableCell>Descripción</TableCell>
              <TableCell>Vigencia</TableCell>
              <TableCell>Institución ID</TableCell>
              <TableCell>Departamento ID</TableCell>
              <TableCell>Estado</TableCell>
              <TableCell>Carrera ID</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>P2023</TableCell>
              <TableCell>Plan 2023</TableCell>
              <TableCell>01.01.2023 - 31.12.2028</TableCell>
              <TableCell>1</TableCell>
              <TableCell>3</TableCell>
              <TableCell>1</TableCell>
              <TableCell>2</TableCell>
            </TableRow>
            <TableRow>
              <TableCell>P2028</TableCell>
              <TableCell>Plan 2028</TableCell>
              <TableCell>01.01.2028 - 31.12.2033</TableCell>
              <TableCell>1</TableCell>
              <TableCell>3</TableCell>
              <TableCell>1</TableCell>
              <TableCell>2</TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default EjemploPlanEstudios;
