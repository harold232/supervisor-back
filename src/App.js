import React from 'react';
import './General/theme/App.css';
import UploadExcel from './CargarExcel/pages/UploadExcel';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <UploadExcel />
      </header>
    </div>
  );
}

export default App;
