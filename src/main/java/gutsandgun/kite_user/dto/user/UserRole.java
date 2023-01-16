package gutsandgun.kite_user.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

public class UserRole {

    @RequiredArgsConstructor
    @Getter
    public enum Userrole {

        ADMIN("관리자"),
        USER("사용자");


        private final String title;

        public static Userrole of(String title) {
            return Arrays.stream(values())
                    .filter(v -> v.getTitle().equals(title))
                    .findFirst().orElseThrow();
        }

        public String getCode() {
            return name();
        }
    }
}
