package leets.leets_mate;

import leets.leets_mate.controller.MatchingController;

public class LeetsMateApplication {
    public static void main(String[] args) {
        MatchingController controller = new MatchingController();
        controller.run();
    }
}