# java 并发核心知识体系精讲
核心基础 + 内存模型 + 死锁 ----- 从用法到原理
## 章节一、线程8大核心基础知识

### 一、实现多线程的方法到底有1种还是2中还是4种？(实现多线程的短信错误和正确方法)
#### 1、正确的理解 (2种)
##### 方法一、实现Runnable接口，重写run()函数；运行start()方法
    更好
##### 方法二、继承Thread类，重写run()函数，运行start()方法
    缺点：
        从架构的角度来讲，任务的执行(run方法)和线程的创建应该是解耦的，不应该混为一谈
        如果继承了Thread类，就需要新建一个线程来启动这个线程，执行完之后还需要释放资源，而实现了Runnable接口可以利用线程池来优化
        java是单继承的，限制了可扩展性 
##### 思考：同时用2中方法会怎么样？
###### 用面向对象的思想去考虑 
    实现Runnable接口以后，执行执行重写的run()方法之前会执行
        @Override
            public void run() {
                if (target != null) {
                    target.run();
                }
            }
    而继承了Thread类以后，重写了run()方法则会覆盖父类的这行代码
    所以同时使用Runnable和Thread的时候，target也是会通过构造器传入进去的，但是被覆盖以后就不会被执行了  
##### 俩种方法对比
###### 方法一: 最终调用target.run()
###### 方法二: run() 整个都被重写
##### 总结：最精准的描述
###### 1、通常我可可以分为两类，Oracle也是这么说的
###### 2、准确的将，创建线程只有一种方式那就是构造Thread类，而实现线程的执行单元有两种方式
    方法一：实现Runnable接口的run方法，并把Runnable实例传给Thread类
    方法二：重写Thread的run方法(继承Thread类)
#### 2、经典错误观点
##### "线程池创建线程也算一种新建线程的方式"
    不对，线程池底层创建线程的本质也是new Thread()
##### "通过Callable和Future创建线程，也算是一种新建线程的方式"
    不对， 底层也是依赖 实现了Runnable接口
##### "无返回值是实现Runnable接口，有返回值是实现Callable接口，所以Callable是新的实现线程的方式"
##### 定时器
    不对，底层还是线程类的包装
##### 匿名内部类

##### lambda表达式
##### 典型错误观点总结
###### 1、多线程的实现方式，在代码中写法千变万化，但其本质万变不离其宗
#### 3、常见面试问题
##### 有多少种实现线程的方法？思路有5点
###### 1、从不同的角度看，会有不同的答案。
###### 2、典型答案是两种
###### 3、我们看原理，两中本质都是一样的
###### 4、具体展开说其他方式
    线程池、计时器、匿名内部类等等
###### 5、结论
    本质上只有一种，它是新建Thread类，但是通常我们把它区分为俩种形式，
    一种是Runnable、一种是继承Thread类，还存在其他的表现形式：线程池、计时器、lambda、匿名内部类
##### 实现Runnable接口和继承Thread类那种方式更好？
###### 1、从代码架构角度：任务的执行(run方法)和线程的创建应该是解耦的，不应该混为一谈
###### 2、新建线程的损耗：如果继承了Thread类，就需要新建一个线程来启动这个线程，执行完之后还需要释放资源，而实现了Runnable接口可以利用线程池来优化，从而进行反复的利用
###### 3、Java不支持双继承

#### 4、彩蛋：
##### 如何从宏观和微观俩个方面来提高技术？
##### 如何了解技术领域的前沿动态？
##### 如何在业务开发中成长？

### 二、启动线程的正确和错误的方法
#### 1、start()和run()的比较
#### 2、start()方法原理解读
##### 方法含义
###### 1、启动新线程
    通知jvm有时间运行一下这个线程
###### 2、准备工作
    线程处于就绪状态
###### 3、不能重复start
##### 源码解读
###### 1、启动新线程检查线程状态
###### 2、加入线程组
###### 3、调用start0()
###### native start()方法

