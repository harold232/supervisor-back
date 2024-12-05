import React from 'react';

function UploadExcelView({ handleFileChange, handleSubmit }) {
  return (
    <div>
      <center>
        <h1>Plan de Estudios</h1>
      </center>
      <h2>Cargar Plan de Estudios</h2>
      <center>
        <form onSubmit={handleSubmit}>
          <input type="file" onChange={handleFileChange} accept=".xlsx, .xls" />
          <button type="submit">Subir</button>
        </form>
      </center>
    </div>
  );
}

export default UploadExcelView;
