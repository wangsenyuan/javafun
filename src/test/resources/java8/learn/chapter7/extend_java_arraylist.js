var arr = new (Java.extend(java.util.ArrayList)) {
    add: function(x) {
        print('Adding ' + x);
        return Java.super(arr).add(x);
    }
};

arr.add(1);
arr.add(2);
arr.add(3);

var sum = 0;
for each(var x in arr) {
    sum += x;
}

sum