#### 3、run()方法原理解读
##### 源码解析 --- 俩种情况
#### 4、常见面试问题
##### 一个线程俩次调用start()方法会出现什么情况？为什么？
###### 会抛出异常，因为在start()源码中首先会进行线程状态的检查  
##### 既然start()方法会调用run()方法，为什么我们选择调用start()方法，而不是直接调用run()方法呢？
###### 因为调用start()方法才是真正意义上启动一个线程，如果直接调用一个run()方法就是调用一个普通的方法


### 三、停止线程的正确方法(重要、难点) -- 上山容易下山难
#### 1、原理介绍：使用interrupt来通知，而不是强制
#### 2、最佳实践：如何正确停止线程
##### 通常的停止过程 (无外界干涉的情况下)
###### 线程执行完以后就会停止
###### 线程执行的过程中抛出了异常
##### 正确方法：用interrupt来请求停止线程
###### 普通情况(run方法内没有sleep或wait方法时的写法)
###### 线程可能被阻塞 包含sleep()方法
###### 如果线程在每次工作迭代之后都阻塞(调用sleep方法等)
###### while内try/catch的问题  会导致线程无法停止
#### 实际开发中的最佳实践
##### 1、优先选择：传递中断
##### 2、不想或无法传递：恢复中断
##### 3、不应屏蔽中断
#### 响应中断的方法总结列表
###### 可以为了响应中断而抛出InterruptException的常见方法列表总结
    响应中断指的是在wait、sleep、join...等过程中，如果有一个中断信号过来了，它能够感知到，及时响应中断
##### 1、Object.wait()/wait( long)/wait(long, int)
##### 2、Thread.sleep(long)/sleep(long, int)
##### 3、Thread.join()/join(long)/join(long, int)
##### 4、java.util.concurrent.BlockingQueue.task()/put(E)
##### 5、java.util.concurrent.locks.Lock.lockInterruptibly()
##### 6、java.util.concurrent.CountDownLatch.await()
##### 7、java.util.concurrent.CyclicBarrier.await()
##### 8、java.util.concurrent.Exchanger.exchanger(V)
##### 9、nio.channels.InterruptibleChannel相关方法
##### 10、java.nio.channels.Selector的相关方法  

##### 为什么要我们要使用interrupt来停止线程？好处
    被中断的线程本有拥有如何响应中断的权利，
    因为某些线程的某些代码是非常重要的，我们必须等待这些线程准备好之后主动释放

#### 3、错误的停止方法
##### 被弃用的stop、suspend和resume方法
###### 使用stop的后果
###### 关于stop的一种错误理论
###### suspend的问题
    suspend 并不会破坏对象，但是它会让一个线程挂起，在恢复之前，锁不会释放，也就是它是带着锁去休息的，这就很容易会造成死锁
##### 用volatile设置boolean标记位
###### 这种停止方法也是不对的， 无法停止处于长时间阻塞等待的线程
#### 4、停止线程相关的重要函数解析
##### 中断线程
###### interrupt方法原理
##### 判断是否已被中断
###### static boolean interrupted()
    检测当前线程(执行该方法的线程)的状态是否是中断状态， 在结果返回之后，
    直接把当前线程的中断状态设为false，直接把线程中断状态给清除了，
    这也是我们唯一能清除线程中断状态的方法
###### boolean isInterrupted()
    同样是检测当前线程的状态是否是中断状态，不会清除线程的中断状态
###### 举例说明，注意Thread.interrupted的目标是"当前线程",而不管方法是来自哪
#### 5、常见面试问题
##### 如何停止线程？
    1、原理： 用interrupt来请求，好处可以保证数据安全，要把线程中断的主动权交给被中断的线程
    2、想停止线程，要请求方（提交中断请求 -- interrupt）、
        被停止方（要设置拦截请求中断 比如 isInterrupted 或者 包含响应中断的方法 sleep、 wait、join ...）、
        子方法被调用方（在编写子方法的时候要将异常显示的抛出，而不要将异常隐藏， 这里的异常通常是 InterruptedException）相互配合
    3、最后再说错误的方法： stop/suspend已废弃，volatile的boolean无法处理长时间阻塞的情况
