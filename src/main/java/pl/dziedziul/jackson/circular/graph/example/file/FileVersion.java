package pl.dziedziul.jackson.circular.graph.example.file;

import javax.persistence.*;

/**
 * Created by luk on 06.01.15.
 */
@Entity
public class FileVersion {
    private String content;
    private int version;
    @ManyToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "file_metadata_id")
    private FileMetadata fileMetadata;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    Long id;
    public Long getId() {
        return id;
    }

    public FileVersion() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    public void onFirstSave(){
        fileMetadata.setLatest(this);
    }

    public FileVersion(String content, FileMetadata fileMetadata) {
        this.content = content;
        this.version = 1;
        this.fileMetadata = fileMetadata;

    }

    public FileVersion(FileVersion previousVersion) {
        version = previousVersion.version+1;
        content = "version " + version;
        fileMetadata = previousVersion.fileMetadata;
        fileMetadata.setLatest(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FileMetadata getFileMetadata() {
        return fileMetadata;
    }

    public void setFileMetadata(FileMetadata fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
