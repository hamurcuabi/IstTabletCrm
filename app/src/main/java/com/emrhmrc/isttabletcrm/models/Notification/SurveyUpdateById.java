package com.emrhmrc.isttabletcrm.models.Notification;

public class SurveyUpdateById {
    private String SurveyId;
    private String SurveyQ1Answer;
    private String SurveyQ2Answer;
    private String SurveyQ3Answer;

    public String getSurveyId() {
        return SurveyId;
    }

    public void setSurveyId(String surveyId) {
        SurveyId = surveyId;
    }

    public String getSurveyQ1Answer() {
        return SurveyQ1Answer;
    }

    public void setSurveyQ1Answer(String surveyQ1Answer) {
        SurveyQ1Answer = surveyQ1Answer;
    }

    public String getSurveyQ2Answer() {
        return SurveyQ2Answer;
    }

    public void setSurveyQ2Answer(String surveyQ2Answer) {
        SurveyQ2Answer = surveyQ2Answer;
    }

    public String getSurveyQ3Answer() {
        return SurveyQ3Answer;
    }

    public void setSurveyQ3Answer(String surveyQ3Answer) {
        SurveyQ3Answer = surveyQ3Answer;
    }

    public String getSurveyDescription() {
        return SurveyDescription;
    }

    public void setSurveyDescription(String surveyDescription) {
        SurveyDescription = surveyDescription;
    }

    public SurveyUpdateById() {

    }

    private String SurveyDescription;
}
