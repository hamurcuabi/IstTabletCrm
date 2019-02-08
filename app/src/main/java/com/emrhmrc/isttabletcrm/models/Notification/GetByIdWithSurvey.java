package com.emrhmrc.isttabletcrm.models.Notification;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class GetByIdWithSurvey {

    private String ErrorMsg;
    private boolean Success;
    private Notification Notification;
    private Inv_Id SurveyId;
    private String SurveyQ1;
    private String SurveyQ1Answer;
    private String SurveyQ1Type;
    private Code SurveyQ1Options;
    private String SurveyQ2;
    private String SurveyQ2Answer;
    private String SurveyQ2Type;
    private Code SurveyQ2Options;

    public GetByIdWithSurvey() {
    }

    private String SurveyQ3;

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public com.emrhmrc.isttabletcrm.models.Notification.Notification getNotification() {
        return Notification;
    }

    public void setNotification(com.emrhmrc.isttabletcrm.models.Notification.Notification notification) {
        Notification = notification;
    }

    public Inv_Id getSurveyId() {
        return SurveyId;
    }

    public void setSurveyId(Inv_Id surveyId) {
        SurveyId = surveyId;
    }

    public String getSurveyQ1() {
        return SurveyQ1;
    }

    public void setSurveyQ1(String surveyQ1) {
        SurveyQ1 = surveyQ1;
    }

    public String getSurveyQ1Answer() {
        return SurveyQ1Answer;
    }

    public void setSurveyQ1Answer(String surveyQ1Answer) {
        SurveyQ1Answer = surveyQ1Answer;
    }

    public String getSurveyQ1Type() {
        return SurveyQ1Type;
    }

    public void setSurveyQ1Type(String surveyQ1Type) {
        SurveyQ1Type = surveyQ1Type;
    }

    public Code getSurveyQ1Options() {
        return SurveyQ1Options;
    }

    public void setSurveyQ1Options(Code surveyQ1Options) {
        SurveyQ1Options = surveyQ1Options;
    }

    public String getSurveyQ2() {
        return SurveyQ2;
    }

    public void setSurveyQ2(String surveyQ2) {
        SurveyQ2 = surveyQ2;
    }

    public String getSurveyQ2Answer() {
        return SurveyQ2Answer;
    }

    public void setSurveyQ2Answer(String surveyQ2Answer) {
        SurveyQ2Answer = surveyQ2Answer;
    }

    public String getSurveyQ2Type() {
        return SurveyQ2Type;
    }

    public void setSurveyQ2Type(String surveyQ2Type) {
        SurveyQ2Type = surveyQ2Type;
    }

    public Code getSurveyQ2Options() {
        return SurveyQ2Options;
    }

    public void setSurveyQ2Options(Code surveyQ2Options) {
        SurveyQ2Options = surveyQ2Options;
    }

    public String getSurveyQ3() {
        return SurveyQ3;
    }

    public void setSurveyQ3(String surveyQ3) {
        SurveyQ3 = surveyQ3;
    }

    public String getSurveyQ3Type() {
        return SurveyQ3Type;
    }

    public void setSurveyQ3Type(String surveyQ3Type) {
        SurveyQ3Type = surveyQ3Type;
    }

    public String getSurveyQ3Answer() {
        return SurveyQ3Answer;
    }

    public void setSurveyQ3Answer(String surveyQ3Answer) {
        SurveyQ3Answer = surveyQ3Answer;
    }

    public String getSurveyStatusCode() {
        return SurveyStatusCode;
    }

    public void setSurveyStatusCode(String surveyStatusCode) {
        SurveyStatusCode = surveyStatusCode;
    }

    public Code getSurveyQ3Options() {
        return SurveyQ3Options;
    }

    public void setSurveyQ3Options(Code surveyQ3Options) {
        SurveyQ3Options = surveyQ3Options;
    }

    public String getSurveyDescription() {
        return SurveyDescription;
    }

    public void setSurveyDescription(String surveyDescription) {
        SurveyDescription = surveyDescription;
    }

    private String SurveyQ3Type;
    private String SurveyQ3Answer;
    private String SurveyStatusCode;
    private Code SurveyQ3Options;
    private String SurveyDescription;
}
