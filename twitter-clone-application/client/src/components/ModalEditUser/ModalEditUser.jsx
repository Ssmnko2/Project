import React, { useState, useRef, useEffect } from "react";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  Button,
  IconButton,
  Toolbar,
  Container,
  Box,
  Typography
} from "@mui/material";
import TextareaAutosize from "@mui/base/TextareaAutosize";
import { Avatar } from "@mui/material";
import { styled } from "@mui/material/styles";
import CloseIcon from "@mui/icons-material/Close";
import TextField from "@mui/material/TextField";
import PhotoCameraOutlinedIcon from "@mui/icons-material/PhotoCameraOutlined";
import ContainerBirthday from "./ContainerBirthday";
import { useTheme } from "@mui/material/styles";
import { useFetch } from "../../hooks/UseFetch";
import { useParams } from "react-router-dom";
import { api } from "../../redux/service/api";

export default function ModalEditUser({ open, onClose, withId, initialBirthdate  }) {
  const { id } = useParams();
  const theme = useTheme();

  const ModalEditUserStyles = {
    backgroundColor: theme.palette.background.default,
  };

  const StyledAvatar = styled(Avatar)(() => ({
    position: "relative",
    "&:before": {
      content: '""',
      position: "absolute",
      top: 0,
      left: 0,
      right: 0,
      bottom: 0,
      backgroundColor: "rgba(0, 0, 0, 0.3)",
      opacity: 0,
      transition: "opacity 0.3s ease",
    },
  }));

  const styles = {
    textarea: {
      width: "100%",
      height: "100px",
      marginBottom: "10px",
      border: "1px solid gray",
      borderRadius: "4px",
      color: theme.palette.text.primary,
      backgroundColor: theme.palette.background.default,
      fontSize: "16px",
      padding: "12px",
      fontFamily: "Roboto, sans-serif",
    },
  };

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

  if (!loading) <p>loading...</p>;

  const { firstName, head_imagerUrl, lastName, location, birthdate: bd, av_imagerUrl, bio } = data;

  
  const [fileAv, setFileAv] = useState(null);
  const [fileHead, setFileHead] = useState(null);
  const [bioText, setBioText] = useState(bio || "");
  const [locationText, setLocation] = useState(location || "");
  const fileAvRef = useRef(null);
  const fileHeadRef = useRef(null);
  const [filePath, setFilePath] = useState(head_imagerUrl);
  const [filePathAv, setFilePathAv] = useState(av_imagerUrl);
  const [birthdate, setBirthdate] = useState(bd);
  const [value, setValue] = useState(`${firstName || ""} ${lastName || ""}`);

  useEffect(() => {
    setValue(`${firstName || ""} ${lastName || ""}`);
  }, [firstName, lastName]);

  useEffect(() => {
    if (fileAv) setFilePathAv(URL.createObjectURL(fileAv));
  }, [fileAv]);

  const handleFileAvChange = (e) => {
    const fileAv = e.target.files[0];
    setFileAv(fileAv);
  };

  useEffect(() => {
    if (fileHead) setFilePath(URL.createObjectURL(fileHead));
  }, [fileHead]);

  const handleFileHeadChange = (e) => {
    const fileHead = e.target.files[0];
    setFileHead(fileHead);
  };

  const handleSave = () => {

    const formData = new FormData();
    // formData.append("firstName", firstName);
    // formData.append("lastName", lastName);
    // formData.append("bio", bioText);
    // formData.append("user_id", id);
    // formData.append("head_imagerUrl", fileHead);
    // formData.append("av_imagerUrl", fileAv);
    // formData.append("location", locationText);
    // formData.append("birthdate", birthdate);

    
  console.log("firstName:", firstName);
  formData.append("firstName", firstName);

  console.log("lastName:", lastName);
  formData.append("lastName", lastName);

  console.log("bioText:", bioText);
  formData.append("bio", bioText);

  // console.log("user_id:", id);
  // formData.append("user_id", id);

  console.log("head_imagerUrl:", fileHead);
  formData.append("head_imagerUrl", fileHead);

  console.log("av_imagerUrl:", fileAv);
  formData.append("av_imagerUrl", fileAv);

  console.log("locationText:", locationText);
  formData.append("location", locationText);

  // console.log("birthdate:", initialBirthdate);
  formData.append("birthdate", birthdate);

    api
      .post("user/update", formData)
      .then((response) => {
        console.log(response);
        alert("Success!");
      })
      .catch((error) => {
        console.error(error);
        alert("Error!: " + error.message);
        if (error.response) {
          console.log("Server Response:", error.response.data);
        }
      });
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <Toolbar style={ModalEditUserStyles} sx={{ display: "flex", justifyContent: "space-between" }}>
        <Toolbar>
          <IconButton edge="start">
            <CloseIcon onClick={onClose} color="gray" />
          </IconButton>
          <DialogTitle>Edit profile</DialogTitle>
        </Toolbar>
        <Button
          onClick={handleSave}
          sx={{
            color: theme.palette.text.primary,
            border: `1px solid ${theme.palette.text.primary}`,
            height: "30px",
            borderRadius: "20px",
            textTransform: "none",
            fontWeight: "600",
          }}
        >
          Save
        </Button>
      </Toolbar>
      <DialogContent sx={{ maxWidth: "md" }} style={ModalEditUserStyles}>
        <Container >
          <div style={{ position: "relative" }}>
            <Box
              sx={{
                backgroundImage: filePath ? `url(${filePath})` : "none",
                backgroundSize: "cover",
                backgroundPosition: "center",
                width: "100%",
                height: "150px",
                bgcolor: !filePath && "grey.300",
              }}
            ></Box>
            <input
              ref={fileHeadRef}
              id="file-input-head"
              onChange={handleFileHeadChange}
              accept="image/png, image/gif, image/jpeg"
              type="file"
              style={{ display: "none" }}
            />
            <IconButton
              sx={{
                position: "absolute",
                top: "55px",
                left: "50%",
                transform: "translateX(-50%)",
                color: "black",
                zIndex: 99999,
              }}
              onClick={() => fileHeadRef.current.click()}
            >
              <PhotoCameraOutlinedIcon />
            </IconButton>
          </div>
          <div style={{ position: "relative" }}>
            <StyledAvatar
              alt="User Avatar"
              src={filePathAv ? filePathAv : av_imagerUrl}
              sx={{
                width: "70px",
                height: "70px",
                borderRadius: "50%",
                marginTop: "-35px",
                marginBottom: "10px",
                marginLeft: "10px",
                cursor: "pointer",
              }}
            />
            <input
              ref={fileAvRef}
              id="file-input-head"
              onChange={handleFileAvChange}
              accept="image/png, image/gif, image/jpeg"
              type="file"
              style={{ display: "none" }}
            />
            <IconButton
              sx={{
                position: "absolute",
                top: "50%",
                left: "45px",
                transform: "translate(-50%, -50%)",
                color: "black",
              }}
              onClick={() => fileAvRef.current.click()}
            >
              <PhotoCameraOutlinedIcon />
            </IconButton>
          </div>
        </Container>
        <TextField
          id="outlined-basic"
          label="Name"
          variant="outlined"
          value={value}
          onChange={(e) => setValue(e.target.value)}
          sx={{
            width: "100%",
            marginBottom: "10px",
            "& .MuiInputBase-input": {
              color: theme.palette.text.primary,
            },
            "& .MuiOutlinedInput-root": {
              borderColor: theme.palette.text.primary,
            },
            "& .MuiOutlinedInput-root .MuiOutlinedInput-notchedOutline": {
              borderColor: theme.palette.text.primary,
            },
            "& .MuiInputLabel-root": {
              color: theme.palette.text.primary,
            },
          }}
        />
        <TextareaAutosize
          id="outlined-basic"
          placeholder="Bio"
          value={bioText}
          onChange={(e) => setBioText(e.target.value)}
          variant="outlined"
          style={styles.textarea}
          inputProps={{ style: { color: theme.palette.text.primary } }}
          sx={{
            "& .MuiOutlinedInput-root": {
              borderColor: theme.palette.text.primary,
            },
            "& .MuiOutlinedInput-root .MuiOutlinedInput-notchedOutline": {
              borderColor: theme.palette.text.primary,
            },
            "& .MuiInputLabel-root": {
              color: theme.palette.text.primary,
            },
          }}
        />
        <TextField
          id="outlined-basic"
          label="Location"
          variant="outlined"
          value={locationText}
          onChange={(e) => setLocation(e.target.value)}
          sx={{
            width: "100%",
            marginBottom: "10px",
            "& .MuiInputBase-input": {
              color: theme.palette.text.primary,
            },
            "& .MuiOutlinedInput-root": {
              borderColor: theme.palette.text.primary,
            },
            "& .MuiOutlinedInput-root .MuiOutlinedInput-notchedOutline": {
              borderColor: theme.palette.text.primary,
            },
            "& .MuiInputLabel-root": {
              color: theme.palette.text.primary,
            },
          }}
        />
        <ContainerBirthday value={birthdate} setValue={setBirthdate} />
        <Typography>{birthdate}</Typography>
      </DialogContent>
    </Dialog>
  );
}


