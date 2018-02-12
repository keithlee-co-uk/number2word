
class Number2Word {
    def unitMap = ['', "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]

    def number2Words(int number){
        if(number == 0){
            return "zero"
        }

        if(number > 999999999 || number < 0) {
            throw new NumberOutOfBounds('Expected a positive integer less than a billion.')
        }

    }

    def splitToThreeDigits(int number) {
        def hundredGroups = []
        // TODO: breakout regex to clearer/explicitly named method eg matchThree()
        def hundredGroupsReversed = number.toString().reverse().split("(?<=\\G...)")

        for(numberSet in hundredGroupsReversed) {
            if (numberSet.size() < 3) {
                numberSet = numberSet.padRight(3, "0")
            }
            hundredGroups.add(numberSet.reverse())
        }
        return hundredGroups
    }

    def hundredthsWord(String threeSet) {
        def hundredthDigit = threeSet.substring(0, 1) as int
        return unitMap[hundredthDigit]
    }
}


class NumberOutOfBounds extends Exception{
    public String message

    NumberOutOfBounds(String message){
        this.message = message
    }


    @Override
    String getMessage(){
        return message
    }
}