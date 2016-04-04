package todo.backend.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.*;


public class VerifyEmailRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 64, max = 64)
    private String emailVerificationCode;

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VerifyEmailRequest other = (VerifyEmailRequest) obj;
        if (emailVerificationCode == null) {
            if (other.emailVerificationCode != null)
                return false;
        } else if (!emailVerificationCode.equals(other.emailVerificationCode))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emailVerificationCode == null) ? 0 : emailVerificationCode.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("VerifyEmailRequest []");
    }

}
