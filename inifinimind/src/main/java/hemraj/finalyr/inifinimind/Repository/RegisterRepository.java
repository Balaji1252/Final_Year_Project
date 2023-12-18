package hemraj.finalyr.inifinimind.Repository;

import hemraj.finalyr.inifinimind.Model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register,String> {
}