// import React, { useState, useRef, useEffect } from "react";
// import {
//   Dialog,
//   DialogTitle,
//   DialogContent,
//   Button,
//   IconButton,
//   Toolbar,
//   Container,
//   Box
// } from "@mui/material";
// import TextareaAutosize from "@mui/base/TextareaAutosize";
// import { Avatar } from "@mui/material";
// import { styled } from "@mui/material/styles";
// import CloseIcon from "@mui/icons-material/Close";
// import TextField from "@mui/material/TextField";
// import PhotoCameraOutlinedIcon from "@mui/icons-material/PhotoCameraOutlined";
// import ContainerBirthday from "./ContainerBirthday";
// import { useTheme } from "@mui/material/styles";
// import { useFetch } from "../../hooks/UseFetch";
// import { useParams } from "react-router-dom";
// import { api } from "../../redux/service/api";

// export default function ModalEditUser({ open, onClose, withId, initialBirthdate  }) {
//   const { id } = useParams();
//   const theme = useTheme();

//   const ModalEditUserStyles = {
//     backgroundColor: theme.palette.background.default,
//   };

//   const StyledAvatar = styled(Avatar)(() => ({
//     position: "relative",
//     "&:before": {
//       content: '""',
//       position: "absolute",
//       top: 0,
//       left: 0,
//       right: 0,
//       bottom: 0,
//       backgroundColor: "rgba(0, 0, 0, 0.3)",
//       opacity: 0,
//       transition: "opacity 0.3s ease",
//     },
//   }));

