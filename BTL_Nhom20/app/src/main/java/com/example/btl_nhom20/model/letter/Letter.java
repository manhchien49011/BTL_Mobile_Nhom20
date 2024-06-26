package com.example.btl_nhom20.model.letter;

import com.example.btl_nhom20.types.TypeOfLetter;

public class Letter {
    private TypeOfLetter typeOfLetter;
    private String timeOfLetter;
    private String reasonResignation;
    private String userId;
    private String username;
    private int workspaceId;

    public Letter(TypeOfLetter typeOfLetter, String timeOfLetter, String reasonResignation, int workspaceId, String userId, String username) {
        this.typeOfLetter = typeOfLetter;
        this.timeOfLetter = timeOfLetter;
        this.reasonResignation = reasonResignation;
        this.workspaceId = workspaceId;
        this.userId = userId;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Letter() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }

    public TypeOfLetter getTypeOfLetter() {
        return typeOfLetter;
    }

    public void setTypeOfLetter(TypeOfLetter typeOfLetter) {
        this.typeOfLetter = typeOfLetter;
    }

    public String getTimeOfLetter() {
        return timeOfLetter;
    }

    public void setTimeOfLetter(String timeOfLetter) {
        this.timeOfLetter = timeOfLetter;
    }

    public String getReasonResignation() {
        return reasonResignation;
    }

    public void setReasonResignation(String reasonResignation) {
        this.reasonResignation = reasonResignation;
    }
}
