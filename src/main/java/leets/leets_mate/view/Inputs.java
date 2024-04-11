package leets.leets_mate.view;


import leets.leets_mate.domain.MaxGroupSize;
import leets.leets_mate.domain.Members;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class Inputs {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Members members() {
        try {
            System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분)");
            return new Members(br.readLine());
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public MaxGroupSize maxGroupSize() {
        try {
            System.out.println();
            System.out.println("최대 짝 수를 입력해 주세요.");
            return new MaxGroupSize(Integer.parseInt(br.readLine()));
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public RetryOpinion retryOpinion() {
        try {
            System.out.print("다시 구성하시겠습니까? (y or n): ");
            String retryOpinion = br.readLine();
            if (retryOpinion.equals("n")) {
                return RetryOpinion.NO;
            } else if (retryOpinion.equals("y")) {
                return RetryOpinion.YES;
            } else {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}