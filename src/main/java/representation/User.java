package representation;

public class User {
    private String email, name, facebookId;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public User(String email, String name, String facebookId) {
        this.email = email;
        this.name = name;
        this.facebookId = facebookId;
    }


    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass())){
            return false;
        }
        User user = (User) obj;

        if (! this.email.equals(user.getEmail())) return false;
        if (! this.name.equals(user.getName())) return false;
        if (! this.facebookId.equals(user.getFacebookId())) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + email.hashCode() + name.hashCode() + facebookId.hashCode();
        return hash;
    }

}
