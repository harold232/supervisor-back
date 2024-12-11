import { createTheme } from '@mui/material/styles';

export const Alumnostheme = createTheme({
  palette: {
    text: {
      primary: '#292929', // TextColor
    },
    background: {
      default: '#EFF1F6', // BackgroundColor
      paper: '#E9EAEE', // BackgroundSecondColor
    },
    primary: {
      main: '#205274', // PrimaryColor
    },
    secondary: {
      main: '#71384D', // SecondaryColor
    },
    accent: {
      main: '#AE675B', // AccentColor
    },
    action: {
      hover: '#D1C5CD', // HoverButtonColor
    },
    threeColor: {
      main: '#C9B9C3', // ThreeColor
    },
  },
  typography: {
    fontFamily: `"Poppins", "Roboto", "Helvetica", "Arial", sans-serif`,
    fontSize: 14,
    fontWeightLight: 300,
    fontWeightRegular: 400,
    fontWeightMedium: 500
  }
});
