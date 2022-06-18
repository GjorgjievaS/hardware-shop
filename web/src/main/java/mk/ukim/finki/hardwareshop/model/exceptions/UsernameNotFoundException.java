package mk.ukim.finki.hardwareshop.model.exceptions;

public class UsernameNotFoundException  extends RuntimeException{

    public UsernameNotFoundException() {
        super("Username not found exception.");
    }
}
