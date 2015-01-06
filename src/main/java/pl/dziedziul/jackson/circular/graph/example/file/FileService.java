package pl.dziedziul.jackson.circular.graph.example.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

/**
 * Created by luk on 06.01.15.
 */
@Service
public class FileService {
    @Autowired
    private FileVersionRepository fileVersionRepository;
    @Autowired
    private FileMetadataRepository fileMetadataRepository;

    private Random random = new Random(Instant.now().getEpochSecond());

    @Transactional
    public FileMetadata create() {
        String uuid = UUID.randomUUID().toString();
        FileMetadata fmm = new FileMetadata(uuid + ".txt");
        FileVersion fm = new FileVersion("bla, bla, bla", fmm);

        fm = fileVersionRepository.save(fm);

        int count = random.nextInt(10)+1;
        for (int i = 0; i < count; i++) {
            fm = fileVersionRepository.save(fmm.createNextVersion());
        }
        return fmm;
    }

    public Iterable<FileMetadata> findAll() {
        return fileMetadataRepository.findAll();
    }

    public Iterable<FileVersion> getVersions(Long fileMetadataId) {
        return fileVersionRepository.findAllVersions(fileMetadataId);
    }

    public FileMetadata get(Long id) {
        return fileMetadataRepository.findOne(id);
    }
}
