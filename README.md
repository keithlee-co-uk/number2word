# number2word
convert digits to English words (TDD practice &amp; learning)

## Premise 
* Take a 'number' up to 999,999,999, as input.
* Gives the equivalent number in British English words as output.
* Does not use a tokeniser or external libraries.

## Assumptions
* 'number' is an integer
* 'number' is positive
* a 'number', a billion or over, will not be converted

## Example input to outputs
* 1 = one
* 20 = twenty
* 21 = twenty one
* 100 = one hundred
* 105 = one hundred and five
* 1105 = one thousand one hundred and five
* 30021 = thirty thousand and twenty one
* 56945781 = fifty six million nine hundred and forty five thousand seven hundred and eighty one
 
  
## Conversion Rules
#####Zero Rule
 If the value is zero then the number in words is 'zero' and no other rules apply.

#####Three Digit Rule
 The integer value is split into groups of three digits starting from the right-hand side. 
 Each set of three digits is then processed individually as a number of hundreds, tens and units.
  Once converted to text, the three-digit groups are recombined with the addition of the relevant 
  scale number (thousand, million).

#####Hundreds Rule
 If the hundreds portion of a three-digit group is not zero, the number of hundreds is added as
  a word. If the three-digit group is exactly divisible by one hundred, the text 'hundred' is
   appended. If not, the text "hundred and" is appended. eg. 'two hundred' or 'one hundred and 
   twelve'

#####Tens Rule
 If the tens section of a three-digit group is two or higher, the appropriate '-ty' word 
 (twenty, thirty, etc.) is added to the text and followed by the name of the third digit 
 (unless the third digit is a zero, which is ignored). If the tens and the units are both zero,
  no text is added. For any other value, the name of the one or two-digit number is added as a 
  special case.

#####Recombination Rule
When recombining the translated three-digit groups, 
each group except the last is followed by a large number name, unless the group is 
blank and therefore not included at all. One exception is when the final group does not include 
any hundreds and there is more than one non-blank group. In this case, the final part is prepended 
with 'and'. eg. 'one million and twelve'.


[ref](http://www.blackwasp.co.uk)