public class Admin {//Admin pojo class
    private String userid;//variable name for userId
    private String password;//variable name for password


    public Admin(String id,String pass){//constructor to initialize the id and pass
        this.userid=id;//this will point itself for id
        this.password=pass;//this will point itself for pass
    }


    public String getPassword() {//getter for get the password
        return password;//return the password
    }
    public String getUserid() {//getter for get the id
        return userid;//return the id
    }
}