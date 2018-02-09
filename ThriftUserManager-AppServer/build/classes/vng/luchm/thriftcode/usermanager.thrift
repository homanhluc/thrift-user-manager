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
exception UserNotFound {
  1: string message
}
exception RegisterFailed {
  1: string message
}
exception LoginFailed {
  1: string message
}
service UserManagerService {
    void setScore(1:Operation op),
    void userRegister(1:User userInfo) throws (1: RegisterFailed rf),
    void userlogin(1:string userName, 2:string passWord) throws (1: LoginFailed lf),
    list<User> getAllUsers(),
    User getUserById(1:string id) throws (1: UserNotFound unf)
}



