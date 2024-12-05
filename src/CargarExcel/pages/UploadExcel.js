import React from 'react';
import UploadExcelView from '../views/UploadExcelView';
import useUploadExcel from '../../General/hooks/useUploadExcel';

function UploadExcel() {
  const { handleFileChange, handleSubmit } = useUploadExcel();

  return (
    <UploadExcelView 
      handleFileChange={handleFileChange}
      handleSubmit={handleSubmit}
    />
  );
}

export default UploadExcel;
