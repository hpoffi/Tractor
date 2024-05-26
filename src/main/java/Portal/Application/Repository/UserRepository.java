package Portal.Application.Repository;

import Portal.Application.Entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDetail, UUID> {
    UserDetail findFirstByUsername(String username);
    void deleteById(UUID userId);

    Optional<UserDetail> findById(UUID userId);

    UserDetail findFirstByEmail(String email);
}