//   const styles = {
//     textarea: {
//       width: "100%",
//       height: "100px",
//       marginBottom: "10px",
//       border: "1px solid gray",
//       borderRadius: "4px",
//       color: theme.palette.text.primary,
//       backgroundColor: theme.palette.background.default,
//       fontSize: "16px",
//       padding: "12px",
//       fontFamily: "Roboto, sans-serif",
//     },
//   };

//   const [{ data, loading }, getData] = useFetch({
//     initData: {},
//     url: withId
//       ? `user/getuser/${id}`
//       : 'user/profile',
//     method: 'GET',
//     dataTransformer: (data) => {
//       console.log(data)
//       return data;
//     },
//   });

//   useEffect(() => {
//     getData()
//   }, [id])

//   if (!loading) <p>loading...</p>;

//   const { firstName, head_imagerUrl, lastName, location, birthdate, av_imagerUrl, bio } = data;

//   const [firstNameText, setFirstName] = useState(firstName)
//   const [fileAv, setFileAv] = useState(null);
//   const [fileHead, setFileHead] = useState(null);
//   const [bioText, setBioText] = useState(bio || "");
//   const [locationText, setLocation] = useState(location || "");
//   const fileAvRef = useRef(null);
//   const fileHeadRef = useRef(null);
//   const [filePath, setFilePath] = useState(head_imagerUrl);
//   const [filePathAv, setFilePathAv] = useState(av_imagerUrl);
//   const [value, setValue] = useState(`${firstName || ""} ${lastName || ""}`);

