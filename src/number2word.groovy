
class Number2Word {
    def unitMap = ["", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    def tenMap = ["", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"]

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
        def hundredthsWords = ""
        def hundredthDigit = threeSet.substring(0, 1) as int

        if((threeSet as int).mod(100) == 0)
            hundredthsWords = " hundred"
        else if(hundredthDigit > 0)
            hundredthsWords = " hundred and"

        hundredthsWords = unitMap[hundredthDigit] + hundredthsWords
        return hundredthsWords

    }

    def tenWord(String threeSet) {
        def tenWords = ""
        def tenDigit = threeSet[1..1] as int
        def unitDigit = threeSet[2..2] as int
        def unitWord = unitMap[unitDigit]
        if(unitWord.size() > 0) {
            tenWords = tenMap[tenDigit] + " " + unitWord
        } else {
            tenWords = tenMap[tenDigit]
        }

        return tenWords
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