##### 如何处理不可中断的阻塞(例如抢锁时ReentrantLock.lock()或者Socket I/O时无响应中断，那应该怎么让该线程停止呢？)
###### 并没有通用的处理手段 
    ReentrantLock可重入锁，如果真的使用了lock()方法，并且在执行过程中被阻塞了，是没有办法相应中断线程的。
        但是这个类提供了lockInterruptibly()方法，这个方法是可以相应中断的
    针对传统的IO阻塞，是没有办法相应中断的，但是可以使用特定的IO操作。

### 四、线程的6个状态(线程的生命周期)
#### 有哪6个状态？ 每个状态是什么含义
    New(新建)                 用new Thread()新建了一个线程，还没有执行thread.start()
    Runnable(可运行)          用了thread.start()方法以后, 针对了操作系统中的ready和running俩种状态
    Blocked(阻塞)             当一个线程进入到被synchronize修饰方法或语句块的时候，并且该锁已经被其他线程拿走了，也就是该monitor已经被其他线程拿走了            
    Waiting(等待)             当调用Object.wait()、Thread.join()、LockSupport.park()      
    Timed Waiting(定时等待)   当调用Thread.sleep(time)、Object.wait(time)、Thread.join(time)、LockSupport.parkNanos(time)、LockSupport.parkUntil(time)
    Terminated(终止)          run()方法正常执行完毕，或者是出现了一个没有被捕获的异常，终止了run()方法
##### Blocked状态和Waiting状态的区别是：Blocked状态等待的是其他线程释放一个排他锁，释放一个monitor；而Waiting和Timed Waiting等待的被唤醒或者一段被设置好的时间

#### 阻塞状态
##### 1、一般习惯而言，把Blocked(被阻塞)、Waiting(等待)、Timed_waiting(计时等待)都称为阻塞状态
##### 2、不仅仅是Blocked
###### 阻塞很长时间和运行需要很长时间的方法的区别
    在阻塞的情况下，什么时候被唤醒是不受自己控制的
    而执行时间很长的方法是可以自己控制的
    
   
#### 常见面试问题
##### 线程有哪几种状态？ 生命周期是什么
    
### 五、Thread和Object类中和线程相关的重要方法

#### 常用方法概览
#### wait()、notify()、notifyAll()方法
##### wait/notify/notifyAll的作用、用法
###### 1、阻塞阶段 
###### 2、唤醒阶段
###### 3、遇到中断
##### 直到以下4中情况之一时，才会被唤醒
###### 1、另一个线程调用这个对象的notify()方法且刚好被唤醒的是本线程
###### 2、另一个线程调用这个对象的notifyAll()方法；
###### 3、过了wait(long timeout)规定的超时时间，如果传入0就是永久等待
###### 4、线程自身调用了interrupt()
##### 代码展示
###### 普通用法 
###### notify和notifyAll展示
###### 只释放当前monitor展示
##### wait/notify/notifyAll的特点、性质
###### 1、用必须先拥有monitor
###### 2、只能唤醒其中一个
###### 3、属于Object类
###### 4、类似功能的Condition
###### 5、同时持有多个锁的情况， 释放的顺序、获取的顺序不当就会导致死锁的发生
##### 注意点
###### 1、执行wait/notify/notifyAll前必须获得对应的monitor
###### 2、过多的上下文切换，造成性能损耗
###### 3、唤醒信号丢失
###### 4、过早唤醒问题 (Wakeup too soon)

#### 常见面试问题
##### 用程序实现俩个线程交替打印 0 - 100 的奇偶数 
    偶线程：0
    基线程：1
    偶线程：2
    基本思路：synchronized  存在很多无用操作，性能低
    更好的方法： wait() 和 notify()
##### 手写生产者消费者设计模式
###### 1、为什么要使用生产者和消费者模式？
           在现实的场景下，经常存在一个生产者生产过快，或者消费者消费过快等问题
           通过生产者消费者模式可以使生产者和消费者之间进行解耦
           
##### 为什么wait()需要在同步代码块内使用，而sleep不需要
    主要是为了让通信变得可靠，防止死锁或者永久等待的发生，
    如果不把wait和notify放在代码块中的话，很有可能是执行wait之前，线程突然切到另外一个执行notify的线程
    因为没有同步代码块保护，线程随时都可能切换，这样对面的所有线程就把notify都执行完毕了，然后再切回来执行这个wait
    本身的逻辑是想执行完wait之后在执行另一个线程的notify来唤醒它，
    
    而sleep针对的是线程自己，和其他线程关系不大
    
