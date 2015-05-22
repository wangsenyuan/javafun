var RandomIterator = function (limit) {
    return new (Java.extend(java.util.Iterator, {
        count: 0,
        next: function () {
            return Math.random();
        },
        hasNext: function () {
            return (this.count++) < limit;
        }
    }));
};

var iter = RandomIterator(10);

var count = 0;
while(iter.hasNext()) {
    lastOne = iter.next();
    count += 1;
}

count
