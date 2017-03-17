package com.m2dl.shifoomi;

/**
 * Created by Timothee on 17/03/2017.
 */

public class EloCalculator {

    private final double coefficientK = 40.0;

    private int player1Elo;
    private int player2Elo;
    private int matchResult;

    public EloCalculator(int player1Elo, int player2Elo) {
        this.player1Elo = player1Elo;
        this.player2Elo = player2Elo;
        this.matchResult = matchResult;
    }

    public int getNewPlayer1Elo() {
        double playerResult;
        switch (matchResult) {
            case 1:
                playerResult = 1;
                break;
            case 0:
                playerResult = 0.5;
                break;
            default:
                playerResult = 0;
        }

        double eloDifference = computeEloDifference(player1Elo, player2Elo);
        double probability = computeProbability(eloDifference);

        return computeNewElo(player1Elo, playerResult, probability);
    }

    public int getNewPlayer2Elo() {
        double playerResult;
        switch (matchResult) {
            case -1:
                playerResult = 1;
                break;
            case 0:
                playerResult = 0.5;
                break;
            default:
                playerResult = 0;
        }


        double eloDifference = computeEloDifference(player2Elo, player1Elo);
        double probability = computeProbability(eloDifference);

        return computeNewElo(player2Elo, playerResult, probability);
    }

    private int computeNewElo(int playerElo, double result, double probability) {
        return playerElo + (int) (coefficientK * (result - probability));
    }

    private int computeEloDifference(int elo1, int elo2) {

        int eloDifference;
        if (elo1 - elo2 > 400) {
            eloDifference = 400;
        } else if (elo1 - elo2 < 400) {
            eloDifference = -400;
        }
        else {
            eloDifference = player1Elo - player2Elo;
        }

        return eloDifference;
    }

    private double computeProbability(double eloDifference) {
        return 1.0 / (1.0 + Math.pow(10.0, (-eloDifference/400.0)));
    }
}
