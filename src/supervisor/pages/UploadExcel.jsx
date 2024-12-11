import React from 'react';
import useUploadExcel from '../hooks/useUploadExcel';
import UploadExcelView from '../views/UploadExcelView';
import { Modal, Box, Button, Typography } from '@mui/material';

function UploadExcel() {
  const {
    sheetsData,
    showConfirmation,
    handleFileChange,
    handleSubmit,
    confirmUpload,
    cancelUpload,
  } = useUploadExcel();

  return (
    <>
      {/* Vista con Tabs */}
      <UploadExcelView
        handleFileChange={handleFileChange}
        handleSubmit={handleSubmit}
        sheetsData={sheetsData} // Pasar datos de hojas
      />

      {/* Modal de confirmación */}
      <Modal open={showConfirmation} onClose={cancelUpload}>
        <Box
          sx={{
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            bgcolor: 'background.paper',
            p: 4,
            boxShadow: 24,
            borderRadius: 2,
          }}
        >
          <Typography variant="h6" gutterBottom>
            ¿Estás seguro de que deseas cargar este archivo?
          </Typography>
          <Button
            variant="contained"
            color="primary"
            onClick={confirmUpload}
            sx={{ marginRight: 2 }}
          >
            Confirmar
          </Button>
          <Button variant="outlined" onClick={cancelUpload}>
            Cancelar
          </Button>
        </Box>
      </Modal>
    </>
  );
}

export default UploadExcel;
