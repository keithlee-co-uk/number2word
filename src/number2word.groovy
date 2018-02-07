public class Number2Word {
    Number2Word() {

        }

    def words(int number){
        def singles = ["zero", "one", "two", "three", "four"]
        return singles.get(number)

    }
}
