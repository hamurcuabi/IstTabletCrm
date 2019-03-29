package com.emrhmrc.isttabletcrm.models.ServApp;

import android.net.Uri;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Notes implements Serializable {

    private boolean IsDocument;
    private String Subject;
    private String MimeType;
    private String FileName;
    private String DocumentBody;
    private String NoteText;
    @Expose
    private int from;
    @Expose
    private transient Uri selectedVideoUri;
    @Expose
    private transient Uri selectedImageUri;
    @Expose
    private boolean isImage1;

    public Notes() {
    }

    public Uri getSelectedImageUri() {
        return selectedImageUri;
    }

    public void setSelectedImageUri(Uri selectedImageUri) {
        this.selectedImageUri = selectedImageUri;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public Uri getSelectedVideoUri() {
        return selectedVideoUri;
    }

    public void setSelectedVideoUri(Uri selectedVideoUri) {
        this.selectedVideoUri = selectedVideoUri;
    }

    public boolean isImage1() {
        return isImage1;
    }

    public void setImage1(boolean image1) {
        isImage1 = image1;
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
