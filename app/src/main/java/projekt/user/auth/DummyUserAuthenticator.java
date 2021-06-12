package projekt.user.auth;

public class DummyUserAuthenticator implements UserAuthenticator {
    @Override
    public void authenticate(Credentials credentials) {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        // ...
    }
}
