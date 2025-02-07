import {
    type ChangeEvent,
    useState,
    type KeyboardEvent,
    useEffect,
  } from "react";
  import { Helmet } from "react-helmet";
  import axios from "axios";
  import httpConfig from "../../config/httpConfig";
  
  import { GridItem } from "../../components/GridSystem";
  import Input from "../../components/Input";
  import { Link, useLocation, useNavigate } from "react-router-dom";


  
  export default function COUSR00() {
    type formInput = {
      usridin: string;
      sel0001: string;
      sel0002: string;
      sel0003: string;
      sel0004: string;
      sel0005: string;
      sel0006: string;
      sel0007: string;
      sel0008: string;
      sel0009: string;
      sel0010: string;
    };
  
    type formOutput = {
      cousr00: string;
      cousr0a: string;
      trnname: string;
      title01: string;
      curdate: string;
      pgmname: string;
      title02: string;
      curtime: string;
      pagenum: string;
      usrid01: string;
      fname01: string;
      lname01: string;
      utype01: string;
      usrid02: string;
      fname02: string;
      lname02: string;
      utype02: string;
      usrid03: string;
      fname03: string;
      lname03: string;
      utype03: string;
      usrid04: string;
      fname04: string;
      lname04: string;
      utype04: string;
      usrid05: string;
      fname05: string;
      lname05: string;
      utype05: string;
      usrid06: string;
      fname06: string;
      lname06: string;
      utype06: string;
      usrid07: string;
      fname07: string;
      lname07: string;
      utype07: string;
      usrid08: string;
      fname08: string;
      lname08: string;
      utype08: string;
      usrid09: string;
      fname09: string;
      lname09: string;
      utype09: string;
      usrid10: string;
      fname10: string;
      lname10: string;
      utype10: string;
      errmsg: string;
    };
  
    const [formData, setFormData] = useState<formInput>({
      usridin: "",
      sel0001: "",
      sel0002: "",
      sel0003: "",
      sel0004: "",
      sel0005: "",
      sel0006: "",
      sel0007: "",
      sel0008: "",
      sel0009: "",
      sel0010: "",
    });
    const [receivedData, setReceivedData] = useState<formOutput>({
      cousr00: "",
      cousr0a: "",
      trnname: "",
      title01: "",
      curdate: "mm/dd/yy",
      pgmname: "",
      title02: "",
      curtime: "hh:mm:ss",
      pagenum: " ",
      usrid01: " ",
      fname01: " ",
      lname01: " ",
      utype01: " ",
      usrid02: " ",
      fname02: " ",
      lname02: " ",
      utype02: " ",
      usrid03: " ",
      fname03: " ",
      lname03: " ",
      utype03: " ",
      usrid04: " ",
      fname04: " ",
      lname04: " ",
      utype04: " ",
      usrid05: " ",
      fname05: " ",
      lname05: " ",
      utype05: " ",
      usrid06: " ",
      fname06: " ",
      lname06: " ",
      utype06: " ",
      usrid07: " ",
      fname07: " ",
      lname07: " ",
      utype07: " ",
      usrid08: " ",
      fname08: " ",
      lname08: " ",
      utype08: " ",
      usrid09: " ",
      fname09: " ",
      lname09: " ",
      utype09: " ",
      usrid10: " ",
      fname10: " ",
      lname10: " ",
      utype10: " ",
      errmsg: "",
    });
  
    // const [userData, setUserData] = useState<User[]>([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [value, setValue] = useState("");
  
    const handleInputChange = async (e: ChangeEvent<HTMLInputElement>) => {
      if (
        e.target.value.toUpperCase() !== 'U' &&
        e.target.value.toUpperCase() !== "D"
      ) {
        e.target.value = ""
      }
      setFormData((state) => {
        return {
          ...state,
          [e.target.name]: e.target.value,
        };
      });
  
      //find user by id
      // const { value } = e.target;
      // setFormData((prevData) => ({
      //   ...prevData,
      //   usridin: value,
      // }));
  
      // if (value) {
      //   const apiURL1 = `${httpConfig.domain}/sec-user-data/get-by-id`;
      //   // Fetch data based on the entered ID
      //   // const response = await fetch(`/api/search/${value}`);
      //   const response = await axios.post(apiURL1, {
      //     secUsrId: `${value}`,
      //   });
  
      //   // Extract data from the response
      //   const data = response.data.data;
      //   // console.log(data)
      //   const secUsrFname = data.secUsrFname.trim();
      //   // console.log(secUsrFname) // Trim to remove extra spaces
      //   const secUsrId = data.secUsrId;
      //   const message = response.data.message;
      //   // Update the state with the extracted data
      //   setReceivedData((prevState) => ({
      //     ...prevState,
      //     usrid01: secUsrId,
      //     fname01: secUsrFname,
      //     lname01: data.secUsrLname.trim(), // Assuming you also want to update the last name
      //     utype01: data.secUsrType,
      //     usrid02: " ",
      //     fname02: " ",
      //     lname02: " ",
      //     utype02: " ",
      //     usrid03: " ",
      //     fname03: " ",
      //     lname03: " ",
      //     utype03: " ",
      //     usrid04: " ",
      //     fname04: " ",
      //     lname04: " ",
      //     utype04: " ",
      //     usrid05: " ",
      //     fname05: " ",
      //     lname05: " ",
      //     utype05: " ",
      //     usrid06: " ",
      //     fname06: " ",
      //     lname06: " ",
      //     utype06: " ",
      //     usrid07: " ",
      //     fname07: " ",
      //     lname07: " ",
      //     utype07: " ",
      //     usrid08: " ",
      //     fname08: " ",
      //     lname08: " ",
      //     utype08: " ",
      //     usrid09: " ",
      //     fname09: " ",
      //     lname09: " ",
      //     utype09: " ",
      //     usrid10: " ",
      //     fname10: " ",
      //     lname10: " ",
      //     utype10: " ",
      //     errmsg: message,
      //   }));
      // }
  
      // handleFormData();
    };
  
    const handleFormData = () => {
      if (formData.sel0001.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0001);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid01,
          };
        });
      } else if (formData.sel0002.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0002);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid02,
          };
        });
      } else if (formData.sel0003.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0003);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid03,
          };
        });
      } else if (formData.sel0004.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0004);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid04,
          };
        });
      } else if (formData.sel0005.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0005);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid05,
          };
        });
      } else if (formData.sel0006.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0006);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid06,
          };
        });
      } else if (formData.sel0007.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0007);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid07,
          };
        });
      } else if (formData.sel0008.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0008);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid08,
          };
        });
      } else if (formData.sel0009.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0009);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid09,
          };
        });
      } else if (formData.sel0010.trim().length > 0) {
        // console.log(formData)
        //console.log(receivedData.usrid01)
        setValue(formData.sel0010);
        setFormData((prev) => {
          return {
            ...prev,
            usridin: receivedData.usrid10,
          };
        });
      } else {
        setFormData((prev) => {
          return {
            ...prev,
            usridin: "",
          };
        });
      }
    };
  
    // const dispatch = useDispatch()
    const navigate = useNavigate();
  
    const handleSubmit = async (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key === "Enter") {
        // testcode(formData.usridin);
        // const { value } = (event.target as HTMLInputElement);
        // console.log(formData)
  
        //Ai generate
        // const { sel0001, sel0002, sel0003, sel0004, sel0005, sel0006, sel0007, sel0008, sel0009, sel0010 } = formData;
        // console.log(usridin);
        console.log(value);
  
        // if (!value && !formData.usridin) {
          if (value === "u" || value === "U") {
            navigate("/COUSR02", { state: { usridin: formData.usridin } });
          } else if (value === "d" || value === "D") {
            navigate("/COUSR03", { state: { usridin: formData.usridin } });
          } else {
            receivedData.errmsg = "Can't commit other";
          }
        // } else {
        //   receivedData.errmsg = "Invalid key in there";
        // }
        handleFormData();
  
        // dispatch(setData(formData.usridin))
  
        // navigate("/COUSR02", { state: { usridin: formData.usridin } });
  
        // const response = await axios.post(
        //   // httpConfig.domain + "/Cousr00",
        //   httpConfig.domain + "/sec-user-data/get-by-id",
        //   {
        //     "secUsrId": formData.usridin
        //   }
        // );
  
        // setReceivedData((_state) => response.data);
      }
  
      if (event.key === "F7") {
        event.preventDefault();
        event.stopPropagation();
        // alert("thai");
        // increase value by +1
        // setCurrentPage(currentPage + 1);
  
        // handlePage(currentPage);
        if (currentPage > 1) {
          handlePage(currentPage - 1);
          // receivedData.errmsg="";
        }
        setReceivedData((pre) => ({
          ...pre,
          errmsg: "",
        }));
      }
  
      if (event.key === "F8") {
        event.preventDefault();
        event.stopPropagation();
        // alert("thai");
        // if(currentPage<2 ){
        //   handlePage(1);
        // }else{
        //   handlePage(currentPage + 1);
        // }
        handlePage(currentPage + 1);
        console.log(currentPage);
        receivedData.errmsg = "";
      }
    };
    console.log(formData.usridin);
    useEffect(() => {
      console.log("Updated formData:", formData);
    }, [formData]);
  
    useEffect(() => {
      handleFormData();
    }, [
      formData.sel0001,
      formData.sel0002,
      formData.sel0003,
      formData.sel0004,
      formData.sel0005,
      formData.sel0006,
      formData.sel0007,
      formData.sel0008,
      formData.sel0009,
      formData.sel0010,
    ]);
  
    // const transformData = (apiData) => {
    //   const transformed = {
    //     cousr00: "",
    //     cousr0a: "",
    //     trnname: "",
    //     title01: "",
    //     curdate: "mm/dd/yy",
    //     pgmname: "",
    //     title02: "",
    //     curtime: "hh:mm:ss",
    //     pagenum: currentPage.toString(),
    //     usrid01: apiData.data[0]?.secUsrId || " ",
    //     fname01: apiData.data[0]?.secUsrFname.trim() || " ",
    //     lname01: apiData.data[0]?.secUsrLname.trim() || " ",
    //     utype01: apiData.data[0]?.secUsrType || " ",
    //     usrid02: apiData.data[1]?.secUsrId || " ",
    //     fname02: apiData.data[1]?.secUsrFname.trim() || " ",
    //     lname02: apiData.data[1]?.secUsrLname.trim() || " ",
    //     utype02: apiData.data[1]?.secUsrType || " ",
    //     usrid03: apiData.data[2]?.secUsrId || " ",
    //     fname03: apiData.data[2]?.secUsrFname.trim() || " ",
    //     lname03: apiData.data[2]?.secUsrLname.trim() || " ",
    //     utype03: apiData.data[2]?.secUsrType || " ",
    //     usrid04: apiData.data[3]?.secUsrId || " ",
    //     fname04: apiData.data[3]?.secUsrFname.trim() || " ",
    //     lname04: apiData.data[3]?.secUsrLname.trim() || " ",
    //     utype04: apiData.data[3]?.secUsrType || " ",
    //     usrid05: apiData.data[4]?.secUsrId || " ",
    //     fname05: apiData.data[4]?.secUsrFname.trim() || " ",
    //     lname05: apiData.data[4]?.secUsrLname.trim() || " ",
    //     utype05: apiData.data[4]?.secUsrType || " ",
    //     usrid06: apiData.data[5]?.secUsrId || " ",
    //     fname06: apiData.data[5]?.secUsrFname.trim() || " ",
    //     lname06: apiData.data[5]?.secUsrLname.trim() || " ",
    //     utype06: apiData.data[5]?.secUsrType || " ",
    //     usrid07: apiData.data[6]?.secUsrId || " ",
    //     fname07: apiData.data[6]?.secUsrFname.trim() || " ",
    //     lname07: apiData.data[6]?.secUsrLname.trim() || " ",
    //     utype07: apiData.data[6]?.secUsrType || " ",
    //     usrid08: apiData.data[7]?.secUsrId || " ",
    //     fname08: apiData.data[7]?.secUsrFname.trim() || " ",
    //     lname08: apiData.data[7]?.secUsrLname.trim() || " ",
    //     utype08: apiData.data[7]?.secUsrType || " ",
    //     usrid09: apiData.data[8]?.secUsrId || " ",
    //     fname09: apiData.data[8]?.secUsrFname.trim() || " ",
    //     lname09: apiData.data[8]?.secUsrLname.trim() || " ",
    //     utype09: apiData.data[8]?.secUsrType || " ",
    //     usrid10: apiData.data[9]?.secUsrId || " ",
    //     fname10: apiData.data[9]?.secUsrFname.trim() || " ",
    //     lname10: apiData.data[9]?.secUsrLname.trim() || " ",
    //     utype10: apiData.data[9]?.secUsrType || " ",
    //     errmsg: "",
    //   };
    //   return transformed;
    // };
  
    const fetchData = async (page) => {
      // Xóa dữ liệu cũ trong localStorage
      // localStorage.removeItem('formData');
  
      try {
        const response = await fetch(
          `http://localhost:8080/sec-user-data/${page}`
        );
        const apiData = await response.json();
        // const transformedData = transformData(apiData);
  
        setReceivedData((prevState) => ({
          ...prevState,
          cousr00: "",
          cousr0a: "",
          trnname: "",
          title01: "",
          curdate: "mm/dd/yy",
          pgmname: "",
          title02: "",
          curtime: "hh:mm:ss",
          pagenum: currentPage.toString(),
          usrid01: apiData.data[0]?.secUsrId || " ",
          fname01: apiData.data[0]?.secUsrFname.trim() || " ",
          lname01: apiData.data[0]?.secUsrLname.trim() || " ",
          utype01: apiData.data[0]?.secUsrType || " ",
          usrid02: apiData.data[1]?.secUsrId || " ",
          fname02: apiData.data[1]?.secUsrFname.trim() || " ",
          lname02: apiData.data[1]?.secUsrLname.trim() || " ",
          utype02: apiData.data[1]?.secUsrType || " ",
          usrid03: apiData.data[2]?.secUsrId || " ",
          fname03: apiData.data[2]?.secUsrFname.trim() || " ",
          lname03: apiData.data[2]?.secUsrLname.trim() || " ",
          utype03: apiData.data[2]?.secUsrType || " ",
          usrid04: apiData.data[3]?.secUsrId || " ",
          fname04: apiData.data[3]?.secUsrFname.trim() || " ",
          lname04: apiData.data[3]?.secUsrLname.trim() || " ",
          utype04: apiData.data[3]?.secUsrType || " ",
          usrid05: apiData.data[4]?.secUsrId || " ",
          fname05: apiData.data[4]?.secUsrFname.trim() || " ",
          lname05: apiData.data[4]?.secUsrLname.trim() || " ",
          utype05: apiData.data[4]?.secUsrType || " ",
          usrid06: apiData.data[5]?.secUsrId || " ",
          fname06: apiData.data[5]?.secUsrFname.trim() || " ",
          lname06: apiData.data[5]?.secUsrLname.trim() || " ",
          utype06: apiData.data[5]?.secUsrType || " ",
          usrid07: apiData.data[6]?.secUsrId || " ",
          fname07: apiData.data[6]?.secUsrFname.trim() || " ",
          lname07: apiData.data[6]?.secUsrLname.trim() || " ",
          utype07: apiData.data[6]?.secUsrType || " ",
          usrid08: apiData.data[7]?.secUsrId || " ",
          fname08: apiData.data[7]?.secUsrFname.trim() || " ",
          lname08: apiData.data[7]?.secUsrLname.trim() || " ",
          utype08: apiData.data[7]?.secUsrType || " ",
          usrid09: apiData.data[8]?.secUsrId || " ",
          fname09: apiData.data[8]?.secUsrFname.trim() || " ",
          lname09: apiData.data[8]?.secUsrLname.trim() || " ",
          utype09: apiData.data[8]?.secUsrType || " ",
          usrid10: apiData.data[9]?.secUsrId || " ",
          fname10: apiData.data[9]?.secUsrFname.trim() || " ",
          lname10: apiData.data[9]?.secUsrLname.trim() || " ",
          utype10: apiData.data[9]?.secUsrType || " ",
          // errmsg: "",
        }));
  
        if (apiData.data.toString().trim() === "") {
          setCurrentPage(currentPage - 1);
          // console.log(currentPage);
          setReceivedData((prevState) => ({
            ...prevState,
            errmsg: apiData.message,
          }));
          // localStorage.setItem("formData", JSON.stringify(transformedData));
          // setReceivedData(transformedData);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
      // console.log(receivedData.errmsg);
    };
  
    // const testcode = async (usid) => {
    //   const apiURL1 = `${httpConfig.domain}/sec-user-data/get-by-id`;
    //   console.log("Request URL:", apiURL1); // Log the URL to check its validity
  
    //   try {
    //     const response = await axios.post(apiURL1, {
    //       secUsrId: `${usid}`,
    //     });
  
    //     // Extract data from the response
    //     const data = response.data.data;
    //     const secUsrFname = data.secUsrFname.trim(); // Trim to remove extra spaces
    //     const secUsrId = data.secUsrId;
    //     const message = response.data.message;
    //     // Update the state with the extracted data
    //     setReceivedData((prevState) => ({
    //       ...prevState,
    //       usrid01: secUsrId,
    //       fname01: secUsrFname,
    //       lname01: data.secUsrLname.trim(), // Assuming you also want to update the last name
    //       utype01: data.secUsrType,
    //       usrid02: " ",
    //       fname02: " ",
    //       lname02: " ",
    //       utype02: " ",
    //       usrid03: " ",
    //       fname03: " ",
    //       lname03: " ",
    //       utype03: " ",
    //       usrid04: " ",
    //       fname04: " ",
    //       lname04: " ",
    //       utype04: " ",
    //       usrid05: " ",
    //       fname05: " ",
    //       lname05: " ",
    //       utype05: " ",
    //       usrid06: " ",
    //       fname06: " ",
    //       lname06: " ",
    //       utype06: " ",
    //       usrid07: " ",
    //       fname07: " ",
    //       lname07: " ",
    //       utype07: " ",
    //       usrid08: " ",
    //       fname08: " ",
    //       lname08: " ",
    //       utype08: " ",
    //       usrid09: " ",
    //       fname09: " ",
    //       lname09: " ",
    //       utype09: " ",
    //       usrid10: " ",
    //       fname10: " ",
    //       lname10: " ",
    //       utype10: " ",
    //       errmsg: message
    //     }));
  
    //     console.log("Message:", response.data.message);
    //     console.log(
    //       "Updated receivedData:",
    //       JSON.stringify(receivedData, null, 2)
    //     );
    //   } catch (error) {
    //     console.error("Error making request:", error);
    //   }
    // };
  
    useEffect(() => {
      fetchData(currentPage);
    }, [currentPage]);
  
    const handlePage = (newPage) => {
      setCurrentPage(newPage);
    };
  
    return (
      <>
        <Helmet>
          <title>COUSR00</title>
        </Helmet>
  
        <GridItem col={1} row={1}>
          <pre style={{ color: "#7faded" }}>Tran:</pre>
        </GridItem>
  
        <GridItem col={7} row={1}>
          <pre style={{ color: "#7faded" }}>{receivedData.trnname}</pre>
        </GridItem>
  
        <GridItem col={21} row={1}>
          <pre style={{ color: "yellow" }}>{receivedData.title01}</pre>
        </GridItem>
  
        <GridItem col={65} row={1}>
          <pre style={{ color: "#7faded" }}>Date:</pre>
        </GridItem>
  
        <GridItem col={71} row={1}>
          <pre style={{ color: "#7faded" }}>{receivedData.curdate}</pre>
        </GridItem>
  
        <GridItem col={1} row={2}>
          <pre style={{ color: "#7faded" }}>Prog:</pre>
        </GridItem>
  
        <GridItem col={7} row={2}>
          <pre style={{ color: "#7faded" }}>{receivedData.pgmname}</pre>
        </GridItem>
  
        <GridItem col={21} row={2}>
          <pre style={{ color: "yellow" }}>{receivedData.title02}</pre>
        </GridItem>
  
        <GridItem col={65} row={2}>
          <pre style={{ color: "#7faded" }}>Time:</pre>
        </GridItem>
  
        <GridItem col={71} row={2}>
          <pre style={{ color: "#7faded" }}>{receivedData.curtime}</pre>
        </GridItem>
  
        <GridItem col={35} row={4}>
          <pre style={{ color: "neutral" }}>List Users</pre>
        </GridItem>
  
        <GridItem col={65} row={4}>
          <pre style={{ color: "turquoise" }}>Page:</pre>
        </GridItem>
  
        <GridItem col={71} row={4}>
          <pre style={{ color: "#7faded" }}>{receivedData.pagenum}</pre>
        </GridItem>
  
        <GridItem col={5} row={6}>
          <pre style={{ color: "turquoise" }}>Search User ID:</pre>
        </GridItem>
  
        <GridItem col={21} row={6}>
          <input
            maxLength={8}
            className="bms underLine"
            name="usridin"
            id="usridin"
            type="text"
            style={{ color: "green", width:"80px" }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
            value={formData.usridin}
          />
        </GridItem>
  
        <GridItem col={30} row={6}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={5} row={8}>
          <pre style={{ color: "neutral" }}>Sel</pre>
        </GridItem>
  
        <GridItem col={12} row={8}>
          <pre style={{ color: "neutral" }}>User ID</pre>
        </GridItem>
  
        <GridItem col={24} row={8}>
          <pre style={{ color: "neutral" }}>First Name</pre>
        </GridItem>
  
        <GridItem col={48} row={8}>
          <pre style={{ color: "neutral" }}>Last Name</pre>
        </GridItem>
  
        <GridItem col={72} row={8}>
          <pre style={{ color: "neutral" }}>Type</pre>
        </GridItem>
  
        <GridItem col={5} row={9}>
          <pre style={{ color: "neutral" }}>---</pre>
        </GridItem>
  
        <GridItem col={12} row={9}>
          <pre style={{ color: "neutral" }}>--------</pre>
        </GridItem>
  
        <GridItem col={24} row={9}>
          <pre style={{ color: "neutral" }}>--------------------</pre>
        </GridItem>
  
        <GridItem col={48} row={9}>
          <pre style={{ color: "neutral" }}>--------------------</pre>
        </GridItem>
  
        <GridItem col={72} row={9}>
          <pre style={{ color: "neutral" }}>----</pre>
        </GridItem>
  
        <GridItem col={6} row={10}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0001"
            id="sel0001"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={10}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={10}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid01}</pre>
        </GridItem>
  
        <GridItem col={24} row={10}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname01}</pre>
        </GridItem>
  
        <GridItem col={48} row={10}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname01}</pre>
        </GridItem>
  
        <GridItem col={73} row={10}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype01}</pre>
        </GridItem>
  
        <GridItem col={6} row={11}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0002"
            id="sel0002"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={11}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={11}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid02}</pre>
        </GridItem>
  
        <GridItem col={24} row={11}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname02}</pre>
        </GridItem>
  
        <GridItem col={48} row={11}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname02}</pre>
        </GridItem>
  
        <GridItem col={73} row={11}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype02}</pre>
        </GridItem>
  
        <GridItem col={6} row={12}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0003"
            id="sel0003"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={12}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={12}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid03}</pre>
        </GridItem>
  
        <GridItem col={24} row={12}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname03}</pre>
        </GridItem>
  
        <GridItem col={48} row={12}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname03}</pre>
        </GridItem>
  
        <GridItem col={73} row={12}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype03}</pre>
        </GridItem>
  
        <GridItem col={6} row={13}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0004"
            id="sel0004"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={13}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={13}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid04}</pre>
        </GridItem>
  
        <GridItem col={24} row={13}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname04}</pre>
        </GridItem>
  
        <GridItem col={48} row={13}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname04}</pre>
        </GridItem>
  
        <GridItem col={73} row={13}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype04}</pre>
        </GridItem>
  
        <GridItem col={6} row={14}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0005"
            id="sel0005"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={14}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={14}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid05}</pre>
        </GridItem>
  
        <GridItem col={24} row={14}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname05}</pre>
        </GridItem>
  
        <GridItem col={48} row={14}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname05}</pre>
        </GridItem>
  
        <GridItem col={73} row={14}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype05}</pre>
        </GridItem>
  
        <GridItem col={6} row={15}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0006"
            id="sel0006"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={15}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={15}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid06}</pre>
        </GridItem>
  
        <GridItem col={24} row={15}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname06}</pre>
        </GridItem>
  
        <GridItem col={48} row={15}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname06}</pre>
        </GridItem>
  
        <GridItem col={73} row={15}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype06}</pre>
        </GridItem>
  
        <GridItem col={6} row={16}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0007"
            id="sel0007"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={16}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={16}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid07}</pre>
        </GridItem>
  
        <GridItem col={24} row={16}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname07}</pre>
        </GridItem>
  
        <GridItem col={48} row={16}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname07}</pre>
        </GridItem>
  
        <GridItem col={73} row={16}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype07}</pre>
        </GridItem>
  
        <GridItem col={6} row={17}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0008"
            id="sel0008"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={17}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={17}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid08}</pre>
        </GridItem>
  
        <GridItem col={24} row={17}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname08}</pre>
        </GridItem>
  
        <GridItem col={48} row={17}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname08}</pre>
        </GridItem>
  
        <GridItem col={73} row={17}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype08}</pre>
        </GridItem>
  
        <GridItem col={6} row={18}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0009"
            id="sel0009"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={18}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={18}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid09}</pre>
        </GridItem>
  
        <GridItem col={24} row={18}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname09}</pre>
        </GridItem>
  
        <GridItem col={48} row={18}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname09}</pre>
        </GridItem>
  
        <GridItem col={73} row={18}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype09}</pre>
        </GridItem>
  
        <GridItem col={6} row={19}>
          <input
            maxLength={1}
            className="bms underLine"
            name="sel0010"
            id="sel0010"
            type="text"
            style={{ color: "green", width:'10px' }}
            onChange={handleInputChange}
            onKeyDown={handleSubmit}
          />
        </GridItem>
  
        <GridItem col={8} row={19}>
          <pre></pre>
        </GridItem>
  
        <GridItem col={12} row={19}>
          <pre style={{ color: "#7faded" }}>{receivedData.usrid10}</pre>
        </GridItem>
  
        <GridItem col={24} row={19}>
          <pre style={{ color: "#7faded" }}>{receivedData.fname10}</pre>
        </GridItem>
  
        <GridItem col={48} row={19}>
          <pre style={{ color: "#7faded" }}>{receivedData.lname10}</pre>
        </GridItem>
  
        <GridItem col={73} row={19}>
          <pre style={{ color: "#7faded" }}>{receivedData.utype10}</pre>
        </GridItem>
  
        <GridItem col={12} row={21}>
          <pre style={{ color: "neutral" }}>Type</pre>
        </GridItem>
  
        <GridItem col={1} row={23}>
          <pre style={{ color: "red" }}>{receivedData.errmsg}</pre>
        </GridItem>
  
        <GridItem col={1} row={24}>
          <pre style={{ color: "yellow" }}>
            ENTER=Continue F3=Back F7=Backward F8=Forward
          </pre>
        </GridItem>
      </>
    );
  }
  