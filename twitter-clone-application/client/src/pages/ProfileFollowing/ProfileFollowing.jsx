import React, { useState, useEffect } from 'react'
import { Typography, Tab, Tabs, Grid } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import {
  IconButton,
  Toolbar,
  Box
} from '@mui/material';
import WestIcon from '@mui/icons-material/West';
import { useTheme } from '@mui/material/styles';
import Following from '../../components/Follower/Following';
import { useFetch } from "../../hooks/UseFetch";
import { useParams } from 'react-router-dom';

const ProfileFollowing = ({ withId }) => {
  const { id } = useParams()

  const [{ data, loading }, getData] = useFetch({
    initData: {},
    url: withId
      ? `user/getuser/${id}`
      : 'user/profile',
    method: 'GET',
    dataTransformer: (data) => {
      return data;
    },
  });

  if (!loading) <p>loading...</p>

  const { username, firstName, lastName, email, location, birthdate, bio } = data


  const [value, setValue] = React.useState(1);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const [buttonColor, setButtonColor] = useState();

  useEffect(() => {
    const savedColor = localStorage.getItem('buttonColor');
    if (savedColor) {
      setButtonColor(savedColor);
    }
  }, [buttonColor]);

  const theme = useTheme();

  const ToolbarStyles = {
    backgroundColor: theme.palette.background.default,
    color: theme.palette.text.default
  };

  return (
    <>
      <div>ProfileFollowing</div>
      <Grid
        sx={{
          borderRight: '1px solid grey',
          borderLeft: '1px solid grey',
          height: '100vh',
          overflow: 'auto',
        }}>
        <Box position='fixed' sx={{
          zIndex: '99',
          top: '0',
        }}>
          <Toolbar style={ToolbarStyles} >
            <RouterLink to={'/profile'}>
              <IconButton color='gray'>
                <WestIcon />
              </IconButton>
            </RouterLink>
            <Box ml={2}>

              <Typography variant='h6'>{firstName} {lastName}</Typography>

              <Typography>@{username}</Typography>
            </Box>
          </Toolbar>
        </Box>

        <Tabs variant="fullWidth" value={value} textColor="inherit" onChange={handleChange} sx={{
          marginTop: '20px',
          position: 'fixed', // Задаем фиксированную позицию для табов
          top: '50px', // Располагаем табы в верхней части экрана
          zIndex: 90, // Устанавливаем z-index для обеспечения отображения поверх других элементов
          width: '41%', // Задаем ширину табов на 100% экрана
          backgroundColor: theme.palette.background.default,
          "& .MuiTabs-indicator": {
            backgroundColor: 'gray',
            borderBottom: `2px solid ${buttonColor}`,
          },
          "& .Mui-selected": {
            color: 'primary',
          },
        }} >
          <Tab label="Followers" href='/profile/followers' sx={{ textTransform: 'none' }}></Tab>
          <Tab label="Following" sx={{ textTransform: 'none' }}></Tab>
        </Tabs>
        <Box sx={{ marginTop: '70px' }}>
          <Following />
        </Box>
      </Grid>
    </>
  )
}

export default ProfileFollowing