##### 为什么线程通信的方法wait()、notify()和notifyAll()被定义在Object类里？而sleep定义在Thread类里？
    在java中wait()、notify()和notifyAll()是锁级别的操作，而锁时属于某个对象的， 
    每一个对象的对象头中都是含有几位用来保存当前锁的状态的，所以锁实际上是绑定到某一个对象中，而不是线程中
    
    反过来想，假设把wait()、notify()和notifyAll()定义在线程(Thread类)中：
    因为经常会有一个线程持有多个对象锁，多个锁时相互配合的，这样一来某个线程具有多把锁，
    如果把wait()、notify()和notifyAll()定义在Thread类里，则不能实现一个线程中多个锁相互配合。
##### wait()方法是属于Object对象的，那调用Thread.wait()会怎么样？
    Thread中的wait方法在线程退出的时候会自动调用notify唤醒其他线程
##### 如何选择用notify还是notifyAll
    唤醒一个线程用notify(),唤醒多个线程用notifyAll()
##### notifyAll之后所有的线程都会再次抢夺锁，如果某线程抢夺失败怎么办？
    没有抢到锁的线程会继续等待持有锁的线程释放锁，或者说等待线程调度器的调度
##### 用suspend()和resume()来阻塞线程可以吗？为什么？
    不推荐，推荐用wait()和notify()来实现
##### Java SE 8 和 JDK 8 是什么关系， 是一个东西吗？
    1、JavaSE(标准版)、JavaEE(企业版)、JavaME(移动版，适合容量非常小的移动端)是什么？
        现在一概使用JavaSE
    2、JRE和JDK和JVM是什么关系？
        JRE(Java Runtime Environment) 运行java程序的环境，包含JVM
        JDK(Java Development kit) 开发工具，包含JRE 和 JVM
    3、Java版本升级都包含了那些东西的升级？
        类的升级和JVM的升级
    4、Java 8 和 Java 1.8 和 JDK 8 是什么关系， 是同一个东西吗？
        Java 8 代表 JavaSE 8 
    
##### join()和sleep()和wait()期间线程的状态分别是什么？ 为什么？


#### sleep()方法详解
##### 作用：只想让线程在预期的时间执行，其他时候不要占用CPU资源
##### sleep 方法不释放锁
    1、包括synchronized和lock
    2、和wait不同，wait会释放锁
##### sleep方法响应中断
    1、抛出InterruptedException
    2、清除中断状态
###### 第二种语法(更优雅)
    更优雅 可以对负数进行忽略处理
    TimeUnit.HOURS.sleep(3);        // 3小时
    TimeUnit.MINUTES.sleep(25);     // 25分钟
    TimeUnit.SECONDS.sleep(1);      // 1秒钟
    
    public void sleep(long timeout) throws InterruptedException {
            if (timeout > 0) {      // 如果参数小于0 直接跳过，什么都不做
                long ms = toMillis(timeout);
                int ns = excessNanos(timeout, ms);
                Thread.sleep(ms, ns);
            }
    }
    
    Thread.sleep(5000) 只能接受long类型的参数，休眠时间需要我们自己转换
    
    public static void sleep(long millis, int nanos)
        throws InterruptedException {
            if (millis < 0) {       // 如果参数小于0 会抛出异常
                throw new IllegalArgumentException("timeout value is negative");
            }
            if (nanos < 0 || nanos > 999999) {
                throw new IllegalArgumentException(
                                    "nanosecond timeout value out of range");
            }
            if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
                millis++;
            }
            sleep(millis);
    }
    
##### 总结(一句话总结，方便记忆)
###### sleep方法可以让线程进入Waiting状态，并且不占用CPU资源，但是不释放锁，知道规定时间后再执行，休眠期间如果被中断，会抛出InterruptedException异常，并清除中断状态

