var intArray = Java.type('int[]')

var numbers = new intArray(10)

for (var i = 0; i < numbers.length; i++) {
    numbers[i] = i + 1
}

var sum = 0

for (var i in numbers) {
   sum += numbers[i]
}

/*for each (var x in numbers) {
 sum += x;
}*/

sum