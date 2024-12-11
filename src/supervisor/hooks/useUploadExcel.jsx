import { useState } from 'react';
import * as XLSX from 'xlsx';
import { useSnackbar } from 'notistack';

function useUploadExcel() {
  const [file, setFile] = useState(null);
  const [sheetsData, setSheetsData] = useState([]); // Datos de las hojas
  const [showConfirmation, setShowConfirmation] = useState(false);
  const { enqueueSnackbar } = useSnackbar();
  const handleFileChange = (e) => {
    const uploadedFile = e.target.files[0];
    setFile(uploadedFile);

    const reader = new FileReader();
    reader.onload = (event) => {
      const data = new Uint8Array(event.target.result);
      const workbook = XLSX.read(data, { type: 'array' });

      // Procesar las hojas del archivo
      const sheets = workbook.SheetNames.map((sheetName) => {
        const sheetData = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName], { header: 1 }); // Leer filas
        return { name: sheetName, data: sheetData }; // Guardar nombre y datos
      });

      setSheetsData(sheets);
    };

    reader.readAsArrayBuffer(uploadedFile);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!file) {
      enqueueSnackbar('Por favor selecciona un archivo.', { variant: 'info' });
      return;
    }

    setShowConfirmation(true);
  };

  const confirmUpload = () => {
    const formData = new FormData();
    formData.append('file', file);

    fetch('http://localhost:8080/v1/planestudios/cargar', {
      method: 'POST',
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          enqueueSnackbar('¡Archivo cargado exitosamente!', { variant: 'success' });
        } else {
          enqueueSnackbar('Error en la carga, asegurse que el formato del archivo sea igual al de la base de datos', { variant: 'warning' });
        }
        console.log(response);
      })
      .catch((error) => {
        enqueueSnackbar('Error al cargar el archivo', { variant: 'error' });
        console.error("Error al cargar el archivo:", error);        
      });
      
    setShowConfirmation(false);
  };
  const cancelUpload = () => {
    enqueueSnackbar('Carga cancelada', { variant: 'info' });
    setShowConfirmation(false); // Cierra el modal
  };

  return {
    file,
    sheetsData,
    showConfirmation,
    handleFileChange,
    handleSubmit,
    confirmUpload,
    cancelUpload,
  };
}

export default useUploadExcel;
