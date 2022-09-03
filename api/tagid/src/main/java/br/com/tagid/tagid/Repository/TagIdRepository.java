package br.com.tagid.tagid.Repository;

import br.com.tagid.tagid.Entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagIdRepository extends JpaRepository<Tag, String> {
    List<Tag> findByStatus(boolean status);
}
