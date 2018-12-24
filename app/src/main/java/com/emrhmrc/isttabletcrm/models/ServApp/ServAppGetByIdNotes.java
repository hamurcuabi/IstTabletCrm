package com.emrhmrc.isttabletcrm.models.ServApp;


public class ServAppGetByIdNotes {
    private Integer FileSize;

    private boolean IsDocument;

    private String Subject;

    private String MimeType;

    private String FileName;

    private String DocumentBody;

    private String NoteId;

    private String NoteText;

    public Integer getFileSize() {
        return FileSize;
    }

    public void setFileSize(Integer FileSize) {
        this.FileSize = FileSize;
    }

    public boolean getIsDocument() {
        return IsDocument;
    }

    public void setIsDocument(boolean IsDocument) {
        this.IsDocument = IsDocument;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getMimeType() {
        return MimeType;
    }

    public void setMimeType(String MimeType) {
        this.MimeType = MimeType;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getDocumentBody() {
        return DocumentBody;
    }

    public void setDocumentBody(String DocumentBody) {
        this.DocumentBody = DocumentBody;
    }

    public String getNoteId() {
        return NoteId;
    }

    public void setNoteId(String NoteId) {
        this.NoteId = NoteId;
    }

    public String getNoteText() {
        return NoteText;
    }

    public void setNoteText(String NoteText) {
        this.NoteText = NoteText;
    }

    @Override
    public String toString() {
        return "ServAppGetByIdNotes{" +
                "FileSize='" + FileSize + '\'' +
                ", IsDocument='" + IsDocument + '\'' +
                ", Subject='" + Subject + '\'' +
                ", MimeType='" + MimeType + '\'' +
                ", FileName='" + FileName + '\'' +
                ", DocumentBody='" + DocumentBody + '\'' +
                ", NoteId='" + NoteId + '\'' +
                ", NoteText='" + NoteText + '\'' +
                '}';
    }
}


