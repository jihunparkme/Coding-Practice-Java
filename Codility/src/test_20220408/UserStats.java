package test_20220408;

import java.util.Optional;

public class UserStats {
    Optional<Long> visitCount;

    public Optional<Long> getVisitCount() {
        return visitCount;
    }

    public UserStats(Optional<Long> visitCount) {
        this.visitCount = visitCount;
    }
}
