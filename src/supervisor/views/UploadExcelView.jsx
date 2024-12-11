import React, { useState } from 'react';
import { Box, Typography, Button, TextField, Tabs, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import EjemploCompetencia from './EjemploCompetencia';
import EjemploCursos from './EjemploCursos';
import EjemploPlanEstudios from './EjemploPlanEstudios';

export function UploadExcelView({ handleFileChange, handleSubmit, sheetsData }) {
  const [currentTab, setCurrentTab] = useState(0); // Controlar la pestaña activa
  const [showExamples, setShowExamples] = useState(false); // Estado para controlar la visibilidad de los ejemplos

  // Cambiar de pestaña
  const handleTabChange = (event, newValue) => {
    setCurrentTab(newValue);
  };

  // Función para alternar la visibilidad de los ejemplos
  const toggleExamples = () => {
    setShowExamples((prev) => !prev);
  };

  return (
    <Box sx={{ maxWidth: 1400, margin: '0 auto', padding: 4 }}>
      {/* Título principal */}
      <Typography variant="h4" component="h1" align="center" gutterBottom>
        Plan de Estudios
      </Typography>

      {/* Subtítulo */}
      <Typography variant="h6" component="h2" align="center" gutterBottom>
        Cargar Plan de Estudios
      </Typography>

      {/* Formulario */}
      <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          gap: 2,
          marginBottom: 4,
        }}
      >
        {/* Campo para seleccionar archivo */}
        <TextField
          type="file"
          inputProps={{ accept: '.xlsx, .xls' }}
          onChange={handleFileChange}
          sx={{
            '& input': {
              cursor: 'pointer',
            },
          }}
        />

        {/* Botón para enviar */}
        <Button variant="contained" type="submit">
          Subir
        </Button>
      </Box>

      {/* Botón para alternar ejemplos */}
      <Button variant="outlined" onClick={toggleExamples} sx={{ marginBottom: 2 }}>
        {showExamples ? 'Ocultar Ejemplos' : 'Mostrar Ejemplos'}
      </Button>

      {/* Mostrar ejemplos si 'showExamples' es verdadero */}
      {showExamples && (
        <>        
          <Typography variant="h6" component="h2" gutterBottom>
            Asegúrese de que el archivo Excel tenga el siguiente formato, y que vallan en el orden mostrado
          </Typography>
          <EjemploPlanEstudios />          
          <EjemploCursos />
          <EjemploCompetencia />
        </>
      )}

      {/* Tabs para visualizar hojas */}
      {sheetsData.length > 0 && (
        <Box>
          <Tabs
            value={currentTab}
            onChange={handleTabChange}
            variant="scrollable"
            scrollButtons="auto"
            aria-label="Hojas de Excel"
          >
            {sheetsData.map((sheet, index) => (
              <Tab label={sheet.name} key={index} />
            ))}
          </Tabs>

          {/* Contenido de cada hoja */}
          <Box sx={{ marginTop: 3 }}>
            <TableContainer component={Paper} sx={{ overflowX: 'auto', maxHeight: 500 }}>
              <Table stickyHeader>
                <TableHead>
                  <TableRow>
                    {sheetsData[currentTab].data[0]?.map((header, i) => (
                      <TableCell key={i} sx={{ minWidth: 120, textAlign: 'center' }}>
                        {header}
                      </TableCell>
                    ))}
                  </TableRow>
                </TableHead>
                <TableBody>
                  {sheetsData[currentTab].data.slice(1).map((row, i) => (
                    <TableRow key={i}>
                      {row.map((cell, j) => (
                        <TableCell key={j} sx={{ textAlign: 'center' }}>
                          {cell}
                        </TableCell>
                      ))}
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Box>
        </Box>
      )}
    </Box>
  );
}

export default UploadExcelView;