//   useEffect(() => {
//     setValue(`${firstName || ""} ${lastName || ""}`);
//   }, [firstName, lastName]);

//   useEffect(() => {
//     if (fileAv) setFilePathAv(URL.createObjectURL(fileAv));
//   }, [fileAv]);

//   const handleFileAvChange = (e) => {
//     const fileAv = e.target.files[0];
//     setFileAv(fileAv);
//   };

//   useEffect(() => {
//     if (fileHead) setFilePath(URL.createObjectURL(fileHead));
//   }, [fileHead]);

//   const handleFileHeadChange = (e) => {
//     const fileHead = e.target.files[0];
//     setFileHead(fileHead);
//   };

//   const handleSave = () => {

//     const formData = new FormData();
//     // formData.append("firstName", firstName);
//     // formData.append("lastName", lastName);
//     // formData.append("bio", bioText);
//     // formData.append("user_id", id);
//     // formData.append("head_imagerUrl", fileHead);
//     // formData.append("av_imagerUrl", fileAv);
//     // formData.append("location", locationText);
//     // formData.append("birthdate", birthdate);

    
//   console.log("firstName:", firstName);
//   formData.append("firstName", firstName);

//   // console.log("lastName:", lastName);
//   // formData.append("lastName", lastName);

//   console.log("bioText:", bioText);
//   formData.append("bio", bioText);

//   // console.log("user_id:", id);
//   // formData.append("user_id", id);

//   console.log("head_imagerUrl:", fileHead);
//   formData.append("head_imagerUrl", fileHead);

//   console.log("av_imagerUrl:", fileAv);
//   formData.append("av_imagerUrl", fileAv);

//   console.log("locationText:", locationText);
//   formData.append("location", locationText);

//   // console.log("birthdate:", initialBirthdate);
//   formData.append("birthdate", '12.01.2013');

//     api
//       .post("user/update", formData)
//       .then((response) => {
//         console.log(response);
//         alert("Success!");
//       })
//       .catch((error) => {
//         console.error(error);
//         alert("Error!: " + error.message);
//         if (error.response) {
//           console.log("Server Response:", error.response.data);
//         }
//       });
//   };

