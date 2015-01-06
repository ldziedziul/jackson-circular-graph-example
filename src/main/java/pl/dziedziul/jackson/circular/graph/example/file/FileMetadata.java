package pl.dziedziul.jackson.circular.graph.example.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by luk on 06.01.15.
 */
@Entity

public class FileMetadata {
    @JsonIgnoreProperties({"fileMetadata"})
    @OneToOne
    private FileVersion latest;
    private String filename;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FileVersion getLatest() {
        return latest;
    }

    public void setLatest(FileVersion latest) {
        this.latest = latest;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public FileMetadata() {
    }

    public FileMetadata(String filename) {
        this.filename = filename;
    }

    public FileVersion createNextVersion(){
        FileVersion fm = new FileVersion(latest);
        return fm;
    }

}
