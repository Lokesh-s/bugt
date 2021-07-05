import http from "../http-common";

class NetworkService {
	
  getAllUsers(){
	 return http.get("/allUsers");
  }
  
  getUserDetails() {
	 return http.get("/userDetails");
  }
	
  getAll() {
    return http.get("/api/bugs");
  }

  get(id) {
    return http.get(`/api/bugs/${id}`);
  }

  create(data) {
    return http.post("/api/bugs", data);
  }
  
  createUser(data) {
    return http.post("/users", data);
  }

  update(id, data) {
    return http.put(`/api/bugs/${id}`, data);
  }

  delete(id) {
    return http.delete(`/tutorials/${id}`);
  }

  /*deleteAll() {
    return http.delete(`/tutorials`);
  }

  findByTitle(title) {
    return http.get(`/tutorials?title=${title}`);
  }*/
}

export default new NetworkService();