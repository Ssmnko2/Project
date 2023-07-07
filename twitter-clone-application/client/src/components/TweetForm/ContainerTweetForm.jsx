import React, { useState, useEffect } from "react";
import {
    Button,
    Container,
    Menu,
    MenuItem,
    ListItemIcon,
    ListItemText,
    Typography,
    Box,
    IconButton,
} from "@mui/material";
import { Avatar } from '@mui/material';
import { styled } from '@mui/material/styles';
import { TextareaAutosize } from "@mui/material";
import CheckIcon from "@mui/icons-material/Check";
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import PublicIcon from "@mui/icons-material/Public";
import PeopleIcon from "@mui/icons-material/People";
import AlternateEmailOutlinedIcon from '@mui/icons-material/AlternateEmailOutlined';
// import Link from '@mui/material/Link';
import { useTheme } from '@mui/material/styles';
import { useFetch } from "../../hooks/UseFetch";
import { useParams, Link } from 'react-router-dom';


export default function ContainerTweetForm({ open, onClose, setTweetText, tweetText, withId }) {

    const theme = useTheme();

    const [buttonColor, setButtonColor] = useState(null);

    useEffect(() => {
        const savedColor = localStorage.getItem('buttonColor');
        if (savedColor) {
            setButtonColor(savedColor);
        }
    }, []);

    const { id } = useParams()

    const StyledAvatar = styled(Avatar)(({ theme }) => ({
        position: 'relative',
        '&:before': {
            content: '""',
            position: 'absolute',
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            backgroundColor: 'rgba(0, 0, 0, 0.3)',
            opacity: 0,
            transition: 'opacity 0.3s ease',
        }
    }));

    const [{ data, loading }, getData] = useFetch({
        initData: {},
        url: withId
            ? `user/getuser/${id}`
            : 'user/profile',
        method: 'GET',
        dataTransformer: (data) => {
            console.log(data)
            return data;
        },
    });

    useEffect(() => {
        getData()
    }, [id])


    if (!loading) <p>loading...</p>

    const { username, av_imagerUrl } = data

    const [selectedValue, setSelectedValue] = useState("");
    const [anchorEl, setAnchorEl] = useState(null);

    const handleButtonClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleMenuClose = (value) => {
        setSelectedValue(value);
        setAnchorEl(null);
    };

    const [selectedValue2, setSelectedValue2] = useState("");
    const [anchorEl2, setAnchorEl2] = useState(null);

    const handleButtonClick2 = (event) => {
        setAnchorEl2(event.currentTarget);
    };

    const handleMenuClose2 = (value) => {
        setSelectedValue2(value);
        setAnchorEl2(null);
    };

    // Визначення іконки для кожного пункту у другому випадаючому меню
    let icon;
    switch (selectedValue2) {
        case "Everyone can reply":
            icon = <PublicIcon sx={{
                color: buttonColor,
                marginRight: '4px',
                fontSize: '16px'
            }} />;
            break;
        case "People you follow can reply":
            icon = <PeopleIcon sx={{
                color: buttonColor,
                marginRight: '4px',
                fontSize: '16px'
            }} />;
            break;
        case "Only people you mention can reply":
            icon = <AlternateEmailOutlinedIcon sx={{
                color: buttonColor,
                marginRight: '4px',
                fontSize: '16px'
            }} />;
            break;
        default:
            icon = <PublicIcon sx={{
                color: buttonColor,
                marginRight: '4px',
                fontSize: '16px'
            }} />;
    }

    const renderTwitterCircleMenuItem = () => {
        const peopleCount = 0; // Здесь можно задать количество людей

        return (
            <MenuItem onClick={() => handleMenuClose('Twitter Circle')} sx={{ marginTop: '8px' }}>
                <ListItemIcon>
                    <PeopleIcon sx={{ color: 'green' }} />
                </ListItemIcon>
                <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                    <ListItemText primary="Twitter Circle" />
                    <Box sx={{ display: 'flex', alignItems: 'center' }}>
                        <Typography variant="caption" sx={{
                            display: 'flex',
                            color: 'gray',
                            marginRight: '8px'
                        }}>
                            <Box sx={{
                                marginInline: '4px',
                                fontWeight: '700'
                            }}>
                                {peopleCount}
                            </Box>
                            People
                        </Typography>
                        <IconButton>
                            <Link href='#' color="inherit" sx={{
                                fontWeight: '700',
                                fontSize: '14px'
                            }}>
                                {'Edit'}
                            </Link>
                        </IconButton>
                    </Box>
                </Box>
                {selectedValue === 'Twitter Circle' && <CheckIcon sx={{
                    color: buttonColor,
                    marginLeft: '16px'
                }} />}
            </MenuItem>
        );
    };

    return (
        <>
            <Container sx={{ display: 'flex', marginTop: '20px' }}>

                <Link to={`/profile`} >
                    <StyledAvatar alt="User Avatar"
                        // src='../../img/avatar.png'
                        src={av_imagerUrl}
                    />
                </Link>
                <Button onClick={handleButtonClick} endIcon={<ArrowDropDownIcon />}
                    sx={{
                        height: '20px',
                        marginLeft: '8px',
                        textTransform: 'none',
                        border: selectedValue === 'Twitter Circle' ? '1px solid green' : '1px solid black',
                        borderRadius: '20px',
                        color: selectedValue === 'Twitter Circle' ? 'green' : buttonColor,
                    }} >
                    {selectedValue || "Everyone"}
                </Button>
                <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={() => handleMenuClose(selectedValue)}>
                    <Typography variant="h6" sx={{ marginLeft: '12px' }}>
                        Choose audience
                    </Typography>
                    <MenuItem onClick={() => handleMenuClose("Everyone")} >
                        <ListItemIcon>
                            <PublicIcon sx={{ color: buttonColor }} />
                        </ListItemIcon>
                        <ListItemText primary="Everyone" />
                        <ListItemIcon>
                            {selectedValue === "Everyone" && <CheckIcon sx={{
                                color: buttonColor,
                                marginLeft: '16px'
                            }} />}
                        </ListItemIcon>
                    </MenuItem >
                    {renderTwitterCircleMenuItem()}
                </Menu>

            </Container>

            <TextareaAutosize onChange={e => setTweetText(e.target.value)} value={tweetText} placeholder="What's happening?" style={{
                width: '300px',
                height: '100px',
                marginBottom: '10px',
                border: '1px solid transparent',
                marginLeft: '50px',
                outline: 'none',
                resize: 'none',
                fontSize: '20px',
                fontFamily: 'sans-serif',
                color: theme.palette.text.primary,
                backgroundColor: theme.palette.background.default
            }
            } />

            <Box>
                <Button
                    onClick={handleButtonClick2}
                    sx={{
                        display: "inline-flex",
                        paddingLeft: "24px",
                        height: "20px",
                        textTransform: "none",
                        border: "none",
                        borderRadius: "20px",
                        overflow: "hidden",
                        whiteSpace: "nowrap",
                        color: buttonColor
                    }}
                >
                    {icon}
                    {selectedValue2 || "Everyone can reply"}
                </Button>

                <Menu anchorEl={anchorEl2} open={Boolean(anchorEl2)} onClose={() => handleMenuClose2(selectedValue2)}>
                    <Typography variant="h6" sx={{ marginLeft: '12px' }}>
                        Who can reply?
                    </Typography>
                    <Typography sx={{
                        marginInline: '10px',
                        marginBottom: '10px',
                        fontSize: '14px'
                    }}>
                        Choose who can reply to this Tweet.<br /> Anyone mentioned can always reply.
                    </Typography>
                    <MenuItem onClick={() => handleMenuClose2("Everyone can reply")} >
                        <ListItemIcon>
                            <PublicIcon sx={{ color: buttonColor, marginRight: '8px' }} />
                        </ListItemIcon>
                        <ListItemText primary="Everyone" />
                        <ListItemIcon>
                            {selectedValue2 === "Everyone can reply" && <CheckIcon sx={{ color: buttonColor }} />}
                        </ListItemIcon>
                    </MenuItem >
                    <MenuItem onClick={() => handleMenuClose2("People you follow can reply")}>
                        <ListItemIcon>
                            <PeopleIcon sx={{ color: buttonColor, marginRight: '8px' }} />
                        </ListItemIcon>
                        <ListItemText primary="People you follow" />
                        <ListItemIcon>
                            {selectedValue2 === "People you follow can reply" && <CheckIcon sx={{
                                color: buttonColor,
                                marginLeft: '4px'
                            }} />}
                        </ListItemIcon>
                    </MenuItem>
                    <MenuItem onClick={() => handleMenuClose2("Only people you mention can reply")}>
                        <ListItemIcon>
                            <AlternateEmailOutlinedIcon sx={{ color: buttonColor, marginRight: '8px' }} />
                        </ListItemIcon>
                        <ListItemText primary="Only people you mention" />
                        <ListItemIcon>
                            {selectedValue2 === "Only people you mention can reply" && <CheckIcon sx={{
                                color: buttonColor,
                                marginLeft: '8px'
                            }} />}
                        </ListItemIcon>
                    </MenuItem>
                </Menu>
            </Box>
        </>
    );
}