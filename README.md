## broadcaster
stateful TCP broadcaster with aggregation and transformation


### Design. Tech stack / libraries discussion.
Though I don’t like Akka’s actors, materialize (no need for them with Monix): monix-nio doesn’t necessary look like production-grade solution. (and there was a requirement “Желательно использование фреймворков, облегчающих написание сетевых серверов”)  
Yet not strictly nesessary and Akka's TCP server [code](https://github.com/akka/akka/blob/master/akka-actor/src/main/scala/akka/io/Tcp.scala)
is better than [monix-nio](https://github.com/monix/monix-nio/blob/master/src/main/scala/monix/nio/tcp/tcp.scala)
or [fs2](https://github.com/functional-streams-for-scala/fs2/blob/series/1.0/io/src/main/scala/fs2/io/tcp/Socket.scala)  

HARD TO WRAP….

Scala community advised (good feedback from people who used Akka streams before)
fs2.io.tcp
(I’m not sure whether fs2 is fully production ready & probably it’s not-conservative to use it,
but for the demo purposes it’s OK, anyway the interviewer advised me to write my “best code”.
Clean codebase. Large community. Active development. )


DESICION
+ Akka - no will
+ Monix - not good tcp for prod? Okay for the demo. Check if it works at all, first… yet OKay	Discussed on the interview a lot...  
+ FS2/ZIO - too complicated, no time … yet GOOD ALL + TCP is OK… next time for 2+ weeks!
I need to catch up with it quite a lot.


MONIX  

