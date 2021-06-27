import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:7700",
  headers: {
    "Content-type": "application/json"
  }
});