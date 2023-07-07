import React, { useState, useEffect } from 'react';
import { Typography, Tab, Tabs } from '@mui/material';
import { TabPanel, TabContext } from '@mui/lab';
import { Avatar } from '@mui/material';
import { useFetch } from "../../hooks/UseFetch";
import Tweet from "../../components/Tweet/Tweet";


export const TabsProfile = () => {

    const [value, setValue] = React.useState(0);

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

    const [{ data, loading }, getData] = useFetch({
        initData: [],
        url: `/tweets/tweet/all?sizePage=10&numberPage=1`,
        method: 'GET',
        dataTransformer: (data) => {
            return data.listDto
        },
    });


    return (
        <>
            <Tabs variant="fullWidth" value={value} textColor="inherit" onChange={handleChange} sx={{
                marginTop: '20px',
                "& .MuiTabs-indicator": {
                    backgroundColor: 'gray',
                    borderBottom: `2px solid ${buttonColor}`
                },
                "& .Mui-selected": {
                    color: 'primary',
                },
            }} >
                <Tab label="Tweets" sx={{ textTransform: 'none' }}></Tab>
                <Tab label="Replies" sx={{ textTransform: 'none' }}></Tab>
                <Tab label="Media" sx={{ textTransform: 'none' }}></Tab>
                <Tab label="Linkes" sx={{ textTransform: 'none' }}></Tab>
            </Tabs>

            <TabContext value={value} >
                <TabPanel value={0} index={0}>

                    <Typography mb={2} sx={{
                        fontSize: '20px',
                        fontWeight: '900'
                    }}>
                        Who to follow
                    </Typography>
                    {/* <Typography variant="h4" >Tweets</Typography>
                    <Typography variant="body1">Here are the tweets</Typography> */}

                    {loading && "Loading..."}
                    {...data.map(t => <Tweet tweet={t} />)}

                </TabPanel>
                <TabPanel value={1} index={1}>

                    <Typography mb={2} sx={{
                        fontSize: '20px',
                        fontWeight: '900'
                    }}>

                        Who to follow
                    </Typography>
                    <Typography variant="h4">Replies</Typography>
                    <Typography variant="body1">Here are the replies</Typography>
                </TabPanel>
                <TabPanel value={2} index={2}>

                    <Avatar variant="square" sx={{
                        width: '100%',
                        height: '100px',
                        marginTop: '20px',
                        marginBottom: '50px'
                    }}
                        src='../../img/mediaTwitter.png' />
                    <Typography mb={2} sx={{
                        fontSize: '28px',
                        fontWeight: '900',

                    }}>

                        Lights, camera … attachments!
                    </Typography>
                    <Typography variant="body1" >
                        When you send Tweets with photos or videos in them, they will show up here.
                    </Typography>

                </TabPanel>
                <TabPanel value={3} index={3}>

                    <Typography mb={2} sx={{
                        fontSize: '28px',
                        fontWeight: '900',

                    }}>

                        You don’t have any likes yet
                    </Typography>
                    <Typography variant="body1" >
                        Tap the heart on any Tweet to show it some love. When you do, it’ll show up here.
                    </Typography>
                </TabPanel>
            </TabContext>
        </>
    )
}

export default TabsProfile