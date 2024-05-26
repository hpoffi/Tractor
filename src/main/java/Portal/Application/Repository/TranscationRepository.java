package Portal.Application.Repository;

import Portal.Application.Entity.Transcation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscationRepository extends JpaRepository<Transcation,Long> {
}
