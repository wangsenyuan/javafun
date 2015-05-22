var intArray = Java.type('int[]')
var JArrays = Java.type('java.util.Arrays')
var numbers = new intArray(10)

for (var i = 0; i < numbers.length; i++) {
    numbers[i] = 10 - i;
}


JArrays.sort(numbers);

numbers[9]