##### sleep方法 - 常见面试问题
###### wait/notify、sleep异同(方法属于哪个对象？ 线程状态怎么切换？) 
    1、相同点
        ①、阻塞，           都可以使线程进行阻塞
        ②、响应中断，        即便是在休眠期间遇到interrupt，也可以响应中断
    2、不同点
        ①、同步方法中：    wait 和 notify 是必须在同步方法中去执行，sleep不需要
        ②、释放锁：        wait 会释放锁，而sleep方法是不会释放锁的
        ③、指定时间：      sleep方法必须需要参数，而wait如果不传参数则是直到自己被唤醒
        ④、所属类：        wait 和 notify是属于Object的方法， sleep是属于 Thread的方法
    

#### join()方法
##### 作用：因为新的线程加入我们，所以我们要等他们执行完成再出发
##### 用法：mian等待thread1执行完毕，注意谁等谁： 主线程等待调用了该方法的线程
##### 例子 
###### 普通用法
###### 遇到中断 
###### 在join期间，线程到底是什么状态?   WAITING
##### 注意点
###### CountDownLatch 或 CyclicBarrier类
    尽量不要使用join()方法，要使用这些类似功能的成熟的工具类
##### join(原理) 
    每个Thread类在执行了run方法之后，都会自动执行notify方法来唤醒其他等待的线程
###### 分析源码
##### 常见面试问题 
###### 在join期间，线程处于那种线程状态？WAITING


#### yield()方法 
##### 作用：放弃已经获取到的CPU资源, 但是线程的状态仍然是 Runnable 状态
##### 定位： JVM不保证遵循
##### yeild 和 sleep 区别 ？ 是否随时可能再次被调度
    yeild方法只是暂时将自己的调度权放弃，但是在下一次竞争中还是能参与CPU资源的抢占的 
    sleep会放弃当前CPU的调度权，状态处于 TIMED_WAITING 状态，等待休眠时间到了才重新恢复到 Runnable
    

    
#### 获取当前执行线程的引用: Thread.currentThread()方法

#### start()和run()方法介绍详见《启动线程的正确方式》

#### stop()、suspend()、resume()详见《错误的停止方法》


### 六、线程各属性
#### 线程各属性纵览
#### 线程id
#### 线程名字 
##### 默认名称源码分析
##### 修改线程的名字
#### 守护线程
##### 作用： 给用户线程提供服务
##### 特性：
###### 线程类型默认继承自父线程
###### 被谁启动？
##### 守护线程和普通线程的区别
##### 常见面试问题
###### 守护线程和普通线程的区别
###### 我们是否需要给线程设置为守护线程？
#### 线程优先级
##### 10 个级别
##### 程序设计不应该依赖于优先级
#### 各属性的赋值阶段 --- 线程的初始化过程
#### 各属性总结
#### 面试常见问题 
##### 为什么Java中不建议使用线程组


### 七、线程的为捕获异常UncaughtException应该如何处理？
#### 为什么需要UncaughtExceptionHandler？
#### 彩蛋：补充知识点 --- Java异常体系
#### 解决方案
#### 面试常见问题
##### 方案一(不推荐): 手动在每个run方法里进行try catch
##### 方案二(推荐) : 利用UncaughtExceptionHandler
###### 默认的UncaughtExceptionHandler
###### 异常处理器的调用策略
###### 自己继承并实现UncaughtException
##### 常见面试问题
###### run方法是否可以跑出异常？ 如果跑出异常，线程的状态回事怎么样？
###### 线程中如何处理某个未处理异常


### 八、线程是把双刃剑：多线程或导致性能问题(线程引入的开销，上下文切换)
#### 线程安全
##### 什么是线程安全
##### 什么情况下会出现线程安全问题，怎么避免
###### 运行结果错误(a ++ 多线程下出现消失的请求现象，属于read-modify-write)
###### 死锁等活跃性问题(包括死锁、活锁、饥饿)
###### 对象发布和初始化的时候的安全问题
###### 总结归纳：各种需要考虑线程安全的情况
#### 性能问题有哪些体现，什么是性能问题
#### 为什么多线程会带来性能问题
##### 调度：上下文切换
##### 协作：内存同步
#### 常见面试问题

### 常见面试题