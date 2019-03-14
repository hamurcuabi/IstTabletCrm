package com.emrhmrc.isttabletcrm.models.ServApp;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;

public class Notes {
    private Integer FileSize;
    private boolean IsDocument;
    private String Subject;
    private String MimeType;
    private String FileName;
    private String DocumentBody;
    private String NoteText;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Expose
    private Bitmap bitmap;

    public Notes() {
    }

    public Integer getFileSize() {
        return FileSize;
    }

    public void setFileSize(Integer fileSize) {
        FileSize = fileSize;
    }

    public boolean isDocument() {
        return IsDocument;
    }

    public void setDocument(boolean document) {
        IsDocument = document;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMimeType() {
        return MimeType;
    }

    public void setMimeType(String mimeType) {
        MimeType = mimeType;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getDocumentBody() {
        return DocumentBody;
    }

    public void setDocumentBody(String documentBody) {
        DocumentBody = documentBody;
    }

    public String getNoteText() {
        return NoteText;
    }

    public void setNoteText(String noteText) {
        NoteText = noteText;
    }
}
