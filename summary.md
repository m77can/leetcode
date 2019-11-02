## 笔记

#### char 比较
````java
//直接比较
c >= '0' && c <= '9';
//或者直接转 int 比较

````

#### int 转化

````java
//使用 long 计算,防止超过 int 范围

sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE;

````

