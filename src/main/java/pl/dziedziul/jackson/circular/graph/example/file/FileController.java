package pl.dziedziul.jackson.circular.graph.example.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luk on 06.01.15.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<FileMetadata> list() {
        return fileService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public FileMetadata create() {
        return fileService.create();
    }

    @RequestMapping(value = "/{id}/versions")
    public Iterable<FileVersion> versions(@PathVariable("id") Long id) {
        return fileService.getVersions(id);
    }

    @RequestMapping(value = "/{id}")
    public FileMetadata show(@PathVariable("id") Long id) {
        return fileService.get(id);
    }


}
