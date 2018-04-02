namespace java vng.luchm.thrift
enum Operation {
  INCREASE = 1,
  DECREASE = 2
}
struct User {
    1: string Id,
    2: string UserName,
    3: string PassWord,
    4: i32 Score,
    5: string CreatedDate,
    6: string UpdatedDate
}
service UserManagerService {
    void setScore(1:Operation op, 2:string id),
    bool userRegister(1:User userInfo),
    bool userlogin(1:string userName, 2:string passWord),
    list<User> getAllUsers(),
    User getUserById(1:string id)
}