//   return (
//     <Dialog open={open} onClose={onClose}>
//       <Toolbar style={ModalEditUserStyles} sx={{ display: "flex", justifyContent: "space-between" }}>
//         <Toolbar>
//           <IconButton edge="start">
//             <CloseIcon onClick={onClose} color="gray" />
//           </IconButton>
//           <DialogTitle>Edit profile</DialogTitle>
//         </Toolbar>
//         <Button
//           onClick={handleSave}
//           sx={{
//             color: theme.palette.text.primary,
//             border: `1px solid ${theme.palette.text.primary}`,
//             height: "30px",
//             borderRadius: "20px",
//             textTransform: "none",
//             fontWeight: "600",
//           }}
//         >
//           Save
//         </Button>
//       </Toolbar>
//       <DialogContent sx={{ maxWidth: "md" }} style={ModalEditUserStyles}>
//         <Container >
//           <div style={{ position: "relative" }}>
//             <Box
//               sx={{
//                 backgroundImage: filePath ? `url(${filePath})` : "none",
//                 backgroundSize: "cover",
//                 backgroundPosition: "center",
//                 width: "100%",
//                 height: "150px",
//                 bgcolor: !filePath && "grey.300",
//               }}
//             ></Box>
//             <input
//               ref={fileHeadRef}
//               id="file-input-head"
//               onChange={handleFileHeadChange}
//               accept="image/png, image/gif, image/jpeg"
//               type="file"
//               style={{ display: "none" }}
//             />
//             <IconButton
//               sx={{
//                 position: "absolute",
//                 top: "55px",
//                 left: "50%",
//                 transform: "translateX(-50%)",
//                 color: "black",
//                 zIndex: 99999,
//               }}
//               onClick={() => fileHeadRef.current.click()}
//             >
//               <PhotoCameraOutlinedIcon />
//             </IconButton>
//           </div>
//           <div style={{ position: "relative" }}>
//             <StyledAvatar
//               alt="User Avatar"
//               src={filePathAv ? filePathAv : av_imagerUrl}
//               sx={{
//                 width: "70px",
//                 height: "70px",
//                 borderRadius: "50%",
//                 marginTop: "-35px",
//                 marginBottom: "10px",
//                 marginLeft: "10px",
//                 cursor: "pointer",
//               }}
//             />
//             <input
//               ref={fileAvRef}
//               id="file-input-head"
//               onChange={handleFileAvChange}
//               accept="image/png, image/gif, image/jpeg"
//               type="file"
//               style={{ display: "none" }}
//             />
//             <IconButton
//               sx={{
//                 position: "absolute",
//                 top: "50%",
//                 left: "45px",
//                 transform: "translate(-50%, -50%)",
//                 color: "black",
//               }}
//               onClick={() => fileAvRef.current.click()}
//             >
//               <PhotoCameraOutlinedIcon />
//             </IconButton>
//           </div>
//         </Container>
//         <TextField
//           id="outlined-basic"
//           label="Name"
//           variant="outlined"
//           value={value}
//           onChange={(e) => setValue(e.target.value)}
//           sx={{
//             width: "100%",
//             marginBottom: "10px",
//             "& .MuiInputBase-input": {
//               color: theme.palette.text.primary,
//             },
//             "& .MuiOutlinedInput-root": {
//               borderColor: theme.palette.text.primary,
//             },
//             "& .MuiOutlinedInput-root .MuiOutlinedInput-notchedOutline": {
//               borderColor: theme.palette.text.primary,
//             },
//             "& .MuiInputLabel-root": {
//               color: theme.palette.text.primary,
//             },
//           }}
//         />
//         <TextareaAutosize
//           id="outlined-basic"
//           placeholder="Bio"
//           value={bioText}
//           onChange={(e) => setBioText(e.target.value)}
//           variant="outlined"
//           style={styles.textarea}
//           inputProps={{ style: { color: theme.palette.text.primary } }}
//           sx={{
//             "& .MuiOutlinedInput-root": {
//               borderColor: theme.palette.text.primary,
//             },
//             "& .MuiOutlinedInput-root .MuiOutlinedInput-notchedOutline": {
//               borderColor: theme.palette.text.primary,
//             },
//             "& .MuiInputLabel-root": {
//               color: theme.palette.text.primary,
//             },
//           }}
//         />
//         <TextField
//           id="outlined-basic"
//           label="Location"
//           variant="outlined"
//           value={locationText}
//           onChange={(e) => setLocation(e.target.value)}
//           sx={{
//             width: "100%",
//             marginBottom: "10px",
//             "& .MuiInputBase-input": {
//               color: theme.palette.text.primary,
//             },
//             "& .MuiOutlinedInput-root": {
//               borderColor: theme.palette.text.primary,
//             },
//             "& .MuiOutlinedInput-root .MuiOutlinedInput-notchedOutline": {
//               borderColor: theme.palette.text.primary,
//             },
//             "& .MuiInputLabel-root": {
//               color: theme.palette.text.primary,
//             },
//           }}
//         />
//         <ContainerBirthday value={birthdate} />
//       </DialogContent>
//     </Dialog>
//   );
// }
