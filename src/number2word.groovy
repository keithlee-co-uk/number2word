
class Number2Word {


    def words(int number){
        if(number == 0){
            return "zero"
        }

        if(number > 999999999) {
            throw new NumberOutOfBounds('Expected a positive integer less than a billion.')
        }

    }

    def splitToThreeDigits(int number) {
        def hundredGroups = []
        def hundredGroupsReversed = number.toString().reverse().split("(?<=\\G...)")
        for(numberSet in hundredGroupsReversed) {
            if (numberSet.size() < 3) {
                numberSet = numberSet.padRight(3, "0")
            }
            hundredGroups.add(numberSet.reverse())
        }
        return hundredGroups
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