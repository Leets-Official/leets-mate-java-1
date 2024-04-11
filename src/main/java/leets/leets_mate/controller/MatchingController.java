package leets.leets_mate.controller;

import leets.leets_mate.view.InputView;
import leets.leets_mate.view.OutputView;

import java.util.Scanner;

public class MatchingController {

    public void run() {
        String[] arr = InputView.readMember();
        int number = InputView.readMaxGroup(arr);
    }
}
