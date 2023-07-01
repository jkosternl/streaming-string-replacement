# Assignment
Our Frank!Framework contains many components which can be connected together in a flexible way, to implement system integrations. These components we call “pipes”.

One of these pipes is a string replacement pipe.

This pipe works well for small strings, but does not work well on large input documents since it reads the entire input in memory. One of our planned improvements is to make this streaming, so it is more memory efficient.

How would you design such a streaming string replacement algorithm, that works with an InputStream or a Reader?

You do not need to worry about regular expressions or wildcards, as that is not currently supported anyway.


# Tips
Although not necessary for coming up with your design for such streaming string replacement, if you want you can look at the current implementation here:

https://github.com/ibissource/iaf/blob/master/core/src/main/java/nl/nn/adapterframework/pipes/ReplacerPipe.java

This may help you understand the context and current features, but don’t get distracted too much by it.