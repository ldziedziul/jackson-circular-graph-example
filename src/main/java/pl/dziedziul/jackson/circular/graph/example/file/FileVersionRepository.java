package pl.dziedziul.jackson.circular.graph.example.file;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by luk on 06.01.15.
 */
public interface FileVersionRepository extends CrudRepository<FileVersion, Long> {
    @Query("from FileVersion v where v.fileMetadata.id = :id")
    public List<FileVersion> findAllVersions(@Param("id") Long id);
}
