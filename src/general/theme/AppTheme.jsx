import { ThemeProvider } from "@emotion/react";
import { CssBaseline } from "@mui/material";
import { purpleTheme } from "./purpleTheme";
import { Alumnostheme } from './AlumnosTheme';
export const AppTheme = ( {children} ) => {
  return (
    <ThemeProvider theme={Alumnostheme}>
        {/* CssBaseline kickstart an elegant, consistent, and simple baseline to build upon. */}
        <CssBaseline />
        
        { children }
    </ThemeProvider>
  )
}
