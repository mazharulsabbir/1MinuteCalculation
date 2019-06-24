package com.wonder.sabbir.robin.DatabaseHelper;

public class DatabaseConnection {
    private String score;
    private String highScore;
    private String totalScore;

    public DatabaseConnection() {
    }

    public DatabaseConnection(String totalScore) {
        this.totalScore = totalScore;
    }

    public DatabaseConnection(String score, String totalScore) {
        this.totalScore = totalScore;
        this.score = score;
    }

    public DatabaseConnection(String score, String totalScore,  String highScore) {

        this.totalScore = totalScore;
        this.score = score;
        this.highScore = highScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public String getScore() {
        return score;
    }

    public String getHighScore() {
        return highScore;
    }
}
