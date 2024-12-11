import React from 'react';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

export const EjemploCursos = () => {
  return (
    <Box sx={{ maxWidth: 1400, margin: '0 auto', padding: 4 }}>
      <Typography variant="h4" component="h1" align="center" gutterBottom>
        Ejemplo de Formato de Cursos
      </Typography>
      {/* Ejemplo visual del formato esperado */}
      <TableContainer component={Paper} sx={{ overflowX: 'auto' }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Código</TableCell>
              <TableCell>Nombre</TableCell>
              <TableCell>Tipo</TableCell>
              <TableCell>Num. Horas Teoría</TableCell>
              <TableCell>Num. Horas Práctica</TableCell>
              <TableCell>Num. Horas Laboratorio</TableCell>
              <TableCell>Num. Horas Campo</TableCell>
              <TableCell>Num. Créditos</TableCell>
              <TableCell>Ciclo</TableCell>
              <TableCell>Estado</TableCell>
              <TableCell>Periodo Académico ID</TableCell>
              <TableCell>Plan de Estudios ID</TableCell>
              <TableCell>Institución ID</TableCell>
              <TableCell>Departamento ID</TableCell>
              <TableCell>Sumilla</TableCell>
              <TableCell>Modalidad</TableCell>
              <TableCell>Etiquetas</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>50W0111</TableCell>
              <TableCell>Inteligencia Artificial - G1 - 2024-II</TableCell>
              <TableCell>obligatorio</TableCell>
              <TableCell>3</TableCell>
              <TableCell>0</TableCell>
              <TableCell>2</TableCell>
              <TableCell>0</TableCell>
              <TableCell>3</TableCell>
              <TableCell>IX</TableCell>
              <TableCell>1</TableCell>
              <TableCell>4</TableCell>
              <TableCell>1</TableCell>
              <TableCell>1</TableCell>
              <TableCell>3</TableCell>
              <TableCell>Híbrido</TableCell>
              <TableCell>IA</TableCell>
            </TableRow>
            <TableRow>
              <TableCell>50W0112</TableCell>
              <TableCell>Machine Learning - G1 - 2024-II</TableCell>
              <TableCell>obligatorio</TableCell>
              <TableCell>3</TableCell>
              <TableCell>0</TableCell>
              <TableCell>2</TableCell>
              <TableCell>0</TableCell>
              <TableCell>3</TableCell>
              <TableCell>IX</TableCell>
              <TableCell>1</TableCell>
              <TableCell>4</TableCell>
              <TableCell>2</TableCell>
              <TableCell>1</TableCell>
              <TableCell>3</TableCell>
              <TableCell>Presencial</TableCell>
              <TableCell>ML</TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default EjemploCursos;
