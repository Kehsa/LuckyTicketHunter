package com.blackcube.kehsa.luckytickethunter;

public class LuckyTicketCalculator {
    private short[] leftNumbers;
    private short[] rightNumbers;
    private int numberSize;
    private int centralPos;

    public void setNumberSize(int numSize) {
        this.numberSize = numSize;
        centralPos = numSize / 2;
        leftNumbers = new short[centralPos];
        rightNumbers = new short[centralPos];
    }

    public int getNumberSize() {
        return numberSize;
    }

    private void setNumber(short[] numbers) {
        System.arraycopy(numbers, 0, leftNumbers, 0, centralPos);
        if (numberSize % 2 == 0)
            System.arraycopy(numbers, centralPos, rightNumbers, 0, centralPos);
        else
            System.arraycopy(numbers, centralPos + 1, rightNumbers, 0, centralPos);
    }

    private short[] toShortArray(String charNumbers) {
        short[] numbers = new short[numberSize];
        for (int i = 0; i < numberSize; ++i) {
            numbers[i] = Short.parseShort(charNumbers.substring(i , i+1));
        }
        return numbers;
    }

    public int nextLuckyMan(String charNumbers) {
        if (charNumbers.length() != numberSize) {
            return -1;
        }
        return nextLuckyMan(toShortArray(charNumbers));
    }

    private int nextLuckyMan(short[] numbers) {
        setNumber(numbers);
        int counter = 0;
        while (summ(leftNumbers) != summ(rightNumbers)) {
            if (nextNumber(rightNumbers))
                nextNumber(leftNumbers);
            counter++;
        }
        return counter;
    }

    private int summ(short[] mass) {
        int sum = 0;
        for (short mas : mass) sum += mas;
        return sum;
    }

    private boolean nextNumber(short[] mass) {
        boolean ansver = true;
        for (short mas : mass)
            if (mas != 9)
                ansver = false;
        if (ansver) {
            for (int c = 0; c < mass.length; ++c)
                mass[c] = 0;
        } else
            for (int c = mass.length - 1; c >= 0; --c)
                if (mass[c] != 9) {
                    mass[c]++;
                    break;
                } else
                    mass[c] = 0;
        return ansver;
    }
